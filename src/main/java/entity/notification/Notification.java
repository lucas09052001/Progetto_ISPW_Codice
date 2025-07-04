package entity.notification;

public class CustomNotification {

    private int id;
    private String senderUsername;
    private String receiverUsername;
    private String message;
    private boolean seen;

    public CustomNotification(){}

    public CustomNotification(int id, String senderUsername, String message, boolean seen) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.message = message;
        this.seen = seen;
    }

    public CustomNotification(int id, String senderUsername, String receiverUsername, String message, boolean seen) {
        this.id = id;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.message = message;
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

}
