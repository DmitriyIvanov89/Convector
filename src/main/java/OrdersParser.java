import ordersparser.ArgsValidator;

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
//
//        BlockingQueue<Object> queue = new ArrayBlockingQueue<>();
//
//        runConsumers(maxConsumersCount);
//        runProducers(maxProducersCount, queue);

    }
}
