package service;

import model.User;

import java.util.List;

public interface UserInt {
    User getUser(String name);

    User getUser(int id);

    List<User> listAll();

    void deleteByPrimaryKey(Integer id);

    void insert(User user);

    void update(User user);
}
