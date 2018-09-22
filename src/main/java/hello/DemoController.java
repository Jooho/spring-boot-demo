package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.IntStream;

@RestController
public class DemoController {


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
                int one_giga = one_mega * 1024;
                int obj_size = one_mega;
                while (true) {


                    Runtime rt = Runtime.getRuntime();
                    System.out.println("free memory: " + rt.freeMemory());
                    if (! (rt.freeMemory() < 300 * one_mega)) {
                        byte b[] = new byte[obj_size];
                        v.add(b);
                    }

//                    if (rt.freeMemory() < 300 * one_mega) {
//                        System.out.println("Free memory is under 300M so change object size to 100 kilobyte");
//                        try {
//                            Thread.sleep(3);
//                            obj_size = one_kil * 100;
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }


//                    if (rt.freeMemory() < 100 * one_mega) {
//                        System.out.println("Free memory is under 100M so change object size to 10 kilobyte");
//                        try {
//                            Thread.sleep(3);
//                            obj_size = one_kil * 10;
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    if (rt.freeMemory() < 10 * one_mega) {
//                        System.out.println("Free memory is under 10M  so change object size to 1 kilobyte");
//                        try {
//                            Thread.sleep(3);
//                            obj_size = one_kil;
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }

                }
            }
        };


        Thread thread = new Thread(myRunnable);
        thread.start();


        return "Generating Object! Use a lot of Memory";
    }


    @RequestMapping("/cpu-load")
    public String cpuLoad() {
        Thread thread;
        Runnable myRunnable = new Runnable() {
            double load = 0.8;
            final long duration = 100000;

            public void run() {
                long startTime = System.currentTimeMillis();
                try {
                    // Loop for the given duration
                    while (System.currentTimeMillis() - startTime < duration) {
                        // Every 100ms, sleep for the percentage of unladen time
                        if (System.currentTimeMillis() % 100 == 0) {
                            Thread.sleep((long) Math.floor((1 - load) * 100));
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        };

        System.out.println("Loading CPU");
        int numCore = 4;
        int numThreadsPerCore = 2;
        for (int i = 0; i < numCore * numThreadsPerCore; i++) {
            thread = new Thread(myRunnable);
            thread.start();
        }

        return "Generating Object! Use a lot of CPU";
    }
}
