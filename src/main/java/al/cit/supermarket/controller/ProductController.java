package al.cit.supermarket.controller;

import al.cit.supermarket.service.ProductService;
import al.cit.supermarket.service.dto.NewProductDTO;
import al.cit.supermarket.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

//    @GetMapping
//    public String getProducts(
//            Model model
//        ){
//
//        List<ProductDTO> products = productService.getStoreProducts();
//        model.addAttribute("products", products);
//
//        return "/products/index";
//    }

    @GetMapping("/{id}")
    public String getProduct(
            @PathVariable("id") int id,
            Model model){

        model.addAttribute("product", productService.getStoreProduct(id));
        return "products/show";
    }

    @PostMapping("")
    public String postProduct(
            @Valid @ModelAttribute("newProduct") NewProductDTO product,
            BindingResult bindingResult,
            Model model
            ){


        if (bindingResult.hasErrors()) {

            model.addAttribute("formErrors", bindingResult.getAllErrors());
            return "redirect:/admin/products";
        }

        int id = productService.createProduct(product);
        return "redirect:/admin/products";
    }


    @PostMapping(value = "/bulk/upload", consumes = {"multipart/form-data"})
    public String postBulkUpload(@RequestParam(name = "file") MultipartFile file) {

        productService.createProducts(file);

        return "redirect:/admin/products";
    }

    @PostMapping("/update/{id}")
    public String putProduct(
            @Valid @ModelAttribute("newProduct") ProductDTO updatedProduct,
            BindingResult bindingResult
            ){

        int id = productService.updateProduct(updatedProduct);
        return "redirect:/admin/products";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){

        productService.deleteProduct(id);

        return "redirect:/admin/products";
    }
}
