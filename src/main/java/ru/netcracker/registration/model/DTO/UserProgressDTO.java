package ru.netcracker.registration.model.DTO;

import ru.netcracker.registration.model.Quest;
import ru.netcracker.registration.model.UserSpotProgress;

import java.sql.Date;
import java.util.Collection;

public class UserProgressDTO {
    private UserDTO user;
    private QuestDTO quest;
    private Date dateComplete;
    private Date takingDate;
    //private Collection<UserSpotProgressDTO> userSpotProgresses;


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public QuestDTO getQuest() {
        return quest;
    }

    public void setQuest(QuestDTO quest) {
        this.quest = quest;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public Date getTakingDate() {
        return takingDate;
    }

    public void setTakingDate(Date takingDate) {
        this.takingDate = takingDate;
    }
}
