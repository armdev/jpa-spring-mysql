package io.project.service;

import io.project.entity.User;
import io.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(List<Long> userIds) {
        return userRepository.findAll(userIds);
    }

    @Override
    public User find(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public Boolean exists(Long userId) {
        return userRepository.exists(userId);
    }

    @Override
    public User create(User userDTO) {
        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        //user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> save(List<User> users) {
        return userRepository.save(users);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}
