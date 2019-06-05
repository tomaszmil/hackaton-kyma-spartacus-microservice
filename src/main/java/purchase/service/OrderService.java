package purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import purchase.data.OrderData;
import purchase.data.ReviewData;
import purchase.model.OrderModel;
import purchase.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderModel> getProductBoughtByUser(final String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public void addUserOrder(final OrderData orderData) {
        final OrderModel order = new OrderModel();

        order.setUserId(orderData.getUserdId());
        order.setProductCode(orderData.getProductCode());
        order.setProductName(orderData.getProductName());

        orderRepository.save(order);
    }

    public void addUserReviewIdToProduct(final ReviewData reviewData) {
        final OrderModel order = orderRepository.getOrderModelByUserIdAndProductCode(reviewData.getUserId(), reviewData.getProductCode());

        order.setReviewId(reviewData.getReviewId());

        orderRepository.save(order);
    }

    public List<String> verifyReviews(final List<String> reviewIds) {
        return orderRepository.findAllByReviewIdIn(reviewIds).stream().map(OrderModel::getReviewId).collect(Collectors.toList());
    }
}
