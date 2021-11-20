package ordersparser.model;

import java.util.Objects;

public class OrderEntry {

    private long lineNumber;
    private String fileName;
    private String error;
    private Order playLoad;

    public OrderEntry() {
    }

    public OrderEntry(long lineNumber, String fileName, String error, Order playLoad) {
        this.lineNumber = lineNumber;
        this.fileName = fileName;
        this.error = error;
        this.playLoad = playLoad;
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

    public Order getPlayLoad() {
        return playLoad;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPlayLoad(Order playLoad) {
        this.playLoad = playLoad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntry that = (OrderEntry) o;
        return lineNumber == that.lineNumber && Objects.equals(fileName, that.fileName) && Objects.equals(error, that.error) && Objects.equals(playLoad, that.playLoad);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (int) (prime * result + lineNumber);
        result = prime * result + (fileName == null ? 0 : fileName.hashCode());
        result = prime * result + (error == null ? 0 : error.hashCode());
        result = prime * result + (playLoad == null ? 0 : playLoad.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderEntry: {FileName: %s, LineNumber: %d, error: %s, Order: %s}", fileName, lineNumber, error, playLoad);
    }
}
