package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMve(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // http body에 문자 내용을 직접 반환 -> viewResolver 대신에 HttpMessageConverter가 동작한다. ->
    // 기본 문자 처리 : StringHttpMessageConverter
    // 기본 객체 처리 : MappingJackson2HttpMessageConverter (Jackson : 객체를 json으로 반환해주는 라이브러리.(google의 Gson도 있음) 스프링은 jackson을 기본으로 탐재하고 있음.)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        // getter setter java bean 규약. property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
