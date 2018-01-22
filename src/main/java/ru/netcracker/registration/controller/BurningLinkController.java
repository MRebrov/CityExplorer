package ru.netcracker.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.netcracker.registration.mail.burningLinks.BurningLinksManager;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.service.UserGroupService;
import ru.netcracker.registration.service.UserService;

@Controller
@RequestMapping("/confirm")
public class BurningLinkController {
    private BurningLinksManager linksManager = BurningLinksManager.getInstance();
    @Autowired
    private UserGroupService groupService;
    @Autowired
    private UserService userService;

    @GetMapping("/{code}")
    public @ResponseBody
    ResponseEntity<?> confirm(@PathVariable String code) {
        try {
            String email = linksManager.getEmailAndBurnLink(code);
            UserDTO userDTO = userService.get(email);
            userDTO.setGroupID(groupService.get("Default"));
            userService.edit(userDTO);

            return new ResponseEntity<>("User email confirmed successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
