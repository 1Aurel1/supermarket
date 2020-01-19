package al.cit.supermarket.service.mapper;

import al.cit.supermarket.model.Store;
import al.cit.supermarket.service.dto.StoreDTO;
import org.springframework.stereotype.Service;

@Service
public class StoreMapper {

    public Store storeDtoToStore(StoreDTO dto){

        Store store = new Store();

        if (dto.getId() != 0)
            store.setId(dto.getId());

        store.setName(dto.getName());
        store.setLocation(dto.getLocation());

        return store;
    }
}
