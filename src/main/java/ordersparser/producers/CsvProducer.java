package ordersparser.producers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ordersparser.model.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class CsvProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<Message> queue;
    private final CountDownLatch countDownLatch;

    public CsvProducer(String filePath, BlockingQueue<Message> queue, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] orderValues;
            int lineNumber = 0;
            while ((orderValues = csvReader.readNext()) != null) {
                lineNumber++;
                Order order = toOrder(orderValues);
                if (order.getError().length() > 0) {
                    queue.put(new Message(MessageType.REGULAR, new Order(Paths.get(filePath).getFileName().toString(), lineNumber, order.getError())));
                } else {
                    queue.put(new Message(MessageType.REGULAR, order));
                }
            }
            countDownLatch.countDown();

        } catch (IOException | CsvValidationException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Order toOrder(String[] orderValues) {
        StringBuilder errors = new StringBuilder();
        Order order = new Order();

        try {
            order.setOrderId(Long.parseLong(orderValues[0]));
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            order.setAmount(Double.parseDouble(orderValues[1]));
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            order.setCurrency(Currency.valueOf(orderValues[2]));
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append(e);
        }

        try {
            order.setComment(orderValues[3]);
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
        return ProducerType.CSV;
    }
}
