package ordersparser.mapper;

import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;

import java.math.BigDecimal;

public class Mapper {

    private OrderIn orderIn;

    public OrderOut convertInToOut(OrderIn orderIn) {
        OrderOut orderOut = new OrderOut();
        if (orderOut != null) {
            orderOut.setId(convertId());
            orderOut.setAmount(convertAmount());
            orderOut.setCurrency(orderIn.getCurrency());
            orderOut.setComment(orderIn.getComment());
            orderOut.setFileName();
            orderOut.setLine();
            // вывод результата конвектирования
            orderOut.setResult("OK");
        } else {
            orderOut.setResult("Wrong");
        }

        return orderOut;
    }

    public Long convertId() {
        return null;
    }

    public BigDecimal convertAmount() {
        return null;
    }

    public String buildErrorMessage() {
        return null;
    }

}
