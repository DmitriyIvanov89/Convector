package ordersparser.producer;

import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private String path;
    private BlockingQueue<Object> queue;
    private final ProducerType type = ProducerType.JSON_PRODUCER;

    public JsonProducer(String path, BlockingQueue<Object> queue) {
        this.path = path;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Считываение данных из json файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

    public ProducerType getType() {
        return type;
    }
}
