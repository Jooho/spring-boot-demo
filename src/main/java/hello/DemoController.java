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


        Runnable myRunnable = new Runnable(){

            public void run(){
                System.out.println("Creating object");
                while(true){
                    String a = new String();
                }
            }
        };

        Thread thread = new Thread(myRunnable);
        thread.start();
    }



}
