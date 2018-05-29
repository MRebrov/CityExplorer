package ru.netcracker.registration.controller;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.converter.UserConverter;
import ru.netcracker.registration.other.ScheduledTasts;
import ru.netcracker.registration.other.TaskPrint;
import ru.netcracker.registration.security.TokenUtils;
import ru.netcracker.registration.mail.burningLinks.BurningLinksManager;
import ru.netcracker.registration.mail.mailer.GmailSender;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.UserGroup;
import ru.netcracker.registration.security.service.SecurityService;
import ru.netcracker.registration.service.impl.UserGroupService;
import ru.netcracker.registration.service.impl.UserService;

import org.joda.time.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер, предоставляет набор REST методов, с помощью которых можно работать с базой пользователей
 * К каждому методу привязан URL адрес, по которому и осуществляется REST запрос
 */
@RestController
@RequestMapping("/userapi")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserGroupService groupService;
    @Autowired
    private SecurityService securityService;


    private List<User> allUsers = new ArrayList<>();
    public UserController(UserService service) {
        userService = service;
    }


    @GetMapping("/get/statistics")
    public @ResponseBody
    Iterable<Integer> getStatistics(){
//        tasks.change(new TaskPrint("from userapi=-=-=-=-=-=-=-=-=-=-=-"));
        return userService.getAllMappedByRegistrationDate(allUsers);
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

    @GetMapping("/get/loggedIn")
    public ResponseEntity<?> getLoggedIn() {
        try {
            return new ResponseEntity<Object>(
                    userService.get(securityService.findLoggedInEmail()),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /*
    @GetMapping("/get/byID/{id}")
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable long id) {
        try {
            UserDTO userDTO = userService.get(id);
            if(!userDTO.getEmail().equals(securityService.findLoggedInEmail())){
                userDTO.setPassword("");
            }
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }*/

    /**
     * Вернёт в json формате пользователя с указанным email
     *
     * @param email - Email пользователя
     * @return Запрашиваемый пользователь
     */
    @GetMapping("/get/byEmail/{email:.+}")
    public @ResponseBody
    ResponseEntity<?> get(@PathVariable String email) {
        try {
            UserDTO userDTO = userService.get(email);
            if (!userDTO.getEmail().equals(securityService.findLoggedInEmail())) {
                userDTO.setPassword("");
            }
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Вернёт в json формате массив всех пользователей
     *
     * @return Массив всех пользователей
     */
    @GetMapping("/get/all")
    public @ResponseBody
    Iterable<UserDTO> getAll() {
        userService.getAll().forEach(u -> {
            try {
                allUsers.add(UserConverter.convertToEntity(u));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return allUsers.stream().map(UserConverter::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Добавить нового пользователя
     * Тело запроса в формате Json содержит информацию о пользователе
     *
     * @param userDTO Json автоматически распарсится и станет объектом userDTO
     * @return Вернёт только что созданного пользователя
     */
    @PostMapping("/add")
    public ResponseEntity<?> post(@RequestBody UserDTO userDTO) {
        try {
            userDTO.setRegistrationDate(LocalDate.now().toString(DateTimeFormat.forPattern("d-M-YYYY")));
            userDTO.setGroupID(groupService.get("Unconfirmed"));
            userService.add(userDTO);
            //String token = securityService.login(userDTO.getEmail(), userDTO.getPassword());

            GmailSender sender = new GmailSender("netcracker.training.center@gmail.com", "netcracker2018");
            String link = BurningLinksManager.getInstance().addNew(userDTO.getEmail());
            String body = String.format(
                    "Hello, %s! Your email address has been used to register on our service.\nTo confirm registration, follow this link: %s",
                    userDTO.getFirstName(),
                    link
            );
            sender.sendMail("Confirm registration", body, "netcracker", userDTO.getEmail());
            //return ResponseEntity.ok(new AuthResponse(token));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/add-business")
    public ResponseEntity<?> postBusiness(@RequestBody UserDTO userDTO) {
        try {
            userDTO.setRegistrationDate(LocalDate.now().toString(DateTimeFormat.forPattern("d-M-YYYY")));
            userDTO.setGroupID(groupService.get("Unconfirmed_Business"));
            userService.addBusinessUser(userDTO);
            //String token = securityService.login(userDTO.getEmail(), userDTO.getPassword());

            GmailSender sender = new GmailSender("netcracker.training.center@gmail.com", "netcracker2018");
            String link = BurningLinksManager.getInstance().addNew(userDTO.getEmail());
            String body = String.format(
                    "Hello, %s! Your email address has been used to register a business account on our service.\nTo confirm registration, follow this link: %s",
                    userDTO.getFirstName(),
                    link
            );
            sender.sendMail("Confirm registration", body, "netcracker", userDTO.getEmail());
            //return ResponseEntity.ok(new AuthResponse(token));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("get/byCriteria")
    public @ResponseBody
    Iterable<UserDTO> getByCriteria(@RequestBody UserDTO pattern){
        return userService.findByPattern(pattern);
    }

    @PostMapping("/ban")
    public ResponseEntity<?> ban(@RequestBody UserDTO toBan){
        try {
            userService.ban(toBan);
            return new ResponseEntity<>("User with e-mail: "+toBan.getEmail()+ " has been successfully banned",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static class AuthForm {
        String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        String password;
    }

    private static class AuthResponse {
        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        String token;
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestBody AuthForm authForm) {
        try {
            UserDTO userDTO = userService.get(authForm.username);
            if(userDTO.getGroupID().getName().equals("Unconfirmed")){
                return new ResponseEntity<Object>(
                        "Please confirm your email first.",
                        HttpStatus.BAD_REQUEST
                );
            }
            if ( userDTO.getGroupID().getName().equals("Banned")){
                return new ResponseEntity<Object>("Your account have been banned",
                        HttpStatus.BAD_REQUEST);
            }
            String token = securityService.login(authForm.username, authForm.password);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    "Wrong email or password",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            securityService.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        try {
            String token = request.getHeader(TokenUtils.tokenHeader);
            String newToken = securityService.refresh(token);
            return ResponseEntity.ok(new AuthResponse(newToken));
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /**
     * Удалить пользователя с указанным email
     *
     * @param email - email пользователя
     * @return - HTTP статус OK или BAD REQUEST
     */
    @DeleteMapping("/delete/byEmail/{email:.+}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        try {
            if (email.equals(securityService.findLoggedInEmail())) {
                userService.delete(email);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else throw new Exception("Access denied: you can delete only your personal account");
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    /*
    @DeleteMapping("/delete/byID/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }*/


    @PostMapping("/edit/personalInfo")
    public ResponseEntity<?> editUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getEmail().equals(securityService.findLoggedInEmail())) {
                userService.editPersonalInfo(userDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                throw new Exception("Access denied: can edit only your own account data");
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private static class ChangePasswordForm {
        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        String email, oldPassword, newPassword;

    }

    @PostMapping("/edit/password")
    public ResponseEntity<?> editPassword(@RequestBody ChangePasswordForm form) {

        try {
            UserDTO userDTO = userService.get(form.getEmail());
            GmailSender sender = new GmailSender("netcracker.training.center@gmail.com", "netcracker2018");
            String body = String.format(
                    "Dear %s! Someone is trying to change password on your account.",
                    userDTO.getFirstName()
            );
            sender.sendMail("Password change attempt", body, "netcracker", userDTO.getEmail());

            if (form.getEmail().equals(securityService.findLoggedInEmail())) {
                userService.editPassword(form.getEmail(), form.getOldPassword(), form.getNewPassword());
                return new ResponseEntity<>(HttpStatus.OK);
            } else throw new Exception("Access denied: can edit only your own account data");
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
