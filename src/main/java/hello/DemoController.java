package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.IntStream;

@RestController
public class DemoController {
    int i=0;
    @RequestMapping("/hello")
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("/leak")
    public String populateObjects(){


        Runnable myRunnable = new Runnable(){

            public void run(){

                System.out.println("Creating object");
                    Vector v = new Vector();
                    while (true)
                    {
                        byte b[] = new byte[1048576];
                        v.add(b);
                        Runtime rt = Runtime.getRuntime();
                        System.out.println( "free memory: " + rt.freeMemory() );
                    }
            }
        };

        IntStream.range(0, 10).parallel().forEach(
                nbr -> {
                    try {
                        Thread thread = new Thread(myRunnable);
                        thread.start();
                    } catch (Exception ex) {}
                    System.out.println(nbr);
                }
        );



        return "Generating Object! Use a lot of CPU/Memory";
    }



}
