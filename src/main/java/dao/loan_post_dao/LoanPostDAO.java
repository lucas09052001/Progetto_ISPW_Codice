package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;

public interface LoanPostDAO {
    public void submit(LoanPost loanPost) throws DAOException, CriticalException;
}
