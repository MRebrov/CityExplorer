package ru.netcracker.registration.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "spot_in_quest", schema = "nc_project", catalog = "vrbqprvx")
public class SpotInQuest {
    private Integer spotsInQuestsId;
    private Spot spotBySpotId;
    private Photo photoByPhotoId;
    private Collection<UserSpotProgress> userSpotProgressesBySpotsInQuestsId;
    private Quest quest;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "quest_id", referencedColumnName = "quest_id", nullable = false)
    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spots_in_quests_id")
    public Integer getSpotsInQuestsId() {
        return spotsInQuestsId;
    }

    public void setSpotsInQuestsId(Integer spotsInQuestsId) {
        this.spotsInQuestsId = spotsInQuestsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotInQuest that = (SpotInQuest) o;

        if (spotsInQuestsId != null ? !spotsInQuestsId.equals(that.spotsInQuestsId) : that.spotsInQuestsId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return spotsInQuestsId != null ? spotsInQuestsId.hashCode() : 0;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public Spot getSpotBySpotId() {
        return spotBySpotId;
    }

    public void setSpotBySpotId(Spot spotBySpotId) {
        this.spotBySpotId = spotBySpotId;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "photo_id", referencedColumnName = "photo_id", nullable = false)
    public Photo getPhotoByPhotoId() {
        return photoByPhotoId;
    }

    public void setPhotoByPhotoId(Photo photoByPhotoId) {
        this.photoByPhotoId = photoByPhotoId;
    }

    @OneToMany(mappedBy = "spotsInQuestsBySpotInQuestId")
    public Collection<UserSpotProgress> getUserSpotProgressesBySpotsInQuestsId() {
        return userSpotProgressesBySpotsInQuestsId;
    }

    public void setUserSpotProgressesBySpotsInQuestsId(Collection<UserSpotProgress> userSpotProgressesBySpotsInQuestsId) {
        this.userSpotProgressesBySpotsInQuestsId = userSpotProgressesBySpotsInQuestsId;
    }
}
