package dao.loan_request_DAO;

import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import repository.LoanPostRepository;
import repository.LoanRequestRepository;

import java.util.ArrayList;

public class LoanRequestDAONoPersistance implements LoanRequestDAO{

    String username;
    LoanRequestRepository repository = LoanRequestRepository.getInstance();
    ArrayList<LoanRequest> loanRequestList;

    public LoanRequestDAONoPersistance(String username) {
        this.loanRequestList = repository.getLoanRequestList();
        this.username = username;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {
        System.out.println("        [DAO] Adding new loanPost to volatile list");
        loanRequestList.add(loanRequest);
        repository.setLoanRequestList(loanRequestList);
    }
}
