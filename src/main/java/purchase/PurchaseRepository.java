package purchase;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin()
@RepositoryRestResource(collectionResourceRel = "purchases", path = "purchases")
public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {
	List<Purchase> findByNameSpace( @Param("nameSpace") String nameSpace );
}
