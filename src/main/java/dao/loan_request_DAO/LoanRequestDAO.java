package dao.loan_request_DAO;

import dao.loan_post_dao.LoanPostDAODB;
import entity.loan.loan_request.LoanRequest;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.util.ArrayList;

public interface LoanRequestDAO {
    ArrayList<LoanRequest> fetchAll();
    void deleteAllRelative(LoanRequest loanRequest) throws  DAOException;
    void submitRequest(LoanRequest loanRequest) throws DAOException;
}
