package al.cit.supermarket.service.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {


    private String type;
    private String message;

    public NotificationDTO() {
    }

    public NotificationDTO(String type, String message) {
        this.type = type;
        this.message = message;
    }

}
