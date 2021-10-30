package ordersparser.producer;

//import com.google.gson.Gson;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;


    public JsonProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
    }

    /*библиотека с JsonObject -> передача параметров filename и line в
     * message OrderIn*/

    @Override
    public void run() {
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
//            OrderIn message = new OrderIn();
//            queue.put(new Gson().fromJson(reader, OrderIn.class));
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public MessageType getType() {
        return type;
    }
}
