package ordersparser.producers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xpath.internal.operations.Or;
import ordersparser.model.Currency;
import ordersparser.model.Order;
import ordersparser.model.OrderWrong;
import ordersparser.model.ProducerType;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
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

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            long stringNumber = 0;
            String errors;
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            while ((line = reader.readLine()) != null) {
                OrderWrong orderWrong;
                stringNumber++;
                try {
                    Order order = toOrder(new Gson().fromJson(line, type));
                    
                    /*
                     * order.setValues
                     * if errors.length() = 0 -> queue.put(order), else queue.put(orderEntry)*/
                } catch (RuntimeException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    private Order toOrder(Map<String, String> values) {
        StringBuilder errors = new StringBuilder();
        Order order = new Order();

        try {
            if (values.get("orderId") != null) {
                order.setOrderId(Long.parseLong(values.get("orderId")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (values.get("amount") != null) {
                order.setAmount(Double.parseDouble(values.get("amount")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (values.get("currency") != null) {
                order.setCurrency(Currency.valueOf(values.get("currency")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (values.get("comment") != null) {
                order.setComment(values.get("comment"));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        if (errors.toString().isEmpty()) {
            return order;
        } else {
            throw new RuntimeException(errors.toString());
        }
    }

    public ProducerType getType() {
        return ProducerType.JSONL;
    }
}
