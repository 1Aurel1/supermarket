package al.cit.supermarket.controller;

import al.cit.supermarket.service.ProductService;
import al.cit.supermarket.service.StoreService;
import al.cit.supermarket.service.dto.NewProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    private StoreService storeService;
    private ProductService productService;

    @Autowired
    public AdminController(StoreService storeService, ProductService productService) {
        this.storeService = storeService;
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model){

        Map<String, Object> attributes = new HashMap<>();

        model.addAllAttributes(attributes);
        return "admin/index";
    }

    @GetMapping("/stores")
    public String getStores(Model model){

        model.addAttribute("stores", storeService.getStores());

        return "admin/stores";
    }

    @GetMapping("/products")
    public String getProducts(Model model){

        model.addAttribute("products", productService.getStoreProducts());
        model.addAttribute("newProduct", new NewProductDTO());

        return "admin/products";
    }
}
