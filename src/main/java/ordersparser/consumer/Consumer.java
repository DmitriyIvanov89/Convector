package ordersparser.consumer;

import ordersparser.consoleprint.ConsolePrint;
import ordersparser.mapper.Mapper;
import ordersparser.model.Message;
import ordersparser.model.MessageType;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<Message> queue;
    private final Mapper mapper = new Mapper();
    private final ConsolePrint consolePrint = new ConsolePrint();

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = queue.take();
                if (message.getMessageType() == MessageType.POISON_PILL) {
                    return;
                }
                consolePrint.printResult(mapper.orderToOrderOut(message));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
