package ordersparser.model;

import java.util.Objects;

public class OrderWrong {

    private long lineNumber;
    private String fileName;
    private String error;
//    private Order playLoad;

    public OrderWrong() {
    }

    public OrderWrong(String fileName,long lineNumber, String error) {
        this.lineNumber = lineNumber;
        this.fileName = fileName;
        this.error = error;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public String getError() {
        return error;
    }

//    public Order getPlayLoad() {
//        return playLoad;
//    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderWrong that = (OrderWrong) o;
        return lineNumber == that.lineNumber && Objects.equals(fileName, that.fileName) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (int) (prime * result + lineNumber);
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (error == null ? 0 : error.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderEntry: {FileName: %s, LineNumber: %d, error: %s}", fileName, lineNumber, error);
    }
}
