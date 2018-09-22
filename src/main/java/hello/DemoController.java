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


                int one_kil = 1024;
                int one_mega = one_kil * 1024;
                int one_giga= one_mega*1024;
                int obj_size= one_mega;
                while (true) {
                    byte b[] = new byte[obj_size];
                    v.add(b);
                    Runtime rt = Runtime.getRuntime();
                    System.out.println("free memory: " + rt.freeMemory());

                    if(rt.freeMemory() < 3*one_giga ){
                        System.out.println("Free memory is under 3G so change object size to 10 kilobyte");
                        try {
                            Thread.sleep(3);
                            obj_size=one_kil*10;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    if(rt.freeMemory() < 2*one_giga ){
                        System.out.println("Free memory is under 2G so change object size to 5 kilobyte");
                        try {
                            Thread.sleep(3);
                            obj_size=one_kil*5;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if(rt.freeMemory() < 1*one_giga ){
                        System.out.println("Free memory is under 1G  so change object size to 1 kilobyte");
                        try {
                            Thread.sleep(3);
                            obj_size=one_kil;
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
