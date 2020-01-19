package al.cit.supermarket.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SessionAttributeAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    private void selectControllers(){}

    @Pointcut("execution(* *(..))")
    private void allMethods(){};

//    @AfterReturning(value = "selectControllers() && allMethods()", returning = "path")
//    private void addMySessionAttributes(Object path){
//
//    }
}
