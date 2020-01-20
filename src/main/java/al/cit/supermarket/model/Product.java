package al.cit.supermarket.model;

import al.cit.supermarket.model.file.ImageFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Data
@Entity
@Table(
        name = "products"
)
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    private int quantity;

    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageFile image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
