package project.bookreview.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookreview.domain.Role;
import project.bookreview.domain.User;
import project.bookreview.repository.CommentRepository;
import project.bookreview.repository.RoleRepository;
import project.bookreview.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static project.bookreview.domain.RolesEnum.USER;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.commentRepository = commentRepository;
    }

    //@Transactional
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

//    private UserRequest convertToUserRequest(User user) {
//        UserRequest userRequest = new UserRequest();
//        userRequest.setUsername(user.getUsername());
//        userRequest.setPassword(user.getPassword());
//        userRequest.setEmail(user.getEmail());
//        userRequest.setAge(user.getAge());
//        return userRequest;
//    }

    public User createUser(User user) {
        // Set role to USER
        Role userRole = roleRepository.findByRoleName(USER); // Assuming you have a role with name "USER"
        user.setRoles(Collections.singleton(userRole));

        userRole.getUsers().add(user);//si asta
        user.setPassword(user.getPassword());

        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String password) {

        User user = userRepository.findByUsername(username);

        return user != null && user.getPassword().equals(password);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setAge(updatedUser.getAge());
                    existingUser.setPassword(updatedUser.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElse(null); // Handle not found scenario
    }

//    public void deleteUserById(Long userId) {
//        userRepository.deleteById(userId);
//    }

    @Transactional
    public void deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            //delete comments of user
            commentRepository.deleteByUser(user);

            user.setRoles(null); // Detach roles from the user

            userRepository.save(user); // Save user without roles

            Role userRole = roleRepository.findByRoleName(USER);
            userRole.getUsers().remove(user);

            // delete user
            userRepository.deleteById(userId);
        }
    }
}

