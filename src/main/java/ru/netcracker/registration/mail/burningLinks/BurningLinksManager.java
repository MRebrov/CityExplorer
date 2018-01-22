package ru.netcracker.registration.mail.burningLinks;

import ru.netcracker.registration.model.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BurningLinksManager {
    private static BurningLinksManager instance = null;
    private List<BurningLink> links = new ArrayList<>();
    private Timer cleanTimer = new Timer();

    private BurningLinksManager() {
        long period = 10 * 60 * 1000;
        cleanTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        links.removeIf(link -> !link.isValid());
                    }
                },
                period,
                period
        );
    }

    public static BurningLinksManager getInstance() {
        if (instance == null) {
            instance = new BurningLinksManager();
        }
        return instance;
    }

    public String addNew(String email) {
        BurningLink link = new BurningLink(email);
        links.add(link);
        return link.getLink();
    }

    public String getEmailAndBurnLink(String code) throws Exception {
        BurningLink link = getLink(code);
        if (link != null) {
            if (link.isValid()) {
                links.remove(link);
                return link.getEmail();
            } else {
                throw new Exception("Link expired");
            }
        } else {
            throw new Exception("Invalid link");
        }
    }

    private BurningLink getLink(String code) {
        for (BurningLink link : links) {
            if (link.getCode().equals(code)) {
                return link;
            }
        }
        return null;
    }
}
