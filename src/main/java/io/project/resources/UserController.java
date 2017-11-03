package io.project.resources;


import io.project.entity.User;
import io.project.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;    

    @GetMapping(path = "/user/{id}", produces = "application/json;charset=UTF-8")
    public User find(@PathVariable Long id) {
        log.debug("REST request to get one");
        System.out.println("id is  " + id);
        User model = userRepository.findById(id);
        return model;
    }

    @PostMapping(
        path = "/user/save",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<User> save(@RequestBody User user) {
        log.debug("REST request to insert one");
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok().body(savedUser);
    }
    
}
