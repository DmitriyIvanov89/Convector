package ordersparser.producer;

import com.google.gson.Gson;
import ordersparser.model.OrderIn;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private final File file;
    private final BlockingQueue<OrderIn> queue;
    private final ProducerType type;

    public JsonProducer(File file, BlockingQueue<OrderIn> queue, ProducerType type) {
        this.file = file;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            queue.put(new Gson().fromJson(reader, OrderIn.class));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProducerType getType() {
        return type;
    }
}
