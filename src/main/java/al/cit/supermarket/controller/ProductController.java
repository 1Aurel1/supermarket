package al.cit.supermarket.controller;

import al.cit.supermarket.service.ProductService;
import al.cit.supermarket.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public String getProducts(
            Model model
        ){

        List<ProductDTO> products = productService.getStoreProducts();
        model.addAttribute("products", products);
        return "/products/index";
    }

    @GetMapping("/{id}")
    public String getProduct(
            @PathVariable("id") int id,
            Model model){

        model.addAttribute("product", productService.getStoreProduct(id));
        return "products/show";
    }

    @PostMapping
    public String postProduct(@ModelAttribute("newProduct") ProductDTO product){

        int id = productService.createProduct(product);
        return String.format("redirect:/products/%d", id);
    }

    @PutMapping
    public String putProduct(
            @ModelAttribute("newProduct") ProductDTO updatedProduct
            ){

        int id = productService.updateProduct(updatedProduct);
        return String.format("redirect:/products/%d", id);
    }
}
