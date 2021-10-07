import ordersparser.ArgsValidator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrdersParser {

    private static int queueCapacity;
    private static int maxConsumersCount;
    private static int maxProducersCount;

    public static void main(String[] args) {

        if (args.length != 0) {
            new ArgsValidator().validateArgs(args);
        } else {
            System.out.println("Incorrect input");
        }

//        BlockingQueue<> queue = new ArrayBlockingQueue<>();
//        runConsumers(maxConsumersCount);
//        runProducers(maxProducersCount, queue);
    }
}
