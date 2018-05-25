package ru.netcracker.registration.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_offer", schema = "nc_project")
public class UserOffer {
    private Long id;
    private User user;
    private Offer offer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOffer userOffer = (UserOffer) o;
        return Objects.equals(id, userOffer.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
