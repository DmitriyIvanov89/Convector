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
import java.util.concurrent.CountDownLatch;

public class CsvProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;
    private final CountDownLatch count;

    public CsvProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type, CountDownLatch count) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
        this.count = count;
    }

    @Override
    public void run() {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
            int currLine = 0;
            for (CSVRecord record : records) {
                currLine++;
                OrderIn message = new OrderIn(record.get(0), record.get(1), record.get(2), record.get(3), Paths.get(filePath).getFileName().toString(), type.getMessageType(), String.valueOf(currLine));
                queue.put(message);
                count.countDown();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
