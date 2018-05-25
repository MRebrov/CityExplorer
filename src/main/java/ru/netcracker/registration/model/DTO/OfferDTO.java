package ru.netcracker.registration.model.DTO;

import java.sql.Date;

public class OfferDTO {
    private Long id;
    private String name;
    private OfferCategoryDTO category;
    private String ownerName;
    private int amount;
    private Date expireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfferCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(OfferCategoryDTO category) {
        this.category = category;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
