package ordersparser.model;

import java.util.Objects;

public class OrderIn {

    private String orderId;
    private String amount;
    private String currency;
    private String comment;

    public OrderIn(String orderId, String amount, String currency, String comment) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        OrderIn orderIn = (OrderIn) obj;
        return Objects.equals(orderId, ((OrderIn) obj).orderId) && Objects.equals(amount, ((OrderIn) obj).amount) &&
                Objects.equals(currency, ((OrderIn) obj).currency) &&
                comment.equals(((OrderIn) obj).comment);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (orderId == null ? 0 : orderId.hashCode());
        result = prime * result + (amount == null ? 0 : amount.hashCode());
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("OrderIn: {");
        stringBuilder.append("orderId = ").append(orderId);
        stringBuilder.append("amount = ").append(amount);
        stringBuilder.append("currency = ").append(currency);
        stringBuilder.append("comment = ").append(comment);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
