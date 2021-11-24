package ordersparser.model;

public class OrderOut {

    private long id;
    private double amount;
    private Currency currency;
    private String comment;
    private String fileName;
    private long line;
    private String parseResult;

    public OrderOut() {
    }

    public OrderOut(Long id, Double amount, Currency currency, String comment, String fileName, Long line, String parseResult) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.parseResult = parseResult;
    }

    public long getId() {
        return id;
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

    public long getLine() {
        return line;
    }

    public String getResult() {
        return parseResult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
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

    public void setLine(Long line) {
        this.line = line;
    }

    public void setResult(String result) {
        this.parseResult = parseResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        OrderOut order = (OrderOut) obj;
        return id == order.id
                && amount == order.amount
                && line == order.line
                && currency == order.currency
                && comment.equals(order.comment)
                && fileName.equals(order.fileName)
                && parseResult.equals(order.parseResult);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = (int) (prime * result + amount);
        result = (int) (prime * result + line);
        result = prime * result + (currency == null ? 0 : currency.hashCode());
        result = prime * result + (comment == null ? 0 : comment.hashCode());
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (parseResult == null ? 0 : parseResult.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderOut: {id: %d, amount: %f, file name: %s, line: %d, result: %s}", id, amount, fileName, line, parseResult);
    }
}
