package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 作者:悠悠我心
 */
@Controller
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
