package io.project.resources;

import io.project.entity.User;
import io.project.repository.UserRepository;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users", produces = "application/json;charset=UTF-8")
    public List<User> findAllUsers() {
        return userRepository.getList(0, 100);
    }

    @GetMapping(path = "/users/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity findUser(@PathVariable("id") Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping(
            path = "/users/create",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity create(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userRepository.delete(id);
        return new ResponseEntity("user with " + id + " deleted", HttpStatus.OK);
    }

    @PutMapping("/users/update/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity updateUserEmail(@PathVariable Long id, @RequestBody User user) {
        int count = userRepository.updateEmail(id, user);
        if (count == 0) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
