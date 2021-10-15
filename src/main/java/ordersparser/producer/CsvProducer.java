package ordersparser.producer;

import ordersparser.model.OrderIn;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

<<<<<<< HEAD
    private String path;
    private BlockingQueue<Object> queue;
    private final ProducerType type = ProducerType.CSV_PRODUCER;
=======
    private final Map<File, String> files;
    private final BlockingQueue<OrderIn> queue;
    private FileExtension type;
>>>>>>> 71cdb02cf1d0ce85e9bbec7fdf0f55e2d4448423

    public CsvProducer(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        this.files = files;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Считываение данных из csv файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

<<<<<<< HEAD
    public ProducerType getType() {
        return type;
=======
    public FileExtension getType() {
        return FileExtension.CSV;
>>>>>>> 71cdb02cf1d0ce85e9bbec7fdf0f55e2d4448423
    }
}
