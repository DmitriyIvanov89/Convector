package ordersparser.mapper;

import ordersparser.model.Message;
import ordersparser.model.OrderOut;


public class Mapper {

    public OrderOut orderToOrderOut(Message message) {
        OrderOut orderOut = new OrderOut();

        orderOut.setFileName(message.getOrder().getFilename());
        orderOut.setLine(message.getOrder().getLineNumber());
        orderOut.setResult(message.getOrder().getError() == null ? "OK" : message.getOrder().getError());

        if (message.getOrder().getError() == null && message.getOrder() != null) {
            orderOut.setId(message.getOrder().getOrderId());
            orderOut.setAmount(message.getOrder().getAmount());
            orderOut.setComment(message.getOrder().getComment());
        }

        return orderOut;
    }
}
