package dao.loan_request_DAO;

import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;

public interface LoanRequestDAO {
    public void submitRequest(LoanRequest loanRequest) throws DAOException;
}
