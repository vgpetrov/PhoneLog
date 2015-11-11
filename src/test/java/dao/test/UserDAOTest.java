package dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.db.dao.UserDAO;
import ru.db.entities.User;
import ru.spring.Application;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by vitaly on 23/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class UserDAOTest {

    @Autowired
    UserDAO dao;

    @Test
    public void createUserTest() {
        String uuid = UUID.randomUUID().toString();
        User user1 = dao.createUser(uuid, "1qa@WS3ed");

        User userByName = dao.findUserById(user1.getId());
        assertEquals(uuid, userByName.getUsername());
    }

}
