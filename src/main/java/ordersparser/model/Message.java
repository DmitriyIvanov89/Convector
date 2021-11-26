package ordersparser.model;

public class Message {

    private final MessageType messageType;
    private final Order order;

    public Message(MessageType messageType, Order order) {
        this.messageType = messageType;
        this.order = order;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public Order getOrder() {
        return order;
    }
}
