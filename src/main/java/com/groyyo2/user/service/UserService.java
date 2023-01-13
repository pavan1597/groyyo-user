package com.groyyo2.user.service;

import com.groyyo2.user.model.Users;
import com.groyyo2.user.model.UserDTO;
import com.groyyo2.user.model.UserRepository;
import com.groyyo2.user.model.UserRequest;
import com.groyyo2.user.producer.NotificationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
@Slf4j
public class UserService {

    @Value("${kafka.topic.usermgt_message}")
    private String slackMessageTopic;
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


    @Autowired
    private NotificationProducer notificationProducer ;


    public void createUser(UserRequest userRequest) {


        notificationProducer.publish("user-service-2", userRequest.getEmailId(), userRequest);
//        Users users = Users.builder().
//                firstName(userRequest.getFirstName()).
//                fullName(userRequest.getFullName()).
//                emailId(userRequest.getEmailId()).
//                phone(userRequest.getPhone()).build();

        log.info("messages p[roduced to kafka ");
//
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
