package ordersparser.producers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
            long lineNumber = 0;
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    Order order = toOrder(new Gson().fromJson(line, type));
                    if (order.getError().length() > 0) {
                        queue.put(order);
                    } else {
                        queue.put(new Order(Paths.get(filePath).getFileName().toString(), lineNumber, order.getError()));
                    }
                } catch (RuntimeException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    private Order toOrder(Map<String, String> orderValues) {
        StringBuilder errors = new StringBuilder();
        Order order = new Order();
        OrderWrong orderWrong = new OrderWrong();

        try {
            if (orderValues.get("orderId") != null) {
                order.setOrderId(Long.parseLong(orderValues.get("orderId")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (orderValues.get("amount") != null) {
                order.setAmount(Double.parseDouble(orderValues.get("amount")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (orderValues.get("currency") != null) {
                order.setCurrency(Currency.valueOf(orderValues.get("currency")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            if (orderValues.get("comment") != null) {
                order.setComment(orderValues.get("comment"));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        order.setError(errors.toString());
        return order;
    }

    public ProducerType getType() {
        return ProducerType.JSONL;
    }
}
