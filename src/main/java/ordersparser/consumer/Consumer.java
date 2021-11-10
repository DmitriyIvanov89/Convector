package ordersparser.consumer;

import ordersparser.consoleprint.ConsolePrint;
import ordersparser.mapper.Mapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<OrderIn> queue;

    public Consumer(BlockingQueue<OrderIn> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            OrderIn message = null;
            while (!(message = queue.take()).getMessageType().equals(MessageType.POISON_PILL.toString())) {
                new ConsolePrint().printResult(new Mapper().convertInToOut(message));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
