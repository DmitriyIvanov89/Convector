import ordersparser.ArgsValidator;
import ordersparser.Consumer;
import ordersparser.Model;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrdersParser {

    private static final int queueCapacity = 1000;
    private static final int maxConsumersCount = 8;
    private static final int maxProducersCount = 3;

    public static void main(String[] args) {

        if (args.length != 0) {
            new ArgsValidator().validateArgs(args);
        } else {
            System.out.println("Incorrect input");
        }

        BlockingQueue<Model> queue = new ArrayBlockingQueue<>(queueCapacity);
//        runConsumers();
//        runProducers();
    }

    public static void runConsumers(BlockingQueue<Model> queue) {
        Thread consumerThread;
        for (int i = 0; i < maxConsumersCount; i++) {
            consumerThread = new Thread(new Consumer());
            consumerThread.start();
        }
    }

    public static void runProducers(File[] files, BlockingQueue<Model> queue) {

    }
}
