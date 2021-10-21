package ordersparser.consumer;

import ordersparser.mapper.Mapper;
import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private final BlockingQueue<OrderIn> queue;
    private Mapper mapper;

    public Consumer(BlockingQueue<OrderIn> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // берет сообщение из очереди(OrderIn), создает mapper с методом(конвертация сообщения в OrderOut),
        // возвращает обьект OrderOut(передает его в ConsolePrint)

    }
}
