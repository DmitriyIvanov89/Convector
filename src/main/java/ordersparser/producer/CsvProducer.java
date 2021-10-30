package ordersparser.producer;

import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                OrderIn message = new OrderIn(record.get(0), record.get(1), record.get(2), record.get(3), Paths.get(filePath).getFileName().toString(), type.getMessageType());
                queue.put(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
