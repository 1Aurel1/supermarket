package al.cit.supermarket.service;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.model.Product;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private MySessionAttributes attributes;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getStoreProducts() {

        List<ProductDTO> products = new ArrayList<>();
//
//        for (Product product :
//                productRepository.findAllByStoreId(id)) {
//
//            products.add(new ProductDTO(product));
//        }

        return products;
    }

    public Object getStoreProduct(int id) {

        return 0;
    }
}
