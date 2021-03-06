package ordersparser.model;

public class Order {

    private long orderId;
    private double amount;
    private Currency currency;
    private String comment;
    private String filename;
    private long lineNumber;
    private String error;

    public Order() {
    }

    public Order(Long orderId, double amount, Currency currency, String comment) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public Order(String filename, long lineNumber, String error) {
        this.filename = filename;
        this.lineNumber = lineNumber;
        this.error = error;
    }

    public Order(long orderId, double amount, Currency currency, String comment, String filename, long lineNumber, String error) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.filename = filename;
        this.lineNumber = lineNumber;
        this.error = error;
    }

    public Long getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public String getFilename() {
        return filename;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public String getError() {
        return error;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Order order = (Order) obj;
        return orderId == order.orderId
                && amount == order.amount
                && currency == order.currency
                && comment.equals(order.comment);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (int) (prime * result + orderId);
        result = (int) (prime * result + amount);
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("Order: { orderId = %s, amount = %s, currency = %s, comment = %s}", orderId, amount, currency, comment);
    }
}
