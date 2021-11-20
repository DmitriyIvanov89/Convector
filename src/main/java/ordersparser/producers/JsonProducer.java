package ordersparser.producers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ordersparser.model.Order;
import ordersparser.model.ProducerType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<Order> queue;
    private final CountDownLatch countDownLatch;

    public JsonProducer(String filePath, BlockingQueue<Order> queue, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        String line;
        long stringNumber = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            while ((line = reader.readLine()) != null) {
                stringNumber++;
                try {
                    Order order = new Gson().fromJson(line, Order.class);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ProducerType getType() {
        return ProducerType.JSONL;
    }
}
