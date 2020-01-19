package al.cit.supermarket.controller;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.service.ProductService;
import al.cit.supermarket.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/products")
public class ProductController {

    private ProductService productService;
    private MySessionAttributes mySessionAttributes;

    @Autowired
    public ProductController(ProductService productService, MySessionAttributes mySessionAttributes) {
        this.productService = productService;
        this.mySessionAttributes = mySessionAttributes;
    }

    @GetMapping
    public String getProducts(
            Model model
        ){

        List<ProductDTO> products = productService.getStoreProducts(mySessionAttributes.getStore().getId());
        model.addAttribute("");
        return "/products/index";
    }

//    @GetMapping("/{id}")
//    public String getProduct(){}
}
