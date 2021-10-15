package ordersparser.producer;

import ordersparser.model.OrderIn;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private final Map<File, String> files;
    private final BlockingQueue<OrderIn> queue;
    private FileExtension type;

    public CsvProducer(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        this.files = files;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Считываение данных из csv файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

    public FileExtension getType() {
        return FileExtension.CSV;
    }
}
