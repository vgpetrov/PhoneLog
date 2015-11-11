package ru.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.db.entities.CallHistory;
import ru.db.entities.User;

/**
 * Created by vitaly on 03/02/15.
 */
@Deprecated
public class HibernateSessionFactory {

    private static volatile HibernateSessionFactory util = null;
    private static volatile SessionFactory sessionFactory = null;

    private HibernateSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(CallHistory.class)
                .buildSessionFactory(registryBuilder.build());
    }

    public static HibernateSessionFactory init() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            util = new HibernateSessionFactory();
            return util;
        }
        return util;
    }

//    public static Session getSession() {
//        return sessionFactory.openSession();
//    }
//
//    public static void closeSession(Session session) {
//        session.close();
//    }
//
//    public static void destroy() {
//        sessionFactory.close();
//    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
