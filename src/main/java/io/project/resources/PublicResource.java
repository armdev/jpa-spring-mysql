package io.project.resources;

import io.project.entity.User;
import io.project.exceptions.BadRequestException;
import io.project.exceptions.NoContentException;
import io.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class PublicResource {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        User user = userService.find(id);

        if (user == null) {
            throw new BadRequestException();
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            throw new NoContentException();
        }
        return users;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User newUser(@RequestBody User user) throws BadRequestException {
        return userService.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User updateUser(@PathVariable Long id, @RequestBody User user) {

        if (!userService.exists(id)) {
            throw new BadRequestException();
        }

        user.setId(id);
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        if (!userService.exists(id)) {
            throw new BadRequestException();
        }
        userService.delete(id);
    }
}
