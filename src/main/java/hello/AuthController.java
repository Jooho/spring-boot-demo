package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    boolean misbehaveFlag = false;

    @RequestMapping("/auth")
    public ResponseEntity auth(@RequestParam String id) {
        if (!misbehaveFlag) {
            System.out.println("User(" + id + ") -" + " get auth");
            return new ResponseEntity("Auth OK", HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity("Service is not unavailable", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping("/misbehave")
    public void misbehave(){
        misbehaveFlag = true;
        System.out.println("Misbehave is enabled: /auth will return 503 error");
    }

    @RequestMapping("/recover")
    public void recover(){
        misbehaveFlag = false;
        System.out.println("Misbehave is disabled: /auth will return 503 error");

    }
}
