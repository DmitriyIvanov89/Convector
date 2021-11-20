package ordersparser.model;

public class OrderIn {

    private long orderId;
    private double amount;
    private Currency currency;
    private String comment;
    private String fileName;
    private long lineNumber;
    private String error;

    public OrderIn() {
    }

    public OrderIn(long orderId, double amount, Currency currency, String comment, String fileName, long lineNumber, String error) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.error = error;
    }

    public long getOrderId() {
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

    public String getFileName() {
        return fileName;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public String getError() {
        return error;
    }

    public void setOrderId(long orderId) {
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        OrderIn order = (OrderIn) obj;
        return orderId == order.orderId
                && amount == order.amount
                && lineNumber == order.lineNumber
                && currency == order.currency
                && comment.equals(order.comment)
                && fileName.equals(order.fileName)
                && error.equals(order.error);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (int) (prime * result + orderId);
        result = (int) (prime * result + amount);
        result = (int) (prime * result + lineNumber);
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (error == null ? 0 : error.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderIn: {OrderId: %d, amount: %f, currency: %s, comment: %s, filename: %s,lineNumber: %d error: %s}", orderId, amount, currency, comment, fileName, lineNumber, error);
    }
}
