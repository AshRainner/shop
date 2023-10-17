package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class TestController {
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
