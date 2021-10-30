package ordersparser.model;

public enum MessageType {

    REGULAR("REGULAR"),
    POISON_PILL("POISON_PILL");

    private final MessageType messageType;

    MessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getMessageType() {
        return messageType;
    }
}
