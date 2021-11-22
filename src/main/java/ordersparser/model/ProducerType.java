package ordersparser.model;

import java.util.HashMap;
import java.util.Map;

public enum ProducerType {
    CSV("CSV"),
    JSONL("JSONL");

    private final String type;
    private static final Map<String, ProducerType> ENUM_MAP = new HashMap<>();

    ProducerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
