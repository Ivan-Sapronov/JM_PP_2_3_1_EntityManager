package ru.sapronov.jm_pp_2_3_1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.sapronov.jm_pp_2_3_1.config.HibernateConfiguration;
import ru.sapronov.jm_pp_2_3_1.model.User;

import java.util.List;

/**
 * @author Ivan Sapronov
 */
@Component
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

    @Override
    public List<User> index(){

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        transaction.commit();

        return users;
    }
    @Override
    public User show(int id){

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        return user;
    }
    @Override
    public void save(User user) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
    @Override
    public void update(int id, User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        User userInDB = session.get(User.class, id);

        userInDB.setName(user.getName());
        userInDB.setSurname(user.getSurname());
        userInDB.setAge(user.getAge());
        userInDB.setEmail(user.getEmail());

        transaction.commit();
    }
    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
    }
}
