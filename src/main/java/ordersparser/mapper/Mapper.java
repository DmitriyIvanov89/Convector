package ordersparser.mapper;

import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;


public class Mapper {

    public OrderOut convertInToOut(OrderIn message) {
        OrderOut messageOut = new OrderOut();
        if (message != null) {
            messageOut.setId(Integer.parseInt(message.getOrderId()));
            messageOut.setAmount(Float.parseFloat(message.getAmount()));
            messageOut.setCurrency(message.getCurrency());
            messageOut.setComment(message.getComment());
            messageOut.setFileName(message.getFileName());
            messageOut.setLine(Integer.parseInt(message.getLine()));
            messageOut.setResult("OK");
        } else {
            messageOut.setResult("Wrong message");
        }

        return messageOut;
    }

    private String buildErrorMessage(String param, String value) {
        return null;
    }

}
