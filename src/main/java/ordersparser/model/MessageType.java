package ordersparser.model;

public enum MessageType {

    REGULAR("REGULAR"),
    POISON_PILL("POISON_PILL");

    private final String messageType;

    MessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
