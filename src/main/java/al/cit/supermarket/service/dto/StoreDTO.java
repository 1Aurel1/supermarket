package al.cit.supermarket.service.dto;

import al.cit.supermarket.model.Store;
import lombok.Data;

@Data
public class StoreDTO {

    private int id;
    private String name;
    private String location;

    public StoreDTO(){}

    public StoreDTO(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.location = store.getLocation();
    }
}
