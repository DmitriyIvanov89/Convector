package ordersparser;

public class Consumer implements Runnable {

    @Override
    public void run() {
        // Обработка сообщения из очереди(BlockingQueue<Model> queue), конвектирование в формат json
        System.out.println("Start consumer");
    }
}
