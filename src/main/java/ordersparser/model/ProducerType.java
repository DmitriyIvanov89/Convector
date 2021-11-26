package ordersparser.model;

public enum ProducerType {
    CSV("CSV"),
    JSONL("JSONL");

    private final String type;

    ProducerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
