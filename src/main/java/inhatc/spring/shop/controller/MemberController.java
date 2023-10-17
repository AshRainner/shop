package inhatc.spring.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/member/new")
    public String memberForm(){
        return "member/memberForm";
    }

}
