package al.cit.supermarket.model.file;

import al.cit.supermarket.util.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ImageFile extends File {

    public String getThumbNail(){
        return super.getURL() + Constants.PATH_TO_THUMBNAIL_IMAGE+ super.getName();
    }

    public String getMedium(){
        return super.getURL() + Constants.PATH_TO_MEDIUM_IMAGE + super.getName();
    }
}
