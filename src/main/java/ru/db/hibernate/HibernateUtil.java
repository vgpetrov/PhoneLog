package ru.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by vitaly on 03/02/15.
 */
@Deprecated
public class HibernateUtil {

    private static volatile SessionFactory sessionFactory = null;

    private HibernateUtil() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration
//                .addAnnotatedClass(Host.class)
                .buildSessionFactory(ssrb.build());
    }

    public static void createSessionFactory() {
        if (sessionFactory == null) {
            HibernateUtil util = new HibernateUtil();
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSession(Session session) {
        session.close();
    }

    public static void destroy() {
        sessionFactory.close();
    }

}
