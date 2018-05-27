package ru.netcracker.registration.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netcracker.registration.model.UserGroup;
import ru.netcracker.registration.repository.UserGroupRepository;

@Service
@Transactional
public class UserGroupService {
    public final UserGroupRepository repository;

    public UserGroupService(UserGroupRepository repository) {
        this.repository = repository;
    }

    public void add(UserGroup group) {
        repository.save(group);
    }

    public UserGroup get(long id){
        return repository.findOne(id);
    }

    public UserGroup get(String name){ return repository.findUserGroupByName(name);}

    public Iterable<UserGroup> getAll(){
        return repository.findAll();
    }

    public UserGroup edit(UserGroup group){
        UserGroup oldGroup = get(group.getId());
        if (oldGroup != null) {
            return repository.save(group);
        }
        return null;
    }

    public void delete(long id){
        repository.delete(id);
    }

    public void delete(UserGroup group){
        repository.delete(group);
    }

}
