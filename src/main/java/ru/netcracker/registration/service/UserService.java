package ru.netcracker.registration.service;

import org.springframework.stereotype.Service;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.converter.UserConverter;
import ru.netcracker.registration.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Сервис пользователей
 * Инкапсулирует работу с репозиторием пользователей,
 * предоставляя для этого конкретный набор методов
 * Все методы принимают и возвращают DTO, внутри преобразуя их в Entity
 * Используется контроллером
 */
@Service
public class UserService {
    public final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Добавить пользователя в БД
     * @param userDTO Пользователь
     * @throws Exception
     */
    public void add(UserDTO userDTO) throws Exception{
        if (repository.findByEmail(userDTO.getEmail()) == null) {
            User entity=UserConverter.convertToEntity(userDTO);
            repository.save(entity);
        }
        else{
            throw new Exception("User with such email is already registered");
        }
    }

    /**
     * Найти пользователя по ID
     * @param id ID пользователя
     * @return найденный пользователь
     * @throws Exception
     */
    public UserDTO get(long id) throws Exception{
        User user = repository.findOne(id);
        if(user!=null) {
            return UserConverter.convertToDTO(user);
        }
        else{
            throw new Exception("User with such ID is not registered");
        }
    }

    /**
     * Найти пользователя по email
     * @param email email пользователя
     * @return найденный пользователь
     * @throws Exception
     */
    public UserDTO get(String email) throws Exception{
        User user = repository.findByEmail(email);
        if(user!=null) {
            return UserConverter.convertToDTO(user);
        }
        else{
            throw new Exception("User with such email is not registered");
        }
    }

    /**
     * Получить список всех пользователей
     * @return Список пользователей
     */
    public Iterable<UserDTO> getAll() {
        Iterable<User> users= repository.findAll();
        List<UserDTO> dtos=new LinkedList<UserDTO>();
        for(User user:users){
            dtos.add(UserConverter.convertToDTO(user));
        }
        return dtos;
    }

    /**
     * Обновить данные о пользователе
     * @param userDTO Обновлённый пользователь
     * @throws Exception
     */
    public void edit(UserDTO userDTO) throws Exception{
        User oldUser = repository.findByEmail(userDTO.getEmail());
        if (oldUser != null) {
            oldUser=UserConverter.convertToEntity(userDTO, oldUser);
            repository.save(oldUser);
        }
        else{
            throw new Exception("Trying to edit user that is not registered");
        }
    }

    /**
     * Удалить пользователя по ID
     * @param id ID пользователя
     */
    public void delete(long id) {
        repository.delete(id);
    }

    /**
     * Удалить пользователя по email
     * @param email email пользователя
     */
    public void delete(String email) {
        repository.deleteByEmail(email);
    }
}
