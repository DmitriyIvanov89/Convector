package ordersparser.mapper;

import ordersparser.model.Message;
import ordersparser.model.OrderOut;


public class Mapper {

    public OrderOut orderToOrderOut(Message message) {
        OrderOut orderOut = new OrderOut();
        orderOut.setId(message.getOrder().getOrderId());
        orderOut.setAmount(message.getOrder().getAmount());
        orderOut.setComment(message.getOrder().getComment());
        orderOut.setFileName(message.getOrder().getFilename());
        orderOut.setLine(message.getOrder().getLineNumber());

        if (message.getOrder().getError().isEmpty()) {
            orderOut.setResult("OK");
        } else {
            orderOut.setResult(message.getOrder().getError());
        }

        return orderOut;
    }
}
