package al.cit.supermarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Store extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    @ManyToMany
    @JoinTable(
            name = "user_store",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id")
    )
    private Set<User> users;
}
