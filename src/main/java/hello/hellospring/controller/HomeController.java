package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // home 화면으로 맵핑된게 있기때문에 /resources/static/index.html(welcome page)로 가지 않음
    public String home() {
        return "home";
    }
}
