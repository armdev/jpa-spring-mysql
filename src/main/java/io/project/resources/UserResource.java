package io.project.resources;

import io.project.entity.User;
import io.project.dao.IUserDAO;
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
@RequestMapping("/api/private")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private IUserDAO userDAO;

    @GetMapping(path = "/users", produces = "application/json;charset=UTF-8")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> userList = userDAO.getList(0, 100);
        if (userList.isEmpty()) {
            return new ResponseEntity("User list is empty ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}", produces = "application/json;charset=UTF-8")
    @SuppressWarnings("unchecked")
    public ResponseEntity findUser(@PathVariable("id") Long id) {
        User user = userDAO.findById(id);
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
    @SuppressWarnings("unchecked")
    public ResponseEntity create(@RequestBody User user) {
        User savedUser = userDAO.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userDAO.delete(id);
        return new ResponseEntity("user with " + id + " deleted", HttpStatus.OK);
    }

    @PutMapping("/users/update/{id}")
    @SuppressWarnings("unchecked")
    public ResponseEntity updateUserEmail(@PathVariable Long id, @RequestBody User user) {
        int count = userDAO.updateEmail(id, user);
        if (count == 0) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
