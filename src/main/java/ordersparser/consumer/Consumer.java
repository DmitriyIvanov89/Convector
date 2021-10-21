package ordersparser.consumer;

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

    }
}
