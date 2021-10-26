package ordersparser.producer;

import ordersparser.model.OrderIn;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private final File file;
    private final BlockingQueue<OrderIn> queue;
    private final ProducerType type;

    public CsvProducer(File file, BlockingQueue<OrderIn> queue, ProducerType type) {
        this.file = file;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(reader);
            for (CSVRecord record : records) {
                queue.put(new OrderIn(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4)));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * добавление в очередь poison_pill
         * */
    }

    public ProducerType getType() {
        return type;
    }
}
