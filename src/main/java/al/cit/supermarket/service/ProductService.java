package al.cit.supermarket.service;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.exeption.ResourceNotFoundException;
import al.cit.supermarket.model.Product;
import al.cit.supermarket.model.Store;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.repository.StoreRepository;
import al.cit.supermarket.service.dto.NewProductDTO;
import al.cit.supermarket.service.dto.ProductDTO;
import al.cit.supermarket.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private MySessionAttributes sessionAttributes;
    private StoreRepository storeRepository;
    private MySessionAttributes mySessionAttributes;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, MySessionAttributes attributes, StoreRepository storeRepository, MySessionAttributes mySessionAttributes) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sessionAttributes = attributes;
        this.storeRepository = storeRepository;
        this.mySessionAttributes = mySessionAttributes;
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

    public int createProduct(NewProductDTO dto) {

        System.out.println(dto);

        Product product = productMapper.newProductDtoToProduct(dto);



        product = productRepository.save(product);

        return product.getId();
    }

    public void createProducts(MultipartFile file){

        ProductCSVImporter importer = new ProductCSVImporter();
        importer.importFromCSV(file);

        List<Product> products = importer.getObjects();

        Store store = storeRepository.getOne(mySessionAttributes.getStore().getId());

        //Adding the store to the product
        for (Product product : products) {

            product.setStore(store);
        }

        // In case there are no parsing or line errors the list is going to be persisted, otherwise the user will be notified about the cancellation
        if (!importer.isHasError()){

            //Saving all the products
            productRepository.saveAll(products);
        }

    }

    public int updateProduct(ProductDTO dto) {

        Product product = productMapper.productDtoToProductForPatch(dto);
        return productRepository.save(product).getId();
    }

    public void deleteProduct(int id) {

        productRepository.deleteById(id);
    }
}
