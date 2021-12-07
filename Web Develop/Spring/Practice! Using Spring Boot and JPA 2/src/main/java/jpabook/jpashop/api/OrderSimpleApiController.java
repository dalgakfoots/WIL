package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *
 *  xToOne(ManyToOne, OneToOne)
 *  Order
 *  Order -> Member
 *  Order -> Delivery
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        // 엔티티 직접 노출 하고 있음.
        // 필요한 데이터만 노출할 수 있도록 DTO 객체 필요함.
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // LAZY loading 되어있는 엔티티 강제 초기화
            order.getDelivery().getId(); // LAZY loading 되어있는 엔티티 강제 초기화
        }
        return all;
    }
}
