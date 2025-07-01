package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.util.ArrayList;

public interface LoanPostDAO {
    void submit(LoanPost loanPost) throws DAOException, CriticalException;
    ArrayList<LoanPost> fetchAllLoanPosts() throws DAOException, CriticalException;
    LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException, CriticalException;
    void deleteByID(LoanPost loanPost) throws DAOException;
}
