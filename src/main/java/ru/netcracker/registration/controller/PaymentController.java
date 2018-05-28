package ru.netcracker.registration.controller;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.security.service.SecurityService;
import ru.netcracker.registration.service.impl.UserGroupService;
import ru.netcracker.registration.service.impl.UserService;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    private String pwd2test = "QG584uSTZqoSYu6vET7b";

    @GetMapping("/payment-done")
    public ResponseEntity paymentDone(@RequestParam(value = "OutSum") String outSum, @RequestParam(value = "InvId") String invId, @RequestParam(value = "SignatureValue") String signatureValue) {
        try {
            String hashable = outSum + ":" + invId + ":" + pwd2test;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(hashable.getBytes());
            byte[] digest = md.digest();
            String hashtext = DatatypeConverter.printHexBinary(digest);
            System.out.println("Received hash: "+signatureValue);
            System.out.println("Calculated hash: "+hashtext);
            if (hashtext.compareToIgnoreCase(signatureValue)==0) {
                System.out.println("Hashes match");
                UserDTO userDTO = userService.get(invId);
                System.out.println("User got");
                if (userDTO != null && userDTO.getGroupID().getName().equals("Business")) {
                    System.out.println("User is business");
                    userDTO.setBalance(userDTO.getBalance() + Double.valueOf(outSum).longValue());
                    userService.editPersonalInfo(userDTO);
                    System.out.println("User saved");
                }
            }
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
