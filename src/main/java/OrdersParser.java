import ordersparser.consumer.Consumer;
import ordersparser.model.OrderIn;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.producer.ProducerType;
import ordersparser.validator.Validator;

import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 2;

    public static void main(String[] args) {

        if (args.length != 0) {
            new Validator().validate(args);
        } else {
            System.out.println("Incorrect args!");
        }

//        метод получения списка файлов
//        BlockingQueue<OrderIn> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

//        runConsumers();
//        runProducers();
    }

    public static void runConsumers(BlockingQueue<OrderIn> queue) {
        Thread consumerThread;
        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

    public static void runProducers(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
        for (Map.Entry<File, String> entry : files.entrySet()) {
//            получение типа Producer в зависимости от расширения файла
//            executorService.execute(new CsvProducer(entry.getKey(), queue, ProducerType.JSON));
//            executorService.execute(new JsonProducer(entry.getKey(), queue, ProducerType.CSV));
//            countDownLatch

        }
    }

}
