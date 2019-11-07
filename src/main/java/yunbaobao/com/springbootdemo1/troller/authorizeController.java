package yunbaobao.com.springbootdemo1.troller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yunbaobao.com.springbootdemo1.dto.AccessTokenDTO;
import yunbaobao.com.springbootdemo1.dto.GithubUser;
import yunbaobao.com.springbootdemo1.provider.AuthorizeProvider;

@Controller
public class authorizeController {

    @Autowired
    private AuthorizeProvider authorizeProvider;

    @Value("${github.Client_id}")
    private String Client_id;

    @Value("${github.Client_secret}")
    private String Client_secret;

    @Value("${github.Redirect_uri}")
    private String Redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        String gitcode = authorizeProvider.getAccessToken(accessTokenDTO);
        GithubUser user = authorizeProvider.getUser(gitcode);
        System.out.println(user);

        return "index";
    }

}
