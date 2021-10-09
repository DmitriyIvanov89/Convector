import ordersparser.ArgsValidator;
import ordersparser.Consumer;
import ordersparser.Model;
import ordersparser.Producer;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdersParser {

    private static final int QUEUE_CAPACITY = 1000;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 3;

    public static void main(String[] args) {

        if (args.length != 0) {
            new ArgsValidator().validateArgs(args);
        } else {
            System.out.println("Incorrect input");
        }

        BlockingQueue<Model> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
//        runConsumers();
//        runProducers();
    }

    public static void runConsumers(BlockingQueue<Model> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer()).start();
        }
    }

    public static void runProducers(File[] files, BlockingQueue<Model> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        for (File file : files) {
            executorService.execute(new Producer());
        }
    }
}
