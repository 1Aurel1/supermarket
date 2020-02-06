package al.cit.supermarket.component;

import al.cit.supermarket.service.dto.StoreDTO;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class MySessionAttributes {

    private StoreDTO store;
    private String username;

    public void setStore(StoreDTO store) {

        this.store = store;
    }

    public void cleanStore() {

        this.store = null;
    }

    public StoreDTO getStore() {
        return store;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MySessionAttributes{" +
                "store=" + store +
                '}';
    }
}
