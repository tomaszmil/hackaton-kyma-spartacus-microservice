package purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import purchase.model.OrderModel;
import purchase.service.OrderService;

@RestController("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("add/{userId}/{orderId}")
    public void add(@PathVariable("userId") final String userId, @PathVariable("orderId") final String orderId) {
        orderService.createOrder(orderId, userId);
    }

    @RequestMapping("/all")
    public Iterable<OrderModel> getAll() {
        return orderService.findAll();
    }
}
