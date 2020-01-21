package al.cit.supermarket.service.mapper;

import al.cit.supermarket.component.MySessionAttributes;
import al.cit.supermarket.model.Product;
import al.cit.supermarket.model.file.ImageFile;
import al.cit.supermarket.repository.ProductRepository;
import al.cit.supermarket.repository.StoreRepository;
import al.cit.supermarket.service.CloudinaryService;
import al.cit.supermarket.service.dto.NewProductDTO;
import al.cit.supermarket.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    private ProductRepository productRepository;
    private MySessionAttributes mySessionAttributes;
    private StoreRepository storeRepository;
    private CloudinaryService cloudinaryService;

    @Autowired
    public ProductMapper(ProductRepository productRepository, MySessionAttributes mySessionAttributes, StoreRepository storeRepository, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.mySessionAttributes = mySessionAttributes;
        this.storeRepository = storeRepository;
        this.cloudinaryService = cloudinaryService;
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

    public Product newProductDtoToProduct(NewProductDTO dto){

        Product product = new Product();
        if (dto.getId() != 0)
            product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        ImageFile image = new ImageFile();
        image.setName(dto.getImage().getName());
        image.setUploadContentType(dto.getImage().getContentType());
        image.setUploadFileSize(dto.getImage().getSize()+"");
        image.setURL(cloudinaryService.uploadFile(dto.getImage()));

        product.setImage(image);
        product.setStore(storeRepository.getOne(mySessionAttributes.getStore().getId()));

        return product;
    }
}
