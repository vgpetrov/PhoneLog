package ru.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.db.entities.User;

/**
 * Created by vitaly on 23/02/15.
 */
@Repository
public class UserDAO extends GenericDAO<User> {

    public UserDAO() {
    }

    /**
     * Create user
     *
     * @param user
     * @return
     */
    @Deprecated
    public User createUser(User user) {
        return super.save(user);
    }

    /**
     * Creates user
     *
     * @param userName
     * @param password
     *
     * @return {@link ru.db.entities.User}
     */
    public User createUser(String userName, String password) {
        User user = new User(userName, password);
        return super.save(user);
    }

    /**
     * Finds user by token
     *
     * @param token
     *
     * @return {@link ru.db.entities.User}
     */
    public User findUserByToken(String token) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from User u where u.token = :token");
        query.setParameter("token", token);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    /**
     * Finds user by id
     *
     * @param id
     *
     * @return {@link ru.db.entities.User}
     */
    public User findUserById(long id) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from User u where u.id = :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    /**
     * Finds user by name
     *
     * @param name
     *
     * @return {@link ru.db.entities.User}
     */
    public User findUserByName(String name) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from User u where u.name = :name");
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
}
