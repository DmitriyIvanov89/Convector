import ordersparser.ArgsValidator;

public class OrdersParser {

    private static int queueCapacity;
    private static int maxConsumersCount;
    private static int maxProducersCount;

    public static void main(String[] args) {

        String[] arr = {".\\src\\main\\resources\\orders44.md",".\\src\\main\\resources\\orders.csv",".\\src\\main\\resources\\orders.jsonl",".\\src\\main\\resources\\orders1.csv"};

        if (arr.length != 0) {
            new ArgsValidator().validateArgs(arr);
        } else {
            System.out.println("Incorrect input");
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
