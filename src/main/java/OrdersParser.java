import ordersparser.consumer.Consumer;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.validator.Validator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 2;

    public static void main(String[] args) throws IOException {

        if (args.length != 0) {
            new Validator().validateArgs(args);
        } else {
            System.out.println("Incorrect args!");
        }

        Map<String, String> files = getFiles(args);
        BlockingQueue<OrderIn> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

//        runConsumers();
//        runProducers();
    }

    public static Map<String, String> getFiles(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            files.put(path, path.substring(path.lastIndexOf(".") + 1).toUpperCase());
        }

        return files;
    }

    public static void runConsumers(BlockingQueue<OrderIn> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

    public static void runProducers(Map<String, String> files, BlockingQueue<OrderIn> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(files.size());
        MessageType type = MessageType.REGULAR;
        for (Map.Entry<String, String> entry : files.entrySet()) {
            if (entry.getValue().equals("JSONL")) {
                executorService.execute(new JsonProducer(entry.getKey(), queue, type));
                countDownLatch.countDown();
            }
            if (entry.getValue().equals("CSV")) {
                executorService.execute(new CsvProducer(entry.getKey(), queue, type));
                countDownLatch.countDown();
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            type = MessageType.POISON_PILL;
        }
    }

//            получение типа Producer в зависимости от расширения файла
//            executorService.execute(new CsvProducer(entry.getKey(), queue, ProducerType.JSON));
//            executorService.execute(new JsonProducer(entry.getKey(), queue, ProducerType.CSV));
//            countDownLatch-- -> MessageType.REGULAR
//            countDownLatch-- -> на последнем файле - MessageType.POISON_PILL
}
