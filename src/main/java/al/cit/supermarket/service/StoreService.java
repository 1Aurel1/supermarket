package al.cit.supermarket.service;

import al.cit.supermarket.exeption.ResourceNotFoundException;
import al.cit.supermarket.model.Store;
import al.cit.supermarket.repository.StoreRepository;
import al.cit.supermarket.service.dto.StoreDTO;
import al.cit.supermarket.service.dto.StoreWithProductsDTO;
import al.cit.supermarket.service.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private StoreRepository storeRepository;
    private StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<Store> getStores() {

        return storeRepository.findAll();
    }

    public List<StoreWithProductsDTO> storesWithProducts(int id){

        List<StoreWithProductsDTO> storesWithProductsDTOS = new ArrayList<>();

        for (Store store:
                storeRepository.findAll()) {

            storesWithProductsDTOS.add(new StoreWithProductsDTO(store));
        }

        return storesWithProductsDTOS;
    }

    public StoreDTO getStore(int id){

        Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store", "Id", id));
        return new StoreDTO(store);
    }

    public int createStore(StoreDTO storeDTO) {

        Store store = storeMapper.storeDtoToStore(storeDTO);
        return storeRepository.saveAndFlush(store).getId();
    }

    public StoreDTO updateStore(StoreDTO storeDTO){

        Store store = storeRepository.saveAndFlush(storeMapper.storeDtoToStore(storeDTO));
        return new StoreDTO(store);
    }

    public void deleteStore(int id) {

        storeRepository.deleteById(id);
    }
}
