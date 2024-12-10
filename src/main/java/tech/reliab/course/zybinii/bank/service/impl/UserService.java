package tech.reliab.course.zybinii.bank.service.impl;

import tech.reliab.course.zybinii.bank.entity.User;
import tech.reliab.course.zybinii.bank.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {
    private final List<User> userStorage = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public User create(User user) {
        user.setId(idCounter++);
        userStorage.add(user);
        return user;
    }

    @Override
    public User read(Long id) {
        for (User user : userStorage) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;  // Если пользователь с таким ID не найден
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < userStorage.size(); i++) {
            if (userStorage.get(i).getId().equals(user.getId())) {
                userStorage.set(i, user);
                return user;
            }
        }
        return null;  // Если пользователь с таким ID не найден
    }

    @Override
    public boolean delete(Long id) {
        return userStorage.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage);
    }

}
