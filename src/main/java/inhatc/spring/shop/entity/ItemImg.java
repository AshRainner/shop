package inhatc.spring.shop.entity;

import inhatc.spring.shop.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_img_id", nullable = false)
    private Long id; //상품 이미지 id

    private String imgName; //이미지 파일명
    private String oriImgName; //이미지 원본 파일명
    private String imgUrl; // 이미지 경로
    private String repImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /*
    @param imgName
    @param oriImgName
    @param imgUrl
     */
    public void updateItemImg(String imgName, String oriImgName, String imgUrl){
        this.imgName=imgName;
        this.oriImgName=oriImgName;
        this.imgUrl=imgUrl;
    }
}
