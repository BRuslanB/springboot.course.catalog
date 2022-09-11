package kz.bitlab.springboot.course.catalog.services;

import kz.bitlab.springboot.course.catalog.model.User;

public interface UserService {
    User registerUser(User user);
    User updatePassword(User user, String oldPassword, String newPassword);
}
