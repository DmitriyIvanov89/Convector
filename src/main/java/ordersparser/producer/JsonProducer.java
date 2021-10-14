package ordersparser.producer;

import ordersparser.model.OrderIn;

import java.io.File;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private Map<File,String> files;
    private final BlockingQueue<OrderIn> queue;
    private ProducerType type;

    public JsonProducer(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        this.files = files;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Считываение данных из json файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

    public ProducerType getType() {
        return ProducerType.JSON_PRODUCER;
    }
}
