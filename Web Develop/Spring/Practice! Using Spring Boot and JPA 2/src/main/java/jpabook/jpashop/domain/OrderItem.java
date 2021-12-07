package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 접근제한자 설정
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @JsonIgnore // 양방향 관계일 시, 무한루프 걸릴 가능성 높음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

//    protected OrderItem() {
//        // JPA는 protected 접근 제한자를 가진 생성자까지 허용한다
//    }

    //== 생성 메서드 ==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 재고를 주문 수량 만큼 줄임
        return orderItem;
    }

    //== 비즈니스 로직 ==//
    public void cancel() {
        getItem().addStock(count);
    }

    // == 조회 로직 == //
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}

