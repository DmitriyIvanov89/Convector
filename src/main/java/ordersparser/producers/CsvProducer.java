package ordersparser.producers;

import ordersparser.model.MessageType;
import ordersparser.model.Order;
import ordersparser.model.ProducerType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class CsvProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<Order> queue;
    private final CountDownLatch countDownLatch;

    public CsvProducer(String filePath, BlockingQueue<Order> queue, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
//            int currLine = 0;
//            for (CSVRecord record : records) {
//                currLine++;
//                Order message = new Order(record.get(0), record.get(1), record.get(2), record.get(3), Paths.get(filePath).getFileName().toString(), MessageType.REGULAR.getMessageType(), String.valueOf(currLine));
//                queue.put(message);
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

//        countDownLatch.countDown();
    }

    public ProducerType getType() {
        return ProducerType.CSV;
    }
}
