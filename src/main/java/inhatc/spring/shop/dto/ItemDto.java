package inhatc.spring.shop.dto;

import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
public class ItemDto {

    private Long id;

    private String itemNm;      //상품 이름

    private int price;          //가격

    private int stockNumber;    //재고수량

    private String itemDetail;  //상품 상세정보

    private String itemSellStatus;

    private LocalDateTime regTime;

    private LocalDateTime upDateTime;

}
