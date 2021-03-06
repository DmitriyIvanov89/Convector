package ordersparser.sevice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ordersparser.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<Message> queue;
    private final CountDownLatch countDownLatch;

    public JsonProducer(String filePath, BlockingQueue<Message> queue, CountDownLatch countDownLatch) {
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
                    order.setFilename(Paths.get(filePath).getFileName().toString());
                    order.setLineNumber(lineNumber);
                    queue.put(new Message(MessageType.REGULAR, order));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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

        try {
            if (orderValues.get("orderId") != null) {
                order.setOrderId(Long.parseLong(orderValues.get("orderId")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field orderId");
        }

        try {
            if (orderValues.get("amount") != null) {
                order.setAmount(Double.parseDouble(orderValues.get("amount")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field amount");
        }

        try {
            if (orderValues.get("currency") != null) {
                order.setCurrency(Currency.valueOf(orderValues.get("currency")));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field currency");
        }

        try {
            if (orderValues.get("comment") != null) {
                order.setComment(orderValues.get("comment"));
            }
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field comment");
        }

        order.setError(errors.toString());
        return order;
    }

    public ProducerType getType() {
        return ProducerType.JSONL;
    }
}
