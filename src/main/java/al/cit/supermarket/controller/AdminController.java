package al.cit.supermarket.controller;

import al.cit.supermarket.service.dto.ProductDTO;
import al.cit.supermarket.service.dto.StoreDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "admin/")
public class AdminController {


    @GetMapping("dashboard")
    public String getDashboard(Model model){

        Map<String, Object> attributes = new HashMap<>();

        attributes.put("newStore", new StoreDTO());
        attributes.put("newProduct", new ProductDTO());

        model.addAllAttributes(attributes);
        return "admin/dashboard`";
    }
}
