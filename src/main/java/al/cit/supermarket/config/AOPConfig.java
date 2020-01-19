package al.cit.supermarket.config;

import al.cit.supermarket.aop.SessionAttributeAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

    @Bean
    public SessionAttributeAspect sessionAttributeAspect(){
        return new SessionAttributeAspect();
    }
}
