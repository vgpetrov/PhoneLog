package ru.db.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.db.entities.CallHistory;
import ru.db.entities.User;

import java.util.List;

/**
 * Created by vitaly on 04/03/15.
 */
@Repository
public class CallHistoryDAO extends GenericDAO<CallHistory> {

    public CallHistory create(CallHistory call) {
        return super.save(call);
    }

    /**
     * Finds list by user
     *
     * @param user
     *
     * @return {@link List<CallHistory>}
     */
    public List<CallHistory> findByUser(User user) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CallHistory ch where ch.user=:user");
        query.setParameter("user", user);
        List<CallHistory> list = query.list();
        session.clear();
        session.close();
        return list;
    }

    /**
     * Finds by id
     *
     * @param id
     *
     * @return {@link ru.db.entities.CallHistory}
     */
    public CallHistory getById(Long id) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CallHistory ch where ch.id =:id");
        query.setParameter("id", id);
        CallHistory result = (CallHistory) query.uniqueResult();
        session.clear();
        session.close();
        return result;
    }

}
