package ru.netcracker.registration.model.DTO;

import ru.netcracker.registration.model.UserGroup;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * DTO пользователя
 * Почти эквиваленте н пользователю, но
 * содержит только те поля, которые могут понадобиться вне backend
 */
public class UserDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public UserGroup getGroupID() {
        return groupID;
    }

    public void setGroupID(UserGroup groupID) {
        this.groupID = groupID;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(Long businessBalance) {
        this.businessBalance = businessBalance;
    }

    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private String registrationDate;
    private UserGroup groupID;
    private Long balance;
    private Long businessBalance;
    private Integer reports;
}
