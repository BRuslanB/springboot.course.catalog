package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    User updatePassword(User user, String oldPassword, String newPassword);
    User getUser(Long id);
    User getUser(String userName);
    List<User> getAllUsers();
    User saveUser(User user);
}
