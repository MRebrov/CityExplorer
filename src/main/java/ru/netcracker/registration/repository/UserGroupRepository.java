package ru.netcracker.registration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.netcracker.registration.model.UserGroup;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    UserGroup findUserGroupByName(String name);
}
