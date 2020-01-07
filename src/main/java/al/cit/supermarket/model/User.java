package al.cit.supermarket.model;

import al.cit.supermarket.model.authority.Authority;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(
        name = "users"
)
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @NaturalId
    @Column(unique = true)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "authority_user",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @ManyToMany
    @JoinTable(
            name = "user_store",
            joinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )private Set<Store> stores;

    public User() {

        this.authorities = new HashSet<>();
        this.enabled = true;
    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority){
        this.authorities.remove(authority);
    }
}
