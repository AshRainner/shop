package inhatc.spring.shop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass // BaseTimeEntity를 상속할경우 필드들도 컬럼으로 인식하도록 함
@Getter
@Setter
public abstract class BaseTimeEntity {


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime; // 등록일

    @LastModifiedDate
    private LocalDateTime updateTime; // 수정일
}
