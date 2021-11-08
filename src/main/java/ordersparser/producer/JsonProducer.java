package ordersparser.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    private final String filePath;
    private final BlockingQueue<OrderIn> queue;
    private final MessageType type;

    public JsonProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {

        String line;
        int currLine = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ObjectMapper objectMapper = new ObjectMapper();
            while ((line = reader.readLine()) != null) {
                currLine++;
                OrderIn message = objectMapper.readValue(line, OrderIn.class);
                message.setFileName(Paths.get(filePath).getFileName().toString());
                message.setMessageType(type.getMessageType());
                message.setLine(String.valueOf(currLine));
                queue.put(message);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
