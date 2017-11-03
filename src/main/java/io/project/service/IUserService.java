package io.project.service;

import io.project.entity.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    List<User> findAll(List<Long> userIds);

    User find(Long userId);

    Boolean exists(Long userId);

    User create(User user);

    User save(User user);

    List<User> save(List<User> users);

    void delete(Long userId);
}
