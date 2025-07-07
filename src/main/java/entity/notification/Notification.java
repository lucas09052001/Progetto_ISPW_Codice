package entity.notification;

public class Notification {

    private String senderUsername;
    private String receiverUsername;
    private String message;
    private boolean seen;

    public Notification(){}

    public Notification(String senderUsername, String message, boolean seen) {
        this.senderUsername = senderUsername;
        this.message = message;
        this.seen = seen;
    }

    public Notification(String senderUsername, String receiverUsername, String message, boolean seen) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.message = message;
        this.seen = seen;
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
