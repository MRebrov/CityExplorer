package ru.netcracker.registration.model;

import javax.persistence.*;

@Entity
@Table(name="external_systems", schema = "nc_project")
public class ExternalSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }
}
