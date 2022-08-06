package communityService.controllers;

import communityService.models.User;
import communityService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser() {
        userService.createUser();
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable Long user_id) {
        return new ResponseEntity<>(userService.getUser(user_id), HttpStatus.OK);
    }
}
