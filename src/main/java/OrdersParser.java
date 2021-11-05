
import ordersparser.consumer.Consumer;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.validator.Validator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_CONSUMERS_COUNT = 2;
    private static final int MAX_PRODUCERS_COUNT = 3;

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 0) {
            new Validator().validateArgs(args);
        } else {
            System.out.println("Incorrect args!");
        }

        Map<String, String> files = getFiles(args);
        BlockingQueue<OrderIn> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        runProducers(files, queue);
        runConsumers(queue);
    }


    public static Map<String, String> getFiles(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            files.put(path, path.substring(path.lastIndexOf(".") + 1).toUpperCase());
        }

        return files;
    }

    public static void runConsumers(BlockingQueue<OrderIn> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

    public static void runProducers(Map<String, String> files, BlockingQueue<OrderIn> queue) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(files.size());
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        MessageType type = MessageType.REGULAR;
        for (Map.Entry<String, String> entry : files.entrySet()) {
            if (entry.getValue().equals("JSONL")) {
                executorService.execute(new JsonProducer(entry.getKey(), queue, type));
                countDownLatch.countDown();
            }
            if (entry.getValue().equals("CSV")) {
                executorService.execute(new CsvProducer(entry.getKey(), queue, type));
                countDownLatch.countDown();
            }
        }
        countDownLatch.await();
        type = MessageType.POISON_PILL;
    }

}
