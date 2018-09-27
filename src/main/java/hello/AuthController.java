package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @RequestMapping("/auth")
    public String auth(@RequestParam String id){
        System.out.println(id + " get auth" );
        return "Auth OK";
    }
}
