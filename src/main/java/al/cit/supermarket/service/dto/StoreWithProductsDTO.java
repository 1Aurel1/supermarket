package al.cit.supermarket.service.dto;

import al.cit.supermarket.model.Product;
import al.cit.supermarket.model.Store;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StoreWithProductsDTO {

    private int id;
    private String name;
    private String location;

    private List<ProductDTO> products;

    public StoreWithProductsDTO(Store store){

        this.id = store.getId();
        this.name = store.getName();
        this.location = store.getLocation();

        this.products = new ArrayList<>();
//        for (Product product:
//                store.getProducts()) {
//
//            this.products.add(new ProductDTO(product));
//        }

    }
    public StoreWithProductsDTO(Store store, List<Product> products){

        this.id = store.getId();
        this.name = store.getName();
        this.location = store.getLocation();

        this.products = new ArrayList<>();
        for (Product product:
                products) {

            this.products.add(new ProductDTO(product));
        }

    }
}
