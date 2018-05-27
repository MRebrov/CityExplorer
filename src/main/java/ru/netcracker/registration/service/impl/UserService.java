package ru.netcracker.registration.service.impl;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.registration.model.User;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.converter.UserConverter;
import ru.netcracker.registration.repository.UserGroupRepository;
import ru.netcracker.registration.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис пользователей
 * Инкапсулирует работу с репозиторием пользователей,
 * предоставляя для этого конкретный набор методов
 * Все методы принимают и возвращают DTO, внутри преобразуя их в Entity
 * Используется контроллером
 */
@Service
@Transactional
public class UserService {
    public final UserRepository repository;

    @Autowired
    private UserGroupRepository groupRepository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getByEmail(String email) {
        return repository.findByEmail(email);
    }


    public void ban(UserDTO userDTO){
        User user = repository.findByEmail(userDTO.getEmail());
        user.setGroupID(groupRepository.findUserGroupByName("Banned"));
        repository.save(user);
    }


    /**
     * Добавить пользователя в БД
     *
     * @param userDTO Пользователь
     * @throws Exception
     */
    public void add(UserDTO userDTO) throws Exception {
        if (repository.findByEmail(userDTO.getEmail()) == null) {
            User entity = UserConverter.convertToEntity(userDTO);
            repository.save(entity);
        } else {
            throw new Exception("User with such email is already registered");
        }
    }

    /**
     * Найти пользователя по ID
     *
     * @param id ID пользователя
     * @return найденный пользователь
     * @throws Exception
     */
    public UserDTO get(long id) throws Exception {
        User user = repository.findOne(id);
        if (user != null) {
            return UserConverter.convertToDTO(user);
        } else {
            throw new Exception("User with such ID is not registered");
        }
    }

    /**
     * Найти пользователя по email
     *
     * @param email email пользователя
     * @return найденный пользователь
     * @throws Exception
     */
    public UserDTO get(String email) throws Exception {
        User user = repository.findByEmail(email);
        if (user != null) {
            return UserConverter.convertToDTO(user);
        } else {
            throw new Exception("User with such email is not registered");
        }
    }

    /**
     * Получить список всех пользователей
     *
     * @return Список пользователей
     */
    public Iterable<UserDTO> getAll() {
        Iterable<User> users = repository.findAll();
        List<UserDTO> dtos = new LinkedList<UserDTO>();
        for (User user : users) {
            dtos.add(UserConverter.convertToDTO(user));
        }
        return dtos;
    }

    public Iterable<Integer> getAllMappedByRegistrationDate(List<User> allUsers) {
        if(allUsers == null || allUsers.isEmpty()) {
            allUsers = (ArrayList<User>) repository.findAll();
        }
        Integer res[] = new Integer[12];
        for (int i =0; i< 12; i++){
            res[i] = 0;
        }
        for (User u : allUsers) {
            res[u.getRegistrationDate().getMonthOfYear() - 1]++;
        }

        return Arrays.asList(res);
    }


    /**
     * Обновить данные о пользователе
     *
     * @param userDTO Обновлённый пользователь
     * @throws Exception
     */
    public void editPersonalInfo(UserDTO userDTO) throws Exception {
        User oldUser = repository.findByEmail(userDTO.getEmail());
        long id = oldUser.getId();
        String password = oldUser.getPassword();
        if (oldUser != null) {
            oldUser = UserConverter.convertToEntity(userDTO, oldUser);
            oldUser.setId(id);
            oldUser.setPassword(password);
            repository.save(oldUser);
        } else {
            throw new Exception("Trying to edit user that is not registered");
        }
    }

    /**
     * Обновить пароль
     *
     * @param email       email пользователя
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     * @throws Exception
     */
    public void editPassword(String email, String oldPassword, String newPassword) throws Exception {
        User oldUser = repository.findByEmail(email);
        if (oldUser != null) {
            if (oldUser.getPassword().equals(oldPassword)) {
                oldUser.setPassword(newPassword);
                repository.save(oldUser);

            } else {
                throw new Exception("Old password is not correct");
            }
        } else {
            throw new Exception("Trying to edit user that is not registered");
        }
    }

    /**
     * Удалить пользователя по ID
     *
     * @param id ID пользователя
     */
    public void delete(long id) {
        repository.delete(id);
    }

    /**
     * Удалить пользователя по email
     *
     * @param email email пользователя
     */
    public void delete(String email) {
        repository.deleteByEmail(email);
    }


    public List<UserDTO> findByPattern(UserDTO pattern){


        List<User> found;
        if (!pattern.getEmail().isEmpty() && !pattern.getFirstName().isEmpty() && !pattern.getRegistrationDate().isEmpty()){
            found = repository.findByEmailContainingOrFirstNameContainingOrRegistrationDateEquals
                    (pattern.getEmail(), pattern.getFirstName(), LocalDate.parse(pattern.getRegistrationDate(), DateTimeFormat.forPattern("YYYY-M-d")));
        }
        else {
            if(pattern.getEmail().isEmpty()) {
                if (pattern.getFirstName().isEmpty()) {
                    found = repository.findByRegistrationDateEquals
                            (LocalDate.parse(pattern.getRegistrationDate(), DateTimeFormat.forPattern("YYYY-M-d")));
                }
                else {
                    if (pattern.getRegistrationDate().isEmpty()) {
                        found = repository.findByFirstNameContaining(pattern.getFirstName());
                    } else {
                        found = repository.findByFirstNameContainingOrRegistrationDateEquals(pattern.getFirstName(), LocalDate.parse(pattern.getRegistrationDate(), DateTimeFormat.forPattern("YYYY-M-d")));
                    }
                }
            }
            else {
                if(pattern.getFirstName().isEmpty()){
                    if (pattern.getRegistrationDate().isEmpty()){
                        found = repository.findByEmailContaining(pattern.getEmail());
                    }
                    else {
                        found = repository.findByEmailContainingOrRegistrationDateEquals
                                (pattern.getEmail(), LocalDate.parse(pattern.getRegistrationDate(), DateTimeFormat.forPattern("YYYY-M-d")));
                    }
                }
                else {
                    found = repository.findByEmailContainingOrFirstNameContaining
                            (pattern.getEmail(), pattern.getFirstName());
                }
            }

        }
//                repository.findAllByEmailLikeOrFirstNameLike(pattern.getEmail(), pattern.getFirstName());

        return found.stream().filter(user->!user.getGroupID().getName().endsWith("ed")).map(UserConverter::convertToDTO).collect(Collectors.toList());
    }
}
