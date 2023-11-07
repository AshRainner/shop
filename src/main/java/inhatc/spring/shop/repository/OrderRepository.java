package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Cart;
import inhatc.spring.shop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {

}
