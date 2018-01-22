package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.User;

/**
 * Репозиторий для пользователей
 * Позволит совершать доступ к таблице пользователей в БД
 * Используется сервисом
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
