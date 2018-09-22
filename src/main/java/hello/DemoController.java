package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("/leak")
    public String populateObjects(){


        Runnable myRunnable = new Runnable(){

            public void run(){
                int i=0;
                System.out.println("Creating object");
                while(true){
                    ArrayList list = new ArrayList(100);
                    String a = new String();
                    System.out.println(i++);
                }
            }
        };

        Thread thread = new Thread(myRunnable);
        thread.start();

        return "Generating Object! Use a lot of CPU/Memory";
    }



}
