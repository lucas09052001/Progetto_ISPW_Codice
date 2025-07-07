package entity.notification;

public class NotificationDTO {

    private int id;
    private String senderUsername;
    private String message;
    private boolean seen;

    public NotificationDTO(Notification notification) {
        this.senderUsername = notification.getSenderUsername();
        this.message = notification.getMessage();
        this.seen = notification.isSeen();
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
