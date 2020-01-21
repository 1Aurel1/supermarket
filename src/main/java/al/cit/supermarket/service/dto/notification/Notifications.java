package al.cit.supermarket.service.dto.notification;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class Notifications {

    private List<NotificationDTO> notifications;

    public Notifications() {
        this.notifications = new ArrayList<>();
    }

    public void addNotification(NotificationDTO dto){

        this.notifications.add(dto);
    }


    public void addNotification(NotificationDTO notification, boolean onTop){

        if (onTop)
            this.notifications.add(0, notification);
        else
            addNotification(notification);
    }

    public void addNotifications(List<NotificationDTO> notifications){

        for (NotificationDTO notification :
                notifications) {
            addNotification(notification);
        }
    }

    public void addNotifications(List<NotificationDTO> notifications, boolean onTop){

        if (onTop)
            for (NotificationDTO notification :
                    notifications) {
                addNotification(notification, true);
            }
        else
            addNotifications(notifications);
    }

    public void addNotificationsFromBindingResult(BindingResult bindingResult){

        for (ObjectError error:
                bindingResult.getAllErrors()) {

            NotificationDTO notification = new NotificationDTO();
            notification.setType(NotificationType.ERROR.toString());

            StringBuilder builder = new StringBuilder();
            builder.append("Error in field: ");
//            builder.append()

        }
    }

    public List<NotificationDTO> getNotifications() {

        if (this.notifications == null)
            this.notifications = new ArrayList<>();

        return notifications;
    }

    public void setNotifications(List<NotificationDTO> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "notifications=" + notifications +
                '}';
    }
}
