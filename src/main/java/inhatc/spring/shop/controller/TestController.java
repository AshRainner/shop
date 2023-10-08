package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GetMapping("/")
    public PersonDto hello(){
        PersonDto p = new PersonDto();
        p.setAge(10);
        p.setName("홍길동");
        return p;
    }
}
