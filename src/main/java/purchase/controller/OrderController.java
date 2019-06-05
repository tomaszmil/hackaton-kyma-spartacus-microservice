package purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import purchase.data.OrderData;
import purchase.data.ReviewData;
import purchase.service.OrderService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<?> getProductBoughtByUser(@PathVariable final String userId) {
        return ResponseEntity.ok().body(orderService.getProductBoughtByUser(userId));
    }

    @PostMapping("/add/order")
    public ResponseEntity<?> addUserOrder(@RequestBody final OrderData orderData) {
        orderService.addUserOrder(orderData);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/review")
    public ResponseEntity<?> addUserReviewIdToProduct(@RequestBody final ReviewData reviewData) {
        orderService.addUserReviewIdToProduct(reviewData);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify/review")
    public ResponseEntity<?> verifyReviews(@RequestBody final List<String> reviewIds) {
        return ResponseEntity.ok().body(orderService.verifyReviews(reviewIds));
    }
}
