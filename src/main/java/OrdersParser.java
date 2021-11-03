import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import com.sun.org.apache.xpath.internal.operations.Or;
import ordersparser.consumer.Consumer;
import ordersparser.mapper.Mapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import ordersparser.producer.CsvProducer;
import ordersparser.producer.JsonProducer;
import ordersparser.validator.Validator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


public class OrdersParser {

    private static final int QUEUE_CAPACITY = 10;
    private static final int MAX_CONSUMERS_COUNT = 3;
    private static final int MAX_PRODUCERS_COUNT = 2;

    public static void main(String[] args) throws IOException {


        String path = ".\\src\\main\\resources\\orders.csv";
//        test(path, MessageType.REGULAR);
        List<OrderIn> list = test2(path, MessageType.REGULAR);
        System.out.println(list.size());
        System.out.println("end");

//        if (args.length != 0) {
//            new Validator().validateArgs(args);
//        } else {
//            System.out.println("Incorrect args!");
//        }

//        Map<String, String> files = getFiles(args);
//        BlockingQueue<OrderIn> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

//        runConsumers();
//        runProducers();
    }

//    public static List<OrderIn> test(String path, MessageType type) throws IOException {
//        List<OrderIn> messages = new ArrayList<OrderIn>();
//        OrderIn message;
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            message = objectMapper.readValue(reader, OrderIn.class);
//            message.setFileName(Paths.get(path).getFileName().toString());
//            message.setMessageType(type.getMessageType());
//            System.out.println("end method");
//        }
//        return messages;
//    }

    public static List<OrderIn> test2(String path, MessageType type) throws IOException {
        List<OrderIn> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
            int currLine = 0;
            for (CSVRecord record : records) {
                currLine++;
                OrderIn message = new OrderIn(record.get(0), record.get(1), record.get(2), record.get(3), Paths.get(path).getFileName().toString(), type.getMessageType(), String.valueOf(currLine));
                list.add(message);
            }
        }
        return list;
    }
    
//    public static Map<String, String> getFiles(String[] args) {
//        Map<String, String> files = new HashMap<>();
//        for (String path : args) {
//            files.put(path, path.substring(path.lastIndexOf(".") + 1).toUpperCase());
//        }
//
//        return files;
//    }
//
//    public static void runConsumers(BlockingQueue<OrderIn> queue) {
//        Thread consumerThread;
//        for (int i = 0; i < MAX_CONSUMERS_COUNT; i++) {
//            new Thread(new Consumer(queue)).start();
//        }
//    }
//
//    public static void runProducers(Map<String, String> files, BlockingQueue<OrderIn> queue) {
//        ExecutorService executorService = Executors.newFixedThreadPool(MAX_PRODUCERS_COUNT);
//        CountDownLatch countDownLatch = new CountDownLatch(files.size());
//        MessageType type = MessageType.REGULAR;
//        for (Map.Entry<String, String> entry : files.entrySet()) {
//            if (entry.getValue().equals("JSONL")) {
//                executorService.execute(new JsonProducer(entry.getKey(), queue, type));
//                countDownLatch.countDown();
//            }
//            if (entry.getValue().equals("CSV")) {
//                executorService.execute(new CsvProducer(entry.getKey(), queue, type));
//                countDownLatch.countDown();
//            }
//            try {
//                countDownLatch.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            type = MessageType.POISON_PILL;
//        }
////            получение типа Producer в зависимости от расширения файла
////            executorService.execute(new CsvProducer(entry.getKey(), queue, ProducerType.JSON));
////            executorService.execute(new JsonProducer(entry.getKey(), queue, ProducerType.CSV));
////            countDownLatch-- -> MessageType.REGULAR
////            countDownLatch-- -> на последнем файле - MessageType.POISON_PILL
//    }
}
