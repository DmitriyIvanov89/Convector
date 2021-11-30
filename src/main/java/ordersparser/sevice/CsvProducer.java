package ordersparser.sevice;

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
                order.setFilename(Paths.get(filePath).getFileName().toString());
                order.setLineNumber(lineNumber);
                queue.put(new Message(MessageType.REGULAR, order));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
            errors.append("Invalid value in field orderId");
        }

        try {
            order.setAmount(Double.parseDouble(orderValues[1]));
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field amount");
        }

        try {
            order.setCurrency(Currency.valueOf(orderValues[2]));
        } catch (RuntimeException e) {
            if (errors.length() > 0) {
                errors.append(" , ");
            }
            errors.append("Invalid value in field currency");
        }

        try {
            order.setComment(orderValues[3]);
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
        return ProducerType.CSV;
    }
}
