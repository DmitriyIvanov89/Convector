package ordersparser.model;

public enum MessageType {

    REGULAR("REGULAR"),
    POISON_PILL("POISONPILL");

    private final String messageType;

    MessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}
