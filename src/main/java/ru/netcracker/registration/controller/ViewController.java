package ru.netcracker.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.netcracker.registration.security.service.SecurityService;

@Controller
public class ViewController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login")
    public String login() {
        return "forward:/registrationAndLogin.html";
    }

    @RequestMapping(value = "/vk")
    public String vk() {
        return "forward:/registrationAndLogin.html";
    }



    @RequestMapping(value = "/registration")
    public String registration() {
        return "forward:/registrationAndLogin.html";
    }

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

    @GetMapping(value = "/protected")
    public @ResponseBody
    ResponseEntity<?> protectedM() {
        return new ResponseEntity<>("okay", HttpStatus.OK);
    }

    @GetMapping("/testName")
    public ResponseEntity<?> getName() {
        try {
            return new ResponseEntity<Object>(
                    securityService.findLoggedInEmail(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
