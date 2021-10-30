package ordersparser.consumer;

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
    /*
     * берет сообщение из очереди(OrderIn), создает mapper с методом(конвертация сообщения в OrderOut),
     * пока не получит poisenpill сообщение
     * возвращает обьект OrderOut(передает его в ConsolePrint)
     * */

    @Override
    public void run() {

        try {
            while (!queue.take().getMessageType().equals(MessageType.POISON_PILL.getMessageType())) {
                new Mapper().convertInToOut(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
