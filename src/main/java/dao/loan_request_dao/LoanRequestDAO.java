package dao.loan_request_dao;

import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;

import java.util.ArrayList;

public interface LoanRequestDAO {
    ArrayList<LoanRequest> fetchAll() throws  DAOException;
    void deleteAllRelative(LoanRequest loanRequest) throws  DAOException;
    void submitRequest(LoanRequest loanRequest) throws DAOException;
}
