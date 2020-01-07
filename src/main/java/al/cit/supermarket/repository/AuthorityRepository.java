package al.cit.supermarket.repository;

import al.cit.supermarket.model.authority.Authority;
import al.cit.supermarket.model.authority.AuthorityName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByName(AuthorityName name);
}
