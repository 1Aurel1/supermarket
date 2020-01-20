package al.cit.supermarket.aop;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.service.dto.StoreDTO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class SessionAttributeAspect {

    @Autowired
    private MySessionAttributes mySessionAttributes;

    @Autowired
    private HttpSession session;

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


            }

        }
    }
}
