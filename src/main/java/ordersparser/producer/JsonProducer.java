package ordersparser.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CountDownLatch count;


    public JsonProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type, CountDownLatch count) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
        this.count = count;
    }

    @Override
    public void run() {
        String line;
        int currLine = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            while ((line = reader.readLine()) != null) {
                currLine++;
                OrderIn message = objectMapper.readValue(line, OrderIn.class);
                message.setFileName(Paths.get(filePath).getFileName().toString());
                message.setMessageType(type.getMessageType());
                message.setLine(String.valueOf(currLine));
                queue.put(message);
                count.countDown();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
