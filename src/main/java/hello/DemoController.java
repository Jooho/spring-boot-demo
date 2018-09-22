package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("/leak")
    public void populateObjects(){
        while(true){
            String a = new String();
        }
    }



}
