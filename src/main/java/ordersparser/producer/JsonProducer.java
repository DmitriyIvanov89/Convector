package ordersparser.producer;

import com.google.gson.Gson;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private final File file;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;


    public JsonProducer(File file, BlockingQueue<OrderIn> queue, MessageType type) {
        this.file = file;
        this.queue = queue;
        this.type = type;
    }

    /*библиотека с JsonObject -> передача параметров filename и line в
    * message OrderIn*/

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            queue.put(new Gson().fromJson(reader, OrderIn.class));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
