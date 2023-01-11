package com.groyyo.user.service;

import com.groyyo.user.model.Users;
import com.groyyo.user.model.UserDTO;
import com.groyyo.user.model.UserRepository;
import com.groyyo.user.model.UserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users createUser(UserRequest userRequest) {
        Users users = Users.builder().
                firstName(userRequest.getFirstName()).
                fullName(userRequest.getFullName()).
                emailId(userRequest.getEmailId()).
                phone(userRequest.getPhone()).build();
        return userRepository.save(users);
    }


    //multi- tennant
//    private User createUser(String schema, String name) {
//
//        currentTenant.setCurrentTenant(schema);
//
//        return txTemplate.execute(tx -> {
//            User user = UserRepository.named(name);
//            return userRepository.save(user);
//        });
//
//    }

    public Users updateUser(Long id, UserRequest updatedUser) {
        Users users = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        users.setEmailId(updatedUser.getEmailId());
        users.setPhone(updatedUser.getPhone());
        users.setFirstName(updatedUser.getFirstName());
        users.setFullName(updatedUser.getFullName());
        return userRepository.save(users);
    }


    public String enableUser(boolean enable, Long id) {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        users.setActive(enable);
        userRepository.save(users);
        return users.getFullName()+((enable)?" - En":"- Dis")+"abled"+" Successfully" ;
    }

    public UserDTO getUserById(Long id) {
        Users users = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(users,userDTO );
        return userDTO;
    }
}
