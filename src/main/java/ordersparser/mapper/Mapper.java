package ordersparser.mapper;

import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;


public class Mapper {

    public OrderOut convertInToOut(OrderIn message) {

        OrderOut messageOut = new OrderOut();

        messageOut.setId(Integer.parseInt(message.getOrderId()));
        messageOut.setAmount(Float.parseFloat(message.getAmount()));
        messageOut.setCurrency(message.getCurrency());
        messageOut.setComment(message.getComment());
        messageOut.setFileName(message.getFileName());
        messageOut.setLine(Integer.parseInt(message.getLine()));
        messageOut.setResult("OK");

        return messageOut;
    }

}
