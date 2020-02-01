package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GitHupUser;
import life.majiang.community.provider.GitHupProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;

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
    private  String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        final AccessTokenDTO tokenDTO = new AccessTokenDTO();
        tokenDTO.setClient_id(client_id);
        tokenDTO.setClient_secret(client_secret);
        tokenDTO.setCode(code);
        tokenDTO.setRedirect_uri(redirect_uri);
        tokenDTO.setState(state);
        final String accessToken = provider.getAccessToken(tokenDTO);
        final GitHupUser user = provider.gitUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
