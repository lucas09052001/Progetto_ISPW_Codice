package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.util.ArrayList;

public interface LoanPostDAO {
    public void submit(LoanPost loanPost) throws DAOException, CriticalException;
    public ArrayList<LoanPost> fetchAllLoanPosts() throws DAOException, CriticalException;
    public LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException, CriticalException;
}
