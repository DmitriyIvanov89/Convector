package ordersparser.model;

public class OrderIn {

    private String orderId;
    private String amount;
    private String currency;
    private String comment;
    private String fileName;
    private String messageType;

    public OrderIn(String orderId, String amount, String currency, String comment, String fileName, String messageType) {
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.messageType = messageType;
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

    public String getFileName() {
        return fileName;
    }

    public String getMessageType() {
        return messageType;
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        OrderIn orderIn = (OrderIn) obj;
        return orderId.equals(orderIn.orderId)
                && amount.equals(orderIn.amount)
                && currency.equals(orderIn.currency)
                && comment.equals(orderIn.comment)
                && fileName.equals(orderIn.fileName)
                && messageType.equals(orderIn.messageType);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (orderId == null ? 0 : orderId.hashCode());
        result = prime * result + (amount == null ? 0 : amount.hashCode());
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (messageType == null ? 0 : messageType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("MessageIn: { orderId = %s, amount = %s, currency = %s, comment = %s, filename = %s, messageType = %s }", orderId, amount, currency, comment, fileName, messageType);
    }
}
