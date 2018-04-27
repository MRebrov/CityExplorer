package ru.netcracker.registration.controller;

import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.netcracker.registration.mail.burningLinks.BurningLinksManager;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.service.impl.UserGroupService;
import ru.netcracker.registration.service.impl.UserService;

import javax.security.auth.login.CredentialExpiredException;
import java.net.URI;

@Controller
@RequestMapping("/confirm")
public class BurningLinkController {
    private BurningLinksManager linksManager = BurningLinksManager.getInstance();
    @Autowired
    private UserGroupService groupService;
    @Autowired
    private UserService userService;

    @GetMapping("/{code}")
    public String confirm(@PathVariable String code) {
        try {
            String email = linksManager.getEmailAndBurnLink(code);
            UserDTO userDTO = userService.get(email);
            userDTO.setGroupID(groupService.get("Default"));
            userService.editPersonalInfo(userDTO);

            return "redirect:/login/activated";
        } catch (CredentialExpiredException e) {
             return "redirect:/login/expired";
        }
        catch (InvalidCredentialsException e){
            return "redirect:/login/invalid";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
