package ru.netcracker.registration.controller;

import com.vk.api.sdk.client.ClientResponse;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.apps.responses.GetResponse;
import com.vk.api.sdk.objects.wall.responses.SearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class VKController {

    private static final int APP_ID = 6346913;
    private static final String SECURE_KEY = "KSyvMNJDmCkP7bg6K1ms";
    private static final String SERVICE_TOKEN = "11195acd11195acd11195acd671179826c1111911195acd4b7318340fa7ea0bd93d7565";
    private static String code = "";

    private VkApiClient vk;
    private UserActor actor;

//    public VKController(){
//        TransportClient transportClient = HttpTransportClient.getInstance();
//        vk = new VkApiClient(transportClient);
//
//        try {
//            ClientResponse cr = transportClient
//                    .get("https://oauth.vk.com/authorize?client_id=6346913&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,photos&response_type=code&v=5.71");
//            cr.getContent();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //UserAuthResponse authResponse = vk.oauth()
//               // .userAuthorizationCodeFlow(APP_ID, SECURE_KEY, "https://oauth.vk.com/blank.html", );
//    }

    @GetMapping("/photo")
    public void getPhoto(HttpServletResponse response, HttpServletRequest request){
        SearchResponse searchResponse = null;
        try {
            searchResponse = vk.wall().search(actor)
                    .ownerId(actor.getId())
                    .query("солдаты").execute();
            //searchResponse.getItems().get(0).getAttachments().get(1).getPhoto().getPhoto1280();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect(searchResponse.getItems().get(0).getAttachments().get(0).getPhoto().getPhoto1280());
        } catch (IOException e) {
            e.printStackTrace();
        }

//            ModelAndView mav = new ModelAndView("redirect:https://oauth.vk.com/authorize?client_id=6346913&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,photos&response_type=code&v=5.71");
//            return mav;
            //test.forEach((key, value) -> System.out.println(key + "=-=-=" + value));
    }

    @GetMapping("/signin")
    public void signIn(HttpServletResponse response){
        if (!code.isEmpty()){
            TransportClient transportClient = HttpTransportClient.getInstance();
            vk = new VkApiClient(transportClient);
            try {
                UserAuthResponse authResponse = vk.oauth()
                        .userAuthorizationCodeFlow(APP_ID, SECURE_KEY, "http://localhost:8081/callback", code)
                        .execute();
                actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
                response.sendRedirect("/photo");
            } catch (ApiException | ClientException | IOException e) {
                e.printStackTrace();
            }

        }
        else {
            try {
                //return new ModelAndView("redirect:https://oauth.vk.com/authorize?client_id=6346913&display=page&redirect_uri=http://localhost:8081/callback&scope=friends,wall,photos&response_type=code&v=5.71");
                response.sendRedirect("https://oauth.vk.com/authorize?client_id=6346913&display=page&redirect_uri=http://localhost:8081/callback&scope=friends,wall,photos&response_type=code&v=5.71");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/callback")
    public ModelAndView callBack(HttpServletRequest request){
        code = request.getQueryString().split("=")[1];
        return new ModelAndView("redirect:/signin");
    }
}
