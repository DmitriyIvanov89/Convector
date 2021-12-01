import ordersparser.consumer.Consumer;
import ordersparser.model.FileExtensions;
import ordersparser.model.Message;
import ordersparser.model.MessageType;
import ordersparser.model.ProducerType;
import ordersparser.sevice.CsvProducer;
import ordersparser.sevice.JsonProducer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_PRODUCERS_COUNT = 2;
    private static final int MAX_CONSUMERS_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {

        if (validateArgs(args)) {
            BlockingQueue<Message> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
            runConsumers(queue);
            Map<String, String > files = getFiles(args);
            runProducers(files, queue);
        }
    }

    // refactor this method with stream
    private static Map<String, String> getFiles(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            files.put(path, getFileExtension(path));
        }
        return files;
    }

    private static void runConsumers(BlockingQueue<Message> queue) {
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

    // added producer type
    private static void runProducers(Map<String, String> files, BlockingQueue<Message> queue) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(files.size());
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);

        for (Map.Entry<String, String> entry : files.entrySet()) {
            if (entry.getValue().equals(FileExtensions.JSONL.getFileExtension())) {
                executorService.execute(new JsonProducer(entry.getKey(), queue, countDownLatch));
            }
            if (entry.getValue().equals(FileExtensions.CSV.getFileExtension())) {
                executorService.execute(new CsvProducer(entry.getKey(), queue, countDownLatch));
            }
        }

        countDownLatch.await();
        executorService.shutdown();
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            queue.put(new Message(MessageType.POISON_PILL, null));
        }
    }

    private static boolean validateArgs(String[] args) {
        for (String path : args) {
            Path filePath = Paths.get(path);
            String fileExtension = getFileExtension(path);
            if (!Files.exists(filePath)) {
                System.out.printf("File: %s not found.", filePath.getFileName());
                return false;
            }
            if (!fileExtension.equals(ProducerType.CSV.getType()) && !fileExtension.equals(ProducerType.JSONL.getType())) {
                System.out.printf("Unknown file extension: %s on the path: %s", fileExtension, path);
                return false;
            }
        }
        return true;
    }

    private static String getFileExtension(String path) {
        return path.substring(path.lastIndexOf(".") + 1).toUpperCase();
    }
}
