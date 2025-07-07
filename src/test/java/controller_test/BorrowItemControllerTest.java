package controller_test;

import controller.borrowitem_controller.BorrowItemControllerV1;
import dao.loan_post_dao.LoanPostDAOFile;
import dao.loan_request_dao.LoanRequestDAO;
import dao.loan_request_dao.LoanRequestDAOFile;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_post.LoanPostDTO;
import exceptions.DAOException;
import main.AppController;
import main.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.PathUtility;
import utilities.SessionInfo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowItemControllerTest {
    private BorrowItemControllerV1 controller;
    private Observer observer;

    @BeforeEach
    void setUp(){
        SessionInfo.getSessionInfo().setUsername("alice");
        observer = new AppController();
        controller = new BorrowItemControllerV1(observer, new LoanPostDAOFile(), new LoanRequestDAOFile("alice"), "alice");
    }

    @Test
    void getLoanPostsTest(){
        int expected = 5;   //Number of current loanPosts in json
        controller.getAllLoanPosts();
        ArrayList<LoanPost> actualList = controller.getLoanPostList();
        int actual = actualList.size();

        assertEquals(expected, actual);
    }

    @Test
    void correctUpdateTest(){
        LoanRequestDAO dao = new LoanRequestDAOFile("bob");
        try {
            int beforeCount = dao.fetchAll().size();

            LoanPost submitee = new LoanPost("bob", "Shoes", "They're smelly", LoanInterval.DAY, PathUtility.getPathToEmptyImage());
            LoanPostDTO dto = new LoanPostDTO(submitee);
            controller.submitRequest(dto);

            int afterCount = dao.fetchAll().size();

            assertEquals(beforeCount+1, afterCount);

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }
}
