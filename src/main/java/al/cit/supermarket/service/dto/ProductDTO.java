package al.cit.supermarket.service.dto;

import al.cit.supermarket.model.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private double price;

    public ProductDTO(){}

    public ProductDTO(Product product){

        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
