package al.cit.supermarket.repository;

import al.cit.supermarket.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    List<Store> findAll();
    Optional<Store> getById(int id);
}
