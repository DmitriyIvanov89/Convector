package ordersparser.producer;

import com.google.gson.Gson;
import ordersparser.model.OrderIn;

import java.io.*;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private final Map<File, String> files;
    private final BlockingQueue<OrderIn> queue;
    private FileExtension type;

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

    public FileExtension getType() {
        return FileExtension.JSON;
    }
}
