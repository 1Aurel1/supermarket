package al.cit.supermarket.model.file;

import al.cit.supermarket.model.Product;
import al.cit.supermarket.util.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ImageFile extends File {


    @OneToOne(mappedBy = "image")
    private Product product;

    public String getThumbNail(){
        return super.getURL() + Constants.PATH_TO_THUMBNAIL_IMAGE+ super.getName();
    }

    public String getMedium(){
        return super.getURL() + Constants.PATH_TO_MEDIUM_IMAGE + super.getName();
    }
}
