package ordersparser.model;

import java.util.Objects;

public class OrderOut {

    private Integer id;
    private Float amount;
    private String currency;
    private String comment;
    private String fileName;
    private Integer line;
    private String result;

    public OrderOut(int id, float amount, String currency, String comment, String fileName, int line, String result) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
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

    public int getLine() {
        return line;
    }

    public String getResult() {
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(float amount) {
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

    public void setLine(int line) {
        this.line = line;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrderOut orderOut = (OrderOut) obj;
        return Objects.equals(id, orderOut.id)
                && Objects.equals(amount, orderOut.amount)
                && Objects.equals(line, orderOut.line)
                && currency.equals(orderOut.currency)
                && fileName.equals(orderOut.fileName)
                && result.equals(orderOut.result);

    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result1 = 1;
        result1 = prime * result1 + (id == null ? 0 : id.hashCode());
        result1 = prime * result1 + (amount == null ? 0 : amount.hashCode());
        result1 = prime * result1 + (line == null ? 0 : line.hashCode());
        result1 = prime * result1 + (currency == null ? 0 : currency.hashCode());
        result1 = prime * result1 + (fileName == null ? 0 : fileName.hashCode());
        result1 = prime * result1 + (result == null ? 0 : result.hashCode());
        return result1;
    }

    @Override
    public String toString() {
        return "{id: " + id + ", " +
                "amount: " + amount + ", " +
                "comment: " + comment + ", " +
                "filename: " + fileName + ", " +
                "line: " + line + ", " +
                "result: " + result + " }";
    }
}
