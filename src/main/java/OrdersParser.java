import ordersparser.consumer.Consumer;
import ordersparser.model.OrderIn;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.producer.ProducerType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 2;

    public static void main(String[] args) {

        if (args.length != 0) {
            Map<String, String> files = validateArgs(args);
        } else {
            System.out.println("Incorrect input");
        }

        BlockingQueue<OrderIn> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

//        runConsumers();
//        runProducers();
    }

    public static void runConsumers(BlockingQueue<OrderIn> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer()).start();
        }
    }

    public static void runProducers(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        for (Map.Entry<File, String> entry : files.entrySet()) {
//            метод получения типа Producer в зависимости от расширения файла
//            executorService.execute(new CsvProducer(entry.getKey(), queue, ProducerType.JSON));
//            executorService.execute(new JsonProducer(entry.getKey(), queue, ProducerType.CSV));
//            countDownLatch

        }
    }

    public static Map<String, String> validateArgs(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            File file = new File(path);
            String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
            if (!file.exists()) {
                System.out.println("File: " + path + " not found");
            } else {
                if (fileExtension.equals("csv") || fileExtension.equals("jsonl")) {
                    files.put(path, fileExtension);
                } else {
                    System.out.println("Unknown file extension: " + fileExtension);
                }
            }
        }

        return files;
    }
}
