package al.cit.supermarket.service.dto;

import al.cit.supermarket.model.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private String image_url;

    public ProductDTO(){}

    public ProductDTO(Product product){

        this.id = product.getId();
        this.quantity = product.getQuantity();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        if (product.getImage() != null)
            this.image_url = product.getImage().getURL();
    }
}
