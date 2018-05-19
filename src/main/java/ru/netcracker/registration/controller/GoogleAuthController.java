package ru.netcracker.registration.controller;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.security.service.SecurityService;
import ru.netcracker.registration.service.impl.UserGroupService;
import ru.netcracker.registration.service.impl.UserService;

@Controller
@RequestMapping("/googleauth")
public class GoogleAuthController {
    @Autowired
    private UserGroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    private String client_id = "416979126475-ttu7rv5k8vjbu4hhv77op8tfnjubksqd.apps.googleusercontent.com";
    private String client_secret = "H_SuLxG8nz25J-dzvWy_lpaX";

    private static class AccessTokenResponse {
        private String access_token;
        private String token_type;
        private Integer expires_in;
        private String id_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public Integer getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(Integer expires_in) {
            this.expires_in = expires_in;
        }

        public String getId_token() {
            return id_token;
        }

        public void setId_token(String id_token) {
            this.id_token = id_token;
        }
    }

    ;

    private static class GoogleUserDTO {
        private String id;
        private String email;
        private String verified_email;
        private String name;
        private String given_name;
        private String family_name;
        private String link;
        private String picture;
        private String gender;
        private String locale;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getVerified_email() {
            return verified_email;
        }

        public void setVerified_email(String verified_email) {
            this.verified_email = verified_email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGiven_name() {
            return given_name;
        }

        public void setGiven_name(String given_name) {
            this.given_name = given_name;
        }

        public String getFamily_name() {
            return family_name;
        }

        public void setFamily_name(String family_name) {
            this.family_name = family_name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }
    }

    @GetMapping("/authenticate")
    public String googleAuth(@RequestParam(value = "code") String code) {
        try {
            String url = "https://accounts.google.com/o/oauth2/token";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("code", code);
            map.add("client_id", client_id);
            map.add("client_secret", client_secret);
            map.add("grant_type", "authorization_code");
            map.add("redirect_uri", "https://localhost:8080/googleauth/authenticate");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AccessTokenResponse> result = restTemplate.postForEntity(url, request , AccessTokenResponse.class);

            AccessTokenResponse accessTokenResponse=result.getBody();

            /////////////
            GoogleUserDTO googleUserDTO = restTemplate.getForObject(
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessTokenResponse.access_token,
                    GoogleUserDTO.class
            );

            /////////////
            UserDTO userDTO;
            String pseudoEmail = googleUserDTO.getEmail();
            if (userService.getByEmail(pseudoEmail) == null) {
                userDTO = new UserDTO();
                userDTO.setEmail(pseudoEmail);
                String password = accessTokenResponse.id_token.substring(10,34);
                userDTO.setPassword(password);//makes no sense
                userDTO.setFirstName(googleUserDTO.getGiven_name());
                userDTO.setLastName(googleUserDTO.getFamily_name());
                userDTO.setBirthday("1-1-1997");
                userDTO.setRegistrationDate(LocalDate.now().toString(DateTimeFormat.forPattern("d-M-YYYY")));
                userDTO.setGroupID(groupService.get("Default"));

                userService.add(userDTO);
            }

            userDTO = userService.get(pseudoEmail);
            if(userDTO.getGroupID().getName().equals("Unconfirmed")) {
                userDTO.setGroupID(groupService.get("Default"));
                userService.editPersonalInfo(userDTO);
            }
            String id_token = securityService.login(userDTO.getEmail(), userDTO.getPassword());

            return "redirect:/finishgoogleauth/" + id_token;
        } catch (Exception e) {
            return "redirect:/map";
        }
    }
}
