package purchase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import purchase.model.OrderModel;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Long> {

    List<OrderModel> findAllByReviewIdIn(final List<String> reviewsIds);

    List<OrderModel> findAllByUserId(final String userId);

    OrderModel getOrderModelByUserIdAndProductCode(final String userId, final String productCode);
}
