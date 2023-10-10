package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.ItemDto;
import inhatc.spring.shop.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ThymeleafController {
    @GetMapping("/thymeleaf/ex1")
    public String ex1(Model model){
        ItemDto itemDto = ItemDto.builder()
                .itemNm("테스트 상품")
                .itemSellStatus("SELL")
                .price(10000)
                .itemDetail("상품 상세 설명")
                .stockNumber(200)
                .build();

        model.addAttribute("itemDto",itemDto);

        return "thymeleaf/ex1";
    }
    @GetMapping("/thymeleaf/ex2")
    public String ex2(){
        return "thymeleaf/ex2";
    }
    @GetMapping("/thymeleaf/ex3")
    public String ex2(ParamDto paramDto, Model model){
        log.info("===========paramDto : "+paramDto);
        model.addAttribute("dto",paramDto);
        return "thymeleaf/ex3";
    }

    @GetMapping("/thymeleaf/ex4")
    public String ex4(){
        return "thymeleaf/ex4";
    }

}
