package ordersparser.producer;

//import com.google.gson.Gson;

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
    private final ObjectMapper objectMapper = new ObjectMapper();


    public JsonProducer(String filePath, BlockingQueue<OrderIn> queue, MessageType type) {
        this.filePath = filePath;
        this.queue = queue;
        this.type = type;
    }

    @Override
    public void run() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            OrderIn message = objectMapper.readValue(reader, OrderIn.class);
            message.setFileName(Paths.get(filePath).getFileName().toString());
            message.setMessageType(type.getMessageType());
            queue.put(message);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageType getType() {
        return type;
    }
}
