package al.cit.supermarket.service.dto;

import al.cit.supermarket.model.Product;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewProductDTO {

    private int id;

    @Size(min = 1)
    private String name;
    @Size(min = 5)
    private String description;

    @Min(1)
    private int quantity;

    @Min(1)
    private double price;

    @NotNull
    private MultipartFile image;

    public NewProductDTO(){}

    public NewProductDTO(Product product){

        this.id = product.getId();
        this.quantity = product.getQuantity();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}
