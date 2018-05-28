package ru.netcracker.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netcracker.registration.model.DTO.OfferCategoryDTO;
import ru.netcracker.registration.model.DTO.OfferDTO;
import ru.netcracker.registration.model.DTO.UserOfferDTO;
import ru.netcracker.registration.security.service.SecurityService;
import ru.netcracker.registration.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/userapi")
public class OfferController {
    @Autowired
    OfferService offerService;
    @Autowired
    SecurityService securityService;

    public OfferController(){

    }

    @GetMapping("/get-offers/{amount}/{portion}")
    public @ResponseBody
    ResponseEntity<?> getOffers(@PathVariable int amount, @PathVariable int portion) {

        List<OfferDTO> offers = offerService.getOffers(amount, portion);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/get-offers-by-category/{categoryId}/{amount}/{portion}")
    public @ResponseBody
    ResponseEntity<?> getOffersByCategory(@PathVariable Long categoryId,@PathVariable int amount, @PathVariable int portion) {

        List<OfferDTO> offers = offerService.getOffersByCategory(categoryId, amount, portion);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/get-my-offers")
    public @ResponseBody
    ResponseEntity<?> getMyOffers() {
        String email = securityService.findLoggedInEmail();

        if (email == null) {
            return new ResponseEntity<Object>(
                    "Must be authorized to get your offers",
                    HttpStatus.UNAUTHORIZED
            );
        }

        List<UserOfferDTO> userOfferDTOS = offerService.getUserOffers(email);
        return ResponseEntity.ok(userOfferDTOS);
    }

    @GetMapping("/get-owned-offers")
    public @ResponseBody
    ResponseEntity<?> getOwnedOffers() {
        String email = securityService.findLoggedInEmail();

        if (email == null) {
            return new ResponseEntity<Object>(
                    "Must be authorized to get owned offers",
                    HttpStatus.UNAUTHORIZED
            );
        }

        List<OfferDTO> offerDTOS = offerService.getOwnedOffers(email);
        return ResponseEntity.ok(offerDTOS);
    }

    @GetMapping("/get-categories")
    public @ResponseBody
    ResponseEntity<?> getCategories() {
        List<OfferCategoryDTO> categoryDTOS = offerService.getCategories();
        return ResponseEntity.ok(categoryDTOS);
    }

    @PostMapping("/save-offer")
    public @ResponseBody
    ResponseEntity<?> saveOffer(@RequestBody OfferDTO offerDTO) {
        try {
            String email = securityService.findLoggedInEmail();

            if (email == null) {
                return new ResponseEntity<Object>(
                        "Must be authorized to get your offers",
                        HttpStatus.UNAUTHORIZED
                );
            }

            offerService.saveOffer(offerDTO, email);

            return ResponseEntity.ok("saved successfully");
        } catch (Exception e) {
            return new ResponseEntity<Object>("failed to save the offer", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/purchase-offer/{offerId}")
    public @ResponseBody
    ResponseEntity<?> purchaseOffer(@PathVariable Long offerId) {
        try {
            String email = securityService.findLoggedInEmail();

            if (email == null) {
                return new ResponseEntity<Object>(
                        "Must be authorized to get your offers",
                        HttpStatus.UNAUTHORIZED
                );
            }

            offerService.purchaseOffer(offerId, email);

            return ResponseEntity.ok("purchased successfully");
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
