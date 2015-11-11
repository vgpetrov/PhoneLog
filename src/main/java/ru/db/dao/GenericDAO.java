package ru.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.db.hibernate.HibernateSessionFactory;

import java.util.List;

/**
 * Created by vitaly on 25/02/15.
 */
public abstract class GenericDAO<T> {

//    @Autowired
//    protected HibernateSessionFactory sessionFactory;

    @Autowired
    protected SessionFactory sessionFactory;

    public T save(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();

        return t;
    }

    public T update(T t) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
        session.close();

        return t;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
