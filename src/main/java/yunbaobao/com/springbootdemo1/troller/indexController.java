package yunbaobao.com.springbootdemo1.troller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
class IndexController {

    @GetMapping("/")
     public String hello(){


          return "index";
    }

}
