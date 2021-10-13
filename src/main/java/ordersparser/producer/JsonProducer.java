package ordersparser.producer;

import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private String path;
    private BlockingQueue<Object> queue;
    private ProducerType type;

    public JsonProducer(String path, BlockingQueue<Object> queue) {
        this.path = path;
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
