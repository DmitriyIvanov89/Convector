package ordersparser.consumer;

import ordersparser.consoleprint.ConsolePrint;
import ordersparser.mapper.Mapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<OrderIn> queue;
    private Mapper mapper;

    public Consumer(BlockingQueue<OrderIn> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (!queue.take().getMessageType().equals(MessageType.POISON_PILL.getMessageType())) {
                new ConsolePrint().printResult(new Mapper().convertInToOut(queue.take()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
