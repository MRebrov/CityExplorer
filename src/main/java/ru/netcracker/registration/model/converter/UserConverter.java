package ru.netcracker.registration.model.converter;

import org.joda.time.format.DateTimeFormat;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.other.Checker;

import org.joda.time.LocalDate;

/**
 * Класс для преобразования Entity пользователя в DTO пользователя
 */
public class UserConverter {
    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBirthday(user.getBirthday().getDayOfMonth() + "-" + user.getBirthday().getMonthOfYear() + "-" + user.getBirthday().getYear());
        dto.setRegistrationDate(user.getRegistrationDate().getDayOfMonth() + "-" + user.getRegistrationDate().getMonthOfYear() + "-" + user.getRegistrationDate().getYear());
        dto.setGroupID(user.getGroupID());
        dto.setBalance(user.getBalance());

        return dto;
    }

    public static User convertToEntity(UserDTO dto, User target) throws Exception {

        String firstName = dto.getFirstName().trim();
        if (firstName.length() > 0) {
            target.setFirstName(firstName);
        } else {
            throw new Exception("First name must be not empty");
        }

        String lastName = dto.getLastName().trim();
        if (lastName.length() > 0) {
            target.setLastName(lastName);
        } else {
            throw new Exception("Last name must be not empty");
        }

        String email = dto.getEmail().trim();
        if (Checker.checkWithRegExp(email, "^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+$")) {
            target.setEmail(email);
        } else {
            throw new Exception("Email is incorrect");
        }

        String password = dto.getPassword();
        if (password.length() > 0) {
            target.setPassword(password);
        } else {
            throw new Exception("Password must be not empty");
        }

        target.setGroupID(dto.getGroupID());

        try {
            LocalDate birthdate = LocalDate.parse(dto.getBirthday(), DateTimeFormat.forPattern("d-M-YYYY"));
            target.setBirthday(birthdate);
        } catch (Exception e) {
            try {
                LocalDate birthdate = LocalDate.parse(dto.getBirthday(), DateTimeFormat.forPattern("YYYY-M-d"));
                target.setBirthday(birthdate);
            }
            catch(Exception e2){
                throw new Exception("Birthdate format must be day-month-year");
            }
        }

        try {
            LocalDate registrationDate = LocalDate.parse(dto.getRegistrationDate(), DateTimeFormat.forPattern("d-M-YYYY"));
            target.setRegistrationDate(registrationDate);
        } catch (Exception e) {
            throw new Exception("Registration date format must be day-month-year");
        }

        return target;
    }

    public static User convertToEntity(UserDTO dto) throws Exception {
        return convertToEntity(dto, new User());
    }
}
