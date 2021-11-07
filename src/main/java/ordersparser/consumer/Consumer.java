package ordersparser.consumer;

import ordersparser.consoleprint.ConsolePrint;
import ordersparser.mapper.Mapper;
import ordersparser.model.MessageType;
import ordersparser.model.OrderIn;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<OrderIn> queue;

    public Consumer(BlockingQueue<OrderIn> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                new ConsolePrint().printResult(new Mapper().convertInToOut(queue.take()));
                if (queue.take().getMessageType().equals(MessageType.POISON_PILL.getMessageType())) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
