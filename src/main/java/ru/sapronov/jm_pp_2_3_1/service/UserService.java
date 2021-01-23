package ru.sapronov.jm_pp_2_3_1.service;

import ru.sapronov.jm_pp_2_3_1.model.User;

import java.util.List;

/**
 * @author Ivan Sapronov
 */
public interface UserService {
    List<User> index();

    User show(int id);

    void save(User user);

    void update(int id, User user);

    void delete(int id);
}
