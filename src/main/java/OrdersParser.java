
import ordersparser.consumer.Consumer;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.validator.ArgsValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdersParser {

    private static final int QUEUE_CAPACITY = 1000;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 2;

    public static void main(String[] args) {

        if (args.length != 0) {
            new ArgsValidator().validateArgs(args);
        } else {
            System.out.println("Incorrect input");
        }

        Map<String, String> files = getFiles(args);

//        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
//        runConsumers();
//        runProducers();
    }

    public static Map<String, String> getFiles(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            int dotIndex = path.lastIndexOf(".");
            String extension = path.substring(dotIndex + 1);
            files.put(path, extension);
        }

        return files;
    }

    public static void runConsumers(BlockingQueue<Object> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer()).start();
        }
    }

    public static void runProducers(File[] filesList, BlockingQueue<Object> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        for (File file : filesList) {
//             метод получения типа Producer в зависимости от расширения файла
//            executorService.execute(new CsvProducer(file, queue));
//            executorService.execute(new JsonProducer(file, queue));
//             countDownLatch
        }
    }
}
