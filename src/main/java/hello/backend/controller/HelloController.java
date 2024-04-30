package hello.backend.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin("http://localhost:3000")
@Controller
public class HelloController {

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @PostMapping("oauth/kakao")
    @ResponseBody
    public Token login(@RequestBody Map<String, Object> obj){
        String code = obj.get("code").toString();
            Token token = Token.builder()
                    .accessToken("accessToken")
                    .refreshToken(code)
                    .build();

            return token;
    }

    @Getter
    @Setter
    @Builder
    static class Token {
        private String accessToken;
        private String refreshToken;

    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

