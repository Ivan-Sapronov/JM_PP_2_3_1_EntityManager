package ru.sapronov.jm_pp_2_3_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sapronov.jm_pp_2_3_1.dao.UserDAO;
import ru.sapronov.jm_pp_2_3_1.model.User;

import java.util.List;

/**
 * @author Ivan Sapronov
 */
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    @Override
    public List<User> index() {
        return userDAO.index();
    }

    @Transactional(readOnly = true)
    @Override
    public User show(int id) {
        return userDAO.show(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
