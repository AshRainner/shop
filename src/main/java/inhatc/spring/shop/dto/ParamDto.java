package inhatc.spring.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class ParamDto {
    private String name;
    private int age;
}
