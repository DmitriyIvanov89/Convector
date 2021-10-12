package ordersparser.producer;

import java.util.concurrent.BlockingQueue;

public class CsvProducer implements Runnable {

    private String path;
    private BlockingQueue<Object> queue;
    private ProducerType type;

    @Override
    public void run() {
        // Считываение данных из csv файла построчно и передача в очередь(BlockingQueue<Model> queue)

    }

    public ProducerType getType() {
        return ProducerType.CSV_PRODUCER;
    }
}
