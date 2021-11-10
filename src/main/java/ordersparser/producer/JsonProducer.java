package ordersparser.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.io.*;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final CountDownLatch countDownLatch;

    public JsonProducer(String filePath, BlockingQueue<OrderIn> queue, CountDownLatch countDownLatch) {
        this.filePath = filePath;
        this.queue = queue;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        String line;
        int currLine = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                currLine++;
                OrderIn message = mapper.readValue(line, OrderIn.class);
                message.setFileName(Paths.get(filePath).getFileName().toString());
                message.setMessageType(MessageType.REGULAR.getMessageType());
                message.setLine(String.valueOf(currLine));
                queue.put(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProducerType getType() {
        return ProducerType.JSONL;
    }
}
