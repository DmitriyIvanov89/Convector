import ordersparser.consumer.Consumer;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.validator.ArgsValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
//        runConsumers();
//        runProducers();
    }

    public static List<File> getFiles(String[] args) {
        List<File> filesList = new ArrayList<>();
        for (String path : args) {
            File file = new File(path);
            filesList.add(file);
        }
        return filesList;
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
            // метод получения типа Producer в зависимости от расширения файла
            executorService.execute(new CsvProducer());
            executorService.execute(new JsonProducer());
            // countDownLatch
        }
    }
}
