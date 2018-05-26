package ru.netcracker.registration.repository;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.netcracker.registration.model.User;

import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

/**
 * Репозиторий для пользователей
 * Позволит совершать доступ к таблице пользователей в БД
 * Используется сервисом
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
//    List<User> findAllByEmailLikeOrFirstNameLikeOrRegistrationDateEquals
//            (String email, String firstName, LocalDate registrationDate);
    List<User> findAllByEmailLikeOrFirstNameLike(String email, String firstName);
    List<User> findByEmailContainingOrFirstNameContainingOrRegistrationDateEquals
            (String email, String firstName, LocalDate registrationDate);
    List<User> findByEmailContaining(String email);
    List<User> findByFirstNameContaining(String firstName);
    List<User> findByRegistrationDateEquals(LocalDate registrationDate);
    List<User> findByEmailContainingOrFirstNameContaining(String email, String firstName);
    List<User> findByEmailContainingOrRegistrationDateEquals(String email, LocalDate registrationDate);
    List<User> findByFirstNameContainingOrRegistrationDateEquals(String firstName, LocalDate registrationDate);
    void deleteByEmail(String email);
}
