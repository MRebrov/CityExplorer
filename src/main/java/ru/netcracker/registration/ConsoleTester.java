package ru.netcracker.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.netcracker.registration.model.DTO.UserDTO;
import ru.netcracker.registration.model.UserGroup;
import ru.netcracker.registration.repository.UserGroupRepository;
import ru.netcracker.registration.service.UserService;

/**
 * Класс для проверки работы
 * добавляет группу и пользователя
 */
@Component
public class ConsoleTester  implements CommandLineRunner {
    @Autowired
    public UserService userService;
    @Autowired
    public UserGroupRepository groupRepository;
    @Override
    public void run(String... args) throws Exception{
/*
        UserGroup group = new UserGroup();
        group.setName("Unconfirmed");
        groupRepository.save(group);

        group = new UserGroup();
        group.setName("Default");
        groupRepository.save(group);

        UserDTO userDTO = new UserDTO();
        userDTO.setBirthday("12-01-1997");
        userDTO.setEmail("aaaaaa@aaaa.aa");
        userDTO.setPassword("fgd466567fgumgj6y6er");
        userDTO.setFirstName("Michael");
        userDTO.setLastName("Stevens");
        userDTO.setGroupID(group);
        userDTO.setRegistrationDate("12-08-1901");

        userService.add(userDTO);*/
    }
}
