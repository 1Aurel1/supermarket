package al.cit.supermarket.component;

import al.cit.supermarket.model.Store;
import al.cit.supermarket.service.dto.StoreDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

@Data
@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionAttributes {

    @Autowired
    private HttpSession httpSession;

    private StoreDTO store;

    public void setStore(StoreDTO store) {

        this.store = store;
        httpSession.setAttribute("currentStore", this.store);
    }

    public void cleanStore() {

        this.store = null;
        httpSession.removeAttribute("currentStore");
    }
}
