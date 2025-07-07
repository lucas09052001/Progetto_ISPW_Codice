package dao_test;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAONoPersistance;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.PathUtility;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LoanPostDAOTest {

    private LoanPostDAO loanPostDAO;

    @BeforeEach
    void setUp(){
        loanPostDAO = new LoanPostDAONoPersistance();
    }

    @Test
    void correctFetchByIDTest(){

        String lendingUsername = "alice";
        String loanObjectName = "Calcolatrice";

        try {
            LoanPost actual = loanPostDAO.fetchById(lendingUsername, loanObjectName);
            assertEquals("alice", actual.getLendingUsername());
            assertEquals("Calcolatrice", actual.getLoanObjectName());
            assertEquals("Prestito per compito in classe", actual.getLoanDescription());
            assertEquals(LoanInterval.DAY, actual.getLoanInterval());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void fetchListTest(){

        try{

            ArrayList<LoanPost> list = loanPostDAO.fetchAll();
            assertFalse(list.isEmpty());

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    void submitTest(){

        try {

            int countBefore = loanPostDAO.fetchAll().size();
            LoanPost loanPost = new LoanPost("alice", "Appunti ISPW", "Per prendere 30 e lode con bacio accademico", LoanInterval.WEEK, PathUtility.getPathToEmptyImage());
            loanPostDAO.submit(loanPost);
            int countAfter = loanPostDAO.fetchAll().size();

            assertEquals(countBefore+1, countAfter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
