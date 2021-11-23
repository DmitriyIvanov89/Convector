
import com.google.gson.reflect.TypeToken;
import ordersparser.consumer.Consumer;
import ordersparser.model.Order;
import ordersparser.producers.CsvProducer;
import ordersparser.producers.JsonProducer;
import ordersparser.validator.Validator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_PRODUCERS_COUNT = 2;
    private static final int MAX_CONSUMERS_COUNT = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws InterruptedException {

        if (new Validator().validateArgs(args)) {
            BlockingDeque<Order> messageQueue = new LinkedBlockingDeque<>(QUEUE_CAPACITY);
            runConsumers(messageQueue);
            Map<String, String> files = getFiles(args);
            runProducers(files, messageQueue);
            System.out.println("test");
        }
    }

    private static Map<String, String> getFiles(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            files.put(path, path.substring(path.lastIndexOf(".") + 1).toUpperCase());
        }
        return files;
    }

    private static void runConsumers(BlockingQueue<Order> queue) {
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

    private static void runProducers(Map<String, String> files, BlockingQueue<Order> queue) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(files.size());
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        for (Map.Entry<String, String> entry : files.entrySet()) {
            if (entry.getValue().equals("JSONL")) {
                executorService.execute(new JsonProducer(entry.getKey(), queue, countDownLatch));
            }
            if (entry.getValue().equals("CSV")) {
                executorService.execute(new CsvProducer(entry.getKey(), queue, countDownLatch));
            }
        }

        countDownLatch.await();
        executorService.shutdown();
//        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
//            queue.put(new OrderIn(MessageType.POISON_PILL.toString()));
//        }
    }
}
