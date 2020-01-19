package al.cit.supermarket.repository;

import al.cit.supermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByStoreId(int id);
    Optional<Product> findByStoreIdAndId(int storeId, int productId);
}
