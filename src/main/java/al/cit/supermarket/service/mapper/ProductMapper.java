package al.cit.supermarket.service.mapper;

import al.cit.supermarket.model.Product;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.service.dto.ProductDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    private ProductRepository productRepository;

    @Autowired
    public ProductMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product productDtoToProduct(ProductDTO dto){

        Product product = new Product();
        if (dto.getId() != 0)
            product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        return product;
    }

    public Product productDtoToProductForPatch(ProductDTO dto) {

        if (dto.getId() != 0) {

            Product product = productRepository.getOne(dto.getId());
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            return product;
        }
        return null;
    }
}
