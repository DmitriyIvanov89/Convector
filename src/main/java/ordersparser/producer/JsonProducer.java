package ordersparser.producer;

import com.google.gson.Gson;
import ordersparser.model.OrderIn;

import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private Map<File, String> files;
    private final BlockingQueue<OrderIn> queue;
    private ProducerType type;

    public JsonProducer(BlockingQueue<OrderIn> queue, ProducerType type) {
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {

    }

    public ProducerType getType() {
        return type;
    }
}
