package ru.sapronov.jm_pp_2_3_1.dao;

import org.springframework.stereotype.Repository;
import ru.sapronov.jm_pp_2_3_1.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ivan Sapronov
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index(){
        List<User> users = entityManager.createQuery("SELECT u from User u").getResultList();
        return users;
    }

    @Override
    public User show(int id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User userInDB = entityManager.find(User.class, id);
        userInDB.setName(user.getName());
        userInDB.setSurname(user.getSurname());
        userInDB.setAge(user.getAge());
        userInDB.setEmail(user.getEmail());
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
