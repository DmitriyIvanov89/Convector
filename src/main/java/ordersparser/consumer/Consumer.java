package ordersparser.consumer;

import ordersparser.consoleprint.ConsolePrint;
import ordersparser.mapper.Mapper;
import ordersparser.model.MessageType;
import ordersparser.model.Order;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<Order> queue;

    public Consumer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

//        try {
//            Order message;
//            while (!(message = queue.take()).getMessageType().equals(MessageType.POISON_PILL.toString())) {
//                new ConsolePrint().printResult(new Mapper().convertInToOut(message));
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
