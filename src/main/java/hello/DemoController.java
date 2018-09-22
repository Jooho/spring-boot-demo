package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.IntStream;

@RestController
public class DemoController {
    int i = 0;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World!";
    }

    @RequestMapping("/leak")
    public String populateObjects() {


        Runnable myRunnable = new Runnable() {

            public void run() {

                System.out.println("Creating object");
                Vector v = new Vector();
                int one_mega = 1048576;
                int thirty_kil = 1048576/3;
                int obj_size= one_mega;
                while (true) {
                    byte b[] = new byte[obj_size];
                    v.add(b);
                    Runtime rt = Runtime.getRuntime();
                    System.out.println("free memory: " + rt.freeMemory());
                    System.out.println("max memory: " + rt.maxMemory());
                    System.out.println("total memory: " + rt.totalMemory());
                    System.out.println("gap between max and total memory: " +  (rt.maxMemory() - rt.totalMemory()));
                    if(rt.freeMemory() < 104857600 ){
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(rt.maxMemory() -rt.totalMemory() < 104857600 ){
                        try {
                            Thread.sleep(1);
                            obj_size=thirty_kil;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };


        Thread thread = new Thread(myRunnable);
        thread.start();


        return "Generating Object! Use a lot of CPU/Memory";
    }


}
