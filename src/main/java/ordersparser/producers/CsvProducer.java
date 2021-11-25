package ordersparser.producers;

import ordersparser.model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class CsvProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<Order> queue;
    private final CountDownLatch countDownLatch;

    public CsvProducer(String filePath, BlockingQueue<Order> queue, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            CSVParser records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);
//            int currLine = 0;
//            for (CSVRecord record : records) {
//                currLine++;
//
//                queue.put();
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

        countDownLatch.countDown();
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
