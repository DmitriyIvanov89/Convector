package ordersparser.consumer;

import ordersparser.mapper.Mapper;
import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;
import ordersparser.producer.ProducerType;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<OrderIn> queue;
    private Mapper mapper;
    private OrderIn orderIn;

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
//        try {
//            while (queue.take().equals(POISON_PILL)) {
//                new Mapper().convertAmount(queue.take(),);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
