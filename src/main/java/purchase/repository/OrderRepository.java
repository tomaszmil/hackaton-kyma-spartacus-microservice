package purchase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import purchase.model.OrderModel;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Long> {

    Iterable<OrderModel> getAllByUserIdAndOrderId(String userId, String orderId);
}
