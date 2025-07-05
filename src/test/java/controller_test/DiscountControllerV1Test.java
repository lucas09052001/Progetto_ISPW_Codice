package controller_test;

import controller.discount_controller.DiscountControllerV1;
import dao.discount_dao.DiscountDAO;
import dao.discount_dao.DiscountDAOFile;
import dao.user_dao.UserDAO;
import dao.user_dao.UserDAOFile;
import entity.discount.DiscountDTO;
import main.AppController;
import main.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountControllerV1Test {
    private DiscountControllerV1 discountControllerV1;

    @BeforeEach
    void setUp(){
        Observer observer = new AppController();
        DiscountDAO discountDAO = new DiscountDAOFile();
        UserDAO userDAO = new UserDAOFile();
        String username = "alice";
        discountControllerV1 = new DiscountControllerV1(observer, discountDAO, userDAO, username);
    }

    @Test
    void returnedExcepectedDTO(){
        discountControllerV1.fetchAll();

        DiscountDTO result = discountControllerV1.handNext();

        assertEquals("SummerSale", result.getName());
        assertEquals("/images/summer.jpg", result.getPathToImage());
        assertEquals(20, result.getPercentage());
        assertEquals(100, result.getCost());
        assertEquals(null, result.getOwnerUsername());

    }
}
