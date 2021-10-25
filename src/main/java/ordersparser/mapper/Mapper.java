package ordersparser.mapper;

import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;

import java.math.BigDecimal;

public class Mapper {

    private OrderIn orderIn;

    public OrderOut convertInToOut(OrderIn orderIn) {
        OrderOut orderOut = new OrderOut();
        if (orderOut != null) {
            orderOut.setId(Integer.parseInt(orderIn.getOrderId()));
            orderOut.setAmount(Float.parseFloat(orderIn.getAmount()));
            orderOut.setCurrency(orderIn.getCurrency());
            orderOut.setComment(orderIn.getComment());
//            orderOut.setFileName();
//            orderOut.setLine();
            // вывод результата конвектирования(есть ли ошибки)
            orderOut.setResult("OK");
        } else {
            orderOut.setResult("Wrong");
        }

        return orderOut;
    }

    public String buildErrorMessage() {
        return null;
    }

}
