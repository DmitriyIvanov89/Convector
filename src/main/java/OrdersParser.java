import ordersparser.Validator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrdersParser {

    private static int queueCapacity;
    private static int maxConsumersCount;
    private static int maxProducersCount;

    public static void main(String[] args) {

        if (args.length != 0) {

        } else {
            System.out.println("Incorrect parameter input");
        }

//        try {
//            validateArgs(pathToFile);
//        } catch (convector.Exception ex) {
//            ex.printStackTrace();
//        }
//
//        BlockingQueue<Object> queue = new ArrayBlockingQueue<>();
//
//        runConsumers(maxConsumersCount);
//        runProducers(maxProducersCount, queue);

    }
}
