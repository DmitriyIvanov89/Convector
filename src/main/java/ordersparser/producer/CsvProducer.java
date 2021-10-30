package ordersparser.producer;

import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;

    public CsvProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
//            for (CSVRecord record : records) {
//                queue.put(new OrderIn(record.get(0),record.get(1),record.get(2),record.get(3),record.get(4)));
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public MessageType getType() {
        return type;
    }
}
