package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model){
        model.addAttribute("data","hello!@#");
        return "hello";
        // 리턴값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리한다.
        // viewName 매핑 : resources:templates/ + {viewName}+ .html
        // resources 하위에 hello.html을 찾아서 렌더링 해라
    }

    @GetMapping("hello-mvc")
    public String HelloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body 부분에 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody // HttpMessageConverter 가 객체를 보고 Json 형식으로 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    //MappingJackson2HttpMessageConverter

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
