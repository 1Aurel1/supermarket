package al.cit.supermarket.repository;

import al.cit.supermarket.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends PagingAndSortingRepository<Store, Integer> {

}
