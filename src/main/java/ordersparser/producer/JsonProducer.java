package ordersparser.producer;

import com.google.gson.Gson;
import ordersparser.model.OrderIn;

import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

<<<<<<< HEAD
    private String path;
    private BlockingQueue<Object> queue;
    private final ProducerType type = ProducerType.JSON_PRODUCER;
=======
    private final Map<File, String> files;
    private final BlockingQueue<OrderIn> queue;
    private FileExtension type;
>>>>>>> 71cdb02cf1d0ce85e9bbec7fdf0f55e2d4448423

    public JsonProducer(Map<File, String> files, BlockingQueue<OrderIn> queue) {
        this.files = files;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (Map.Entry<File, String> entry : files.entrySet()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(entry.getKey()))) {
                queue.put(new Gson().fromJson(reader, OrderIn.class));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

<<<<<<< HEAD
    public ProducerType getType() {
        return type;
=======
    public FileExtension getType() {
        return FileExtension.JSON;
>>>>>>> 71cdb02cf1d0ce85e9bbec7fdf0f55e2d4448423
    }
}
