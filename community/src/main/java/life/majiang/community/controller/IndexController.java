package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 作者:悠悠我心
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                final String  token= cookie.getValue();
                User user = mapper.finByToken(token);
                if (user!=null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        return "index";
    }
}
