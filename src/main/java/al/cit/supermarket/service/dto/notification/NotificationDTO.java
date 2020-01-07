package al.cit.supermarket.service.dto.notification;

public class NotificationDTO {


    private NotificationType type;
    private String message;

    public NotificationDTO() {
    }

    public NotificationDTO(NotificationType type, String message) {
        this.type = type;
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
