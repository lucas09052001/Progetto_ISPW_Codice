package dao_test;

import dao.user_dao.UserDAONoPersistance;
import entity.user.User;
import exceptions.DAOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoNoPersistanceTest {

    private UserDAONoPersistance userDaoNoPersistance;

    @BeforeEach
    void setUp(){
        userDaoNoPersistance = new UserDAONoPersistance();
    }

    @Test
    void returnExpectedUserInfo() {

        String username = "bob";

        try {
            User user = userDaoNoPersistance.fetchUserInfo(username);

            assertEquals(user.getUsername(), "bob");
            assertEquals(user.getPassword(), "bob");
            assertEquals(user.getPoints(), 1100);
            assertEquals(user.getRating(), 1.3);

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }


    }

}
