package com.groyyo.user.controller;


import java.util.List;

import com.groyyo.user.model.Users;
import com.groyyo.user.model.UserDTO;
import com.groyyo.user.model.UserRequest;
import com.groyyo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
      private final UserService userService;

      @GetMapping
      public List<Users> getAllUsers() {
         return userService.getAllUsers();
      }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


      @PostMapping
      public Users createUser(@RequestBody UserRequest userRequest) {
         return userService.createUser(userRequest);
      }

      @PutMapping("/{id}")
      public Users updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
         return userService.updateUser(id, userRequest);
      }

      @PutMapping("enableUser/{id}")
      public ResponseEntity<String> activateUser(@RequestParam("enable") boolean enable, @PathVariable Long id) {
          String responseString = userService.enableUser(enable, id);
          return ResponseEntity.ok().body(responseString);
      }
}

