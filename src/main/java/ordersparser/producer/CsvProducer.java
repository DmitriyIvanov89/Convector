package ordersparser.producer;

import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private final File file;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;

    public CsvProducer(File file, BlockingQueue<OrderIn> queue, MessageType type) {
        this.file = file;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
            for (CSVRecord record : records) {
                queue.put(new OrderIn());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
