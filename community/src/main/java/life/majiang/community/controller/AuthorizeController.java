package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GitHupUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.pojo.User;
import life.majiang.community.provider.GitHupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 作者:悠悠我心
 * Authorize:授权
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GitHupProvider provider;

    @Value("${githup.client_id}")
    private String client_id;
    @Value("${githup.client_secret}")
    private String client_secret;
    @Value("${githup.redirect_uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        final AccessTokenDTO tokenDTO = new AccessTokenDTO();
        tokenDTO.setClient_id(client_id);
        tokenDTO.setClient_secret(client_secret);
        tokenDTO.setCode(code);
        tokenDTO.setRedirect_uri(redirect_uri);
        tokenDTO.setState(state);
        final String accessToken = provider.getAccessToken(tokenDTO);
        final GitHupUser gitHupUser = provider.gitUser(accessToken);
        if (gitHupUser != null) {
            final User user = new User();
            final String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHupUser.getName());
            user.setAccount_id(String.valueOf(gitHupUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));


        } else {
            //登录失败,重新登录
        }
        return "redirect:/";
    }
}
