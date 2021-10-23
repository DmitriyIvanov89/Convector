package ordersparser.mapper;

import ordersparser.model.OrderIn;
import ordersparser.model.OrderOut;

import java.math.BigDecimal;

public class Mapper {

    /*
     * конвертация модели OrderIn в OrderOut
     * построение строки с ошибкой
     * */

    private OrderIn orderIn;

    public OrderOut convertInToOut(OrderIn orderIn, String fileName, Long line, String errorMsg) {
        OrderOut orderOut = new OrderOut();
        if (orderOut != null) {
            orderOut.setId(convertId());
            orderOut.setAmount(convertAmount());
            orderOut.setCurrency(orderIn.getCurrency());
            orderOut.setComment(orderIn.getComment());
            orderOut.setFileName(fileName);
            orderOut.setLine(line);
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
