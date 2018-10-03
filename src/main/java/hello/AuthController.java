package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthController {
    boolean misbehaveFlag = false;
    boolean longTaskFlag = false;


    @RequestMapping("/auth")
    public ResponseEntity auth(@RequestParam String id) {
        InetAddress ip;
        String hostname = "";
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!misbehaveFlag) {
            if (longTaskFlag) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("User(" + id + ") -" + " get auth");
            return new ResponseEntity(hostname + ": Auth OK", HttpStatus.OK);
        } else {
            String msg = "Misbehave is enabled: so it returns 503 error";
            System.out.println(msg);
            return new ResponseEntity(hostname + ": Service is not unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping("/misbehave")
    public ResponseEntity misbehave() {
        misbehaveFlag = true;
        String msg = "Misbehave is enabled: /auth will return 503 error";
        System.out.println(msg);
        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @RequestMapping("/misbehave/off")
    public ResponseEntity misbehaveOff() {
        misbehaveFlag = false;
        String msg = "Misbehave is disabled: /auth will return right response";
        System.out.println(msg);
        return new ResponseEntity(msg, HttpStatus.OK);

    }



    @RequestMapping("/longtask")
    public ResponseEntity longTask() {
        longTaskFlag = true;
        String msg = "Long task is enabled: /auth will return right response after 5 Secs";
        System.out.println(msg);
        return new ResponseEntity(msg, HttpStatus.OK);

    }

    @RequestMapping("/longtask/off")
    public ResponseEntity longTaskOff() {
        longTaskFlag = false;
        String msg = "Long task is disabled: /auth will return right response";
        System.out.println(msg);
        return new ResponseEntity(msg, HttpStatus.OK);

    }
}
