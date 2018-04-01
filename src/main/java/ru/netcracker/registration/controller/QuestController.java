package ru.netcracker.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.photos.PhotoUpload;
import com.vk.api.sdk.objects.photos.responses.WallUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netcracker.registration.model.*;
import ru.netcracker.registration.model.DTO.QuestDTO;
import ru.netcracker.registration.model.DTO.UserProgressDTO;
import ru.netcracker.registration.model.DTO.UserSpotProgressDTO;
import ru.netcracker.registration.model.storage_emulation.QuestStorage;
import ru.netcracker.registration.security.service.SecurityService;
import ru.netcracker.registration.service.PhotoService;
import ru.netcracker.registration.service.PhotoTypeService;
import ru.netcracker.registration.service.QuestService;
import ru.netcracker.registration.service.impl.UserService;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/userapi")
public class QuestController {


    private QuestStorage questStorage = QuestStorage.getInstance();
    private Quest pendingQuest = new Quest();
    private VkApiClient vk;
    private UserActor actor;

    private static final String TEST_VK_UNLIMITED_TOKEN = "cc5326e955f98ad7a979dd4c4e93416b17dbd925bb813cdedd7588a7c021ab325ca95e0b190517ecf8533";
    private static final int MY_ID = 30327533;


    @Autowired
    private PhotoTypeService photoTypeService;
    @Autowired
    private QuestService questService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;


    public QuestController() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
    }

    @PostMapping("/upload-photo")
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            //ObjectMapper mapper = new ObjectMapper();
            //QuestDTO quest = mapper.readValue(questDTO, QuestDTO.class);
            //quest.getName();
            File convertFile = new File(file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            fos.close();
            Photo spotPhoto = new Photo();

            actor = new UserActor(MY_ID, TEST_VK_UNLIMITED_TOKEN);

            PhotoUpload serverResponse = vk.photos().getWallUploadServer(actor).execute();
            WallUploadResponse uploadResponse = vk.upload().photoWall(serverResponse.getUploadUrl(), convertFile).execute();
            // vk.wall().post(actor).message("#cityexplorer").

            List<com.vk.api.sdk.objects.photos.Photo> photoList = vk.photos()
                    .saveWallPhoto(actor, uploadResponse.getPhoto())
                    .server(uploadResponse.getServer())
                    .hash(uploadResponse.getHash())
                    .execute();
            com.vk.api.sdk.objects.photos.Photo photo = photoList.get(0);
            convertFile.delete();
//
//            spotPhoto.setPhotoTypeByTypeId(photoTypeService.getByType("spot"));
//            spotPhoto.setUser(userService.getByEmail(securityService.findLoggedInEmail()));
//            spotPhoto.setUploadDate(Date.valueOf(LocalDate.now()));
//            spotPhoto.setUrl(photo.getPhoto604());
//            SpotInQuest spotInQuest = new SpotInQuest();
//            spotInQuest.setPhotoByPhotoId(spotPhoto);
//            //pendingQuest.getSpotInQuests().add(spotInQuest);
//            questService.save(quest, spotPhoto, userService.getByEmail(securityService.findLoggedInEmail()));
//            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.ok(photo.getPhoto604());
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload-info")
    public ResponseEntity<?> uploadInfo(@RequestBody QuestDTO questDTO) {
        User user = userService.getByEmail(securityService.findLoggedInEmail());
        questService.save(questDTO, user);
        try {
            return ResponseEntity.ok("added successfully");
        } catch (Exception e) {
            return new ResponseEntity<Object>("failed to add the quest", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-quests")
    public @ResponseBody
    Iterable<QuestDTO> getQuests() {
        List<QuestDTO> re = questService.getAllToDTO();
        return questService.getAllToDTO();
    }


    @GetMapping("/get-closest-quests/{lat}/{lng}/{range}")
    public @ResponseBody
    Iterable<QuestDTO> getQuests(@PathVariable double lat, @PathVariable double lng, @PathVariable double range) {
        List<QuestDTO> res = questService.getAllInRange(lat, lng, range);
        return res;
    }

    @GetMapping("/get-progresses-by-user/")
    public @ResponseBody
    Iterable<UserProgressDTO> getProgressByUser() {
        String email = securityService.findLoggedInEmail();
        return questService.getUserProgressByUser(email);
    }

    @GetMapping("/get-progress-for-quest/{questId}")
    public @ResponseBody
    ResponseEntity<?> getProgressByName(@PathVariable Long questId) {
        try {
            String email = securityService.findLoggedInEmail();
            UserProgressDTO userProgressDTO = questService.getUserProgressByUserAndQuest(email, questId);
            return new ResponseEntity<Object>(
                    userProgressDTO,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/get-quest-by-id/{questId}")
    public @ResponseBody
    ResponseEntity<?> getQuestByName(@PathVariable Long questId) {
        try {
            QuestDTO questDTO = questService.getById(questId);
            return new ResponseEntity<Object>(
                    questDTO,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/join-quest/{questId}")
    public @ResponseBody
    ResponseEntity<?> joinQuest(@PathVariable Long questId) {
        try {
            questService.userJoinQuest(securityService.findLoggedInEmail(), questId);
            return ResponseEntity.ok("User joined quest");
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    private static class PostSpotPhotoForm {
        private String url;
        private Long questId;
        private Long spotId;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Long getQuestId() {
            return questId;
        }

        public void setQuestId(Long questId) {
            this.questId = questId;
        }

        public Long getSpotId() {
            return spotId;
        }

        public void setSpotId(Long spotId) {
            this.spotId = spotId;
        }
    }

    @PostMapping("/post-spot-photo")
    public @ResponseBody
    ResponseEntity<?> postSpotPhoto(@RequestBody PostSpotPhotoForm form) {
        try {
            String email = securityService.findLoggedInEmail();
            photoService.save(email, form.url, form.questId, form.spotId);
            questService.userCompleteSpot(email, form.questId, form.spotId);
            return ResponseEntity.ok("Photo was posted");
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
