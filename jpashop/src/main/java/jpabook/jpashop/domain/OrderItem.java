package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문
    private int orderPrice; //주문 가격
    private int count; //주문 수량

    //new OrderItem()을 통해 생성하는 것을 제한한다. OR 클래스 위에 @NoArgsConstructor(access=AccessLevel.PROTECTED) 선언
    protected OrderItem() {

    }

    //생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        //주문시 item의 재고를 수정해야 한다.
        item.removeStock(count);
        return orderItem;
    }
    //비즈니스 로직
    public void cancel() {
        //Item의 재고 수량을 원복시켜준다.
        getItem().addStock(count);
    }

    //조회 로직
    public int getTotalPrice() {
        //주문 가격 * 주문 수량
        return getOrderPrice() * getCount();
    }
}
