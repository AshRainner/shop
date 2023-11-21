package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import inhatc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static inhatc.spring.shop.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("querydsl 테스트")
    public void queryDslTest(){
        createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = item;
        //queryFactory.selectFrom(QItem.item);

        List<Item> itemList = queryFactory.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%"+"1"+"%"))
                .orderBy(item.price.desc())
                .fetch();

        itemList.forEach(System.out::println);

    }

    @Test
    @DisplayName("과제")
    public void homeWorkTest(){
        createItemList();

        System.out.println("----------------------------------------------------");
        //query
        var itemList = itemRepository.findByStockNumberAndItemNmQuery(150,"8");
        itemList.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        //jpql
        itemList=itemRepository.findByStockNumberGreaterThanEqualAndItemNmContaining(150,"8");
        itemList.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        //Native
        itemList=itemRepository.findByStockNumberGreaterThanEqualAndItemNmContainingNative(150,"8");
        itemList.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        //querydsl
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        itemList = queryFactory.selectFrom(item)
                .where(item.stockNumber.goe(150).and(item.itemDetail.like("%"+"8"+"%")))
                .fetch();
        itemList.forEach(System.out::println);
    }


    public void createItemList(){
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+i)
                    .price(10000+i)
                    .itemDetail("테스트 설명"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100+i)
                    .build();
            itemRepository.save(item);
        }
    }

    public void createItemList2(){
        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+i)
                    .price(10000+i)
                    .itemDetail("테스트 설명"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100+i)
                    .build();
            itemRepository.save(item);
        }
    }

    public void createItemList3(){
        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품"+i)
                    .price(10000+i)
                    .itemDetail("테스트 설명"+i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .stockNumber(100+i)
                    .build();
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 querydsl조회 테스트")
    public void querydslTest(){
        createItemList2();
        createItemList3();

        BooleanBuilder builder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트";
        int price = 10003;
        String itemStatus = "SELL";
        builder.and(item.itemDetail.like("%"+itemDetail+"%"));
        builder.and(item.price.gt(price));

        if(StringUtils.equals(itemStatus,ItemSellStatus.SELL)){
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0,5);
        Page<Item> page = itemRepository.findAll(builder,pageable);

        List<Item> content = page.getContent();
        for (Item item1 : content) {
            System.out.println(item1);
        }


    }

    @Test
    @DisplayName("JPQL 테스트")
    public void findByDetailTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByDetail("1");
        itemList.forEach(System.out::println);

    }

    @Test
    @DisplayName("가격에 대한 정렬 테스트")
    public void findByPriceLessThanOrderByPriceDescTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);

        itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("상품명 또는 상품 상세 설명 조회 테스트")
    public void findByItemNmOrItemDetailTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 설명2");
        itemList.forEach(item-> System.out.println(item));
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");

        for (Item item : itemList) {
            System.out.println(item);
        }
    }


    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .itemDetail("테스트 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .stockNumber(100)
                .build();

        System.out.println("##################Item : "+item);

        Item saveItem = itemRepository.save(item);

        System.out.println("##################saveItem : "+saveItem);
    }
}