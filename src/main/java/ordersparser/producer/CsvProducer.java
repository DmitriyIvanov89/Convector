package ordersparser.producer;

import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private String path;
    private BlockingQueue<Object> queue;
    private final ProducerType type = ProducerType.CSV_PRODUCER;

    public CsvProducer(String path, BlockingQueue<Object> queue) {
        this.path = path;
        this.queue = queue;
    }

    @Override
    public void run() {
        // Считываение данных из csv файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

    public ProducerType getType() {
        return type;
    }
}
