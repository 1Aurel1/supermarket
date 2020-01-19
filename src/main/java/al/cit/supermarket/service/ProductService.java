package al.cit.supermarket.service;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.exeption.ResourceNotFoundException;
import al.cit.supermarket.model.Product;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.service.dto.ProductDTO;
import al.cit.supermarket.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private MySessionAttributes sessionAttributes;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, MySessionAttributes attributes) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sessionAttributes = attributes;
    }

    public List<ProductDTO> getStoreProducts() {

        int storeId = sessionAttributes.getStore().getId();
        List<ProductDTO> products = new ArrayList<>();

        for (Product product :
                productRepository.findAllByStoreId(storeId)) {

            products.add(new ProductDTO(product));
        }

        return products;
    }

    public ProductDTO getStoreProduct(int id) {

        int storeId = sessionAttributes.getStore().getId();

        Product product = productRepository.findByStoreIdAndId(storeId, id).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", id));

        return new ProductDTO(product);
    }

    public int createProduct(ProductDTO dto) {

        Product product = productMapper.productDtoToProduct(dto);
        product = productRepository.saveAndFlush(product);

        return product.getId();
    }

    public int updateProduct(ProductDTO dto) {

        Product product = productMapper.productDtoToProductForPatch(dto);
        return productRepository.saveAndFlush(product).getId();
    }
}
