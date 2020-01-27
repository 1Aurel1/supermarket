package al.cit.supermarket.service;

import al.cit.supermarket.model.Product;
import al.cit.supermarket.model.Store;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.repository.StoreRepository;
import al.cit.supermarket.service.dto.StoreWithProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private StoreRepository storeRepository;
    private ProductRepository productRepository;

    @Autowired
    public HomeService(StoreRepository storeRepository, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    public List<StoreWithProductsDTO> getStoresAndProducts(){

        List<Store> stores = storeRepository.findAll();
        List<StoreWithProductsDTO> storeWithProductsDTOS = new ArrayList<>();

        for (Store store :
                stores) {
            List<Product> products = productRepository.findAllByStoreId(store.getId());
            StoreWithProductsDTO dto = new StoreWithProductsDTO(store, products);
            storeWithProductsDTOS.add(dto);
        }

        return storeWithProductsDTOS;
    }
}
