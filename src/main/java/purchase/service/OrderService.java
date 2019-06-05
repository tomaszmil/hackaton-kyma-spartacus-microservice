package purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import purchase.model.OrderModel;
import purchase.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(final String orderId, final String userId) {
        final OrderModel order = new OrderModel(userId, orderId);
        orderRepository.save(order);
    }

    public Iterable<OrderModel> getOrder(final String userId, final String orderId) {
        return orderRepository.getAllByUserIdAndOrderId(userId, orderId);
    }

    public Iterable<OrderModel> findAll() {
        return orderRepository.findAll();
    }
}
