package inhatc.spring.shop.entity;

import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.repository.ItemRepository;
import inhatc.spring.shop.repository.MemberRepository;
import inhatc.spring.shop.repository.OrderItemRepository;
import inhatc.spring.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @PersistenceContext
    private EntityManager em;

    public Item createItem(){
        Item item = Item.builder().itemNm("테스트상품")
                .price(10000).itemDetail("상세설명").itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100).regTime(LocalDateTime.now()).upDateTime(LocalDateTime.now()).build();
        return item;
    }

    public Orders createOrder(){
        Orders order = new Orders();
        for(int i=0; i<3; i++){
            Item item  = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);
        order.setMember(member);
        orderRepository.save(order);
        return order;
    }
    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Orders order= new Orders();
        for(int i=0; i<3; i++){
            Item item  = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        orderRepository.saveAndFlush(order);
        em.clear();;
        Orders savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

    @Transactional
    @Test
    @DisplayName("고아객체 제거 테스트")
    public void orphanRemovalTest(){
        Orders order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }

    @Transactional
    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){
        Orders order = this.createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("Order class : "+
                orderItem.getOrder().getClass());
    }



}