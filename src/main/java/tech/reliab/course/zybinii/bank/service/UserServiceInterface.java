package tech.reliab.course.zybinii.bank.service;

import tech.reliab.course.zybinii.bank.entity.User;

import java.util.List;

public interface UserServiceInterface {
    User create(User user);

    User read(Long id);

    User update(User user);

    boolean delete(Long id);

    List<User> getAllUsers();
}
