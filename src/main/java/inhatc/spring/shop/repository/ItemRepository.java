package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String ItemNm,String ItemDetail);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    List<Item> findByStockNumberGreaterThanEqualAndItemNmContaining(Integer stockNumber, String ItemNm);

    @Query(value = "select * from Item i where i.stock_number >= :stockNumber and i.item_nm like %:ItemNm%",nativeQuery = true)
    List<Item> findByStockNumberGreaterThanEqualAndItemNmContainingNative(Integer stockNumber, String ItemNm);
    @Query("select i from Item i where i.stockNumber >= :stockNumber and i.itemNm like %:ItemNm%")
    List<Item> findByStockNumberAndItemNmQuery(Integer stockNumber, String ItemNm);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc" )
    List<Item> findByDetail(@Param("itemDetail")String itemDetail);

    @Query(value = "select * from Item i where i.item_detail like %:itemDetail% order by i.price desc",nativeQuery = true )
    List<Item> findByDetailNative(@Param("itemDetail")String detail);



}
