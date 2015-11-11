package dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.db.dao.CallHistoryDAO;
import ru.db.dao.UserDAO;
import ru.db.entities.CallHistory;
import ru.db.entities.User;
import ru.spring.Application;

import java.util.UUID;

/**
 * Created by vitaly on 04/03/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class CallHistoryDAOTest {

    @Autowired
    CallHistoryDAO callHistoryDAO;

    @Autowired
    UserDAO userDAO;

    @Test
    public void createTest() {
        String uuid = UUID.randomUUID().toString();
        User user = userDAO.createUser(uuid, "1qa@WS3ed");

        CallHistory call = new CallHistory("89019011212", user);
        callHistoryDAO.create(call);

    }

}
