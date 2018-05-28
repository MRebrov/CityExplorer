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

    @RequestMapping(value = "/userquests")
    public String quests() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/notfound")
    public String notfound() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/mainpage")
    public String mainpage() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/newquest")
    public String newquest() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/newoffer")
    public String newoffer() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/offers")
    public String offers() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/finishgoogleauth/*")
    public String finishGoogleAuth() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/login/*")
    public String login() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/vk")
    public String vk() {
        return "forward:/index.html";
    }

    @RequestMapping(value ="/map")
    public String map(){
        return "forward:/index.html";
    }

    @RequestMapping(value ="/admin")
    public String admin(){
        return "forward:/index.html";
    }

    @RequestMapping(value ="/userPage")
    public String userPage(){
        return "forward:/index.html";
    }

    @RequestMapping(value = "/questpage/*")
    public String questPage() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/confirmations")
    public String confirmations() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/registration")
    public String registration() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/mainpage";
    }

    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirect() {
        return "forward:/notfound";
    }

    @GetMapping(value = "/protected")
    public @ResponseBody
    ResponseEntity<?> protectedM() {
        return new ResponseEntity<>("okay", HttpStatus.OK);
    }

}
