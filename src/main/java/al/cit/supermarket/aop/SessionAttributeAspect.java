package al.cit.supermarket.aop;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.service.dto.StoreDTO;
import al.cit.supermarket.service.dto.notification.NotificationType;
import al.cit.supermarket.service.dto.notification.Notifications;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Aspect
@Component
public class SessionAttributeAspect {

    private MySessionAttributes mySessionAttributes;
    private HttpSession session;
    private Notifications notifications;

    @Autowired
    public SessionAttributeAspect(MySessionAttributes mySessionAttributes, HttpSession session, Notifications notifications) {
        this.mySessionAttributes = mySessionAttributes;
        this.session = session;
        this.notifications = notifications;
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    private void selectControllers(){}

    @Pointcut("execution(* *(..))")
    private void allMethods(){};

    @AfterReturning(value = "selectControllers() && allMethods()", returning = "path")
    private void addMySessionAttributes(Object path){

        //Using the code if the method returns a String
        if (path instanceof String) {

            // If we are redirecting, the actions are postponed
            if (!((String) path).startsWith("redirect:/")){

                if (mySessionAttributes.getStore() != null) {

                    StoreDTO dto = mySessionAttributes.getStore();
                    System.out.println(dto);

                    session.setAttribute("selectedStore", dto);
                }

                if (notifications.getNotifications() != null) {

                    //Adding the messages to the session(same as adding to the model)
                    session.setAttribute(NotificationType.NOTIFICATIONS.toString(), notifications.getNotifications());
                    // Cleaning the Notifications bean
                    notifications.setNotifications(new ArrayList<>());
                }
            }
        }
    }
}
