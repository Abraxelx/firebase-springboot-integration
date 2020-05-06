package com.risaleinurdanvecizeler.quotesservice.controller;

import com.risaleinurdanvecizeler.quotesservice.model.Person;
import com.risaleinurdanvecizeler.quotesservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {
    final
    UserService firebaseService;

    public UserController(UserService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @RequestMapping("/fetchUser")
    public Person getUserDetails(@RequestHeader() String userName) throws ExecutionException, InterruptedException {
        return firebaseService.fetchUserDetails(userName);
    }
    @PostMapping("/createUser")
    public String  postNewUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseService.createNewUser(person);
    }
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return  firebaseService.updateUser(person);
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestHeader String username) throws ExecutionException, InterruptedException {
        return firebaseService.deleteUser(username);
    }
}
