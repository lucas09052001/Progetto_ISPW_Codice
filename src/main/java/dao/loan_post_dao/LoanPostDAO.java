package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;

import java.util.ArrayList;

public interface LoanPostDAO {
    void submit(LoanPost loanPost) throws DAOException;
    ArrayList<LoanPost> fetchAll() throws DAOException;
    LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException;
    void deleteByID(LoanPost loanPost) throws DAOException;
}
