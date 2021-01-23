package ru.sapronov.jm_pp_2_3_1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sapronov.jm_pp_2_3_1.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * @author Ivan Sapronov
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<User> index(){
        List<User> users = emf.createEntityManager().createQuery("SELECT u from User u").getResultList();
        return users;
    }

    @Override
    public User show(int id){
        User user = emf.createEntityManager().find(User.class, id);
        return user;
    }

    @Override
    public void save(User user) {
        emf.createEntityManager().persist(user);
    }

    @Override
    public void update(int id, User user) {
        User userInDB = emf.createEntityManager().find(User.class, id);
        userInDB.setName(user.getName());
        userInDB.setSurname(user.getSurname());
        userInDB.setAge(user.getAge());
        userInDB.setEmail(user.getEmail());
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = emf.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
