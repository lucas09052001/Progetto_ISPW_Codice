package dao.loan_request_DAO;

import dao.loan_post_dao.LoanPostDAO;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_post.LoanPostDTO;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import repository.LoanPostRepository;
import repository.LoanRequestRepository;

import java.util.ArrayList;

public class LoanRequestDAONoPersistance implements LoanRequestDAO{

    String username;
    LoanRequestRepository repository = LoanRequestRepository.getInstance();
    ArrayList<LoanRequest> loanRequestList;
    LoanPostDAO loanPostDAO;

    public LoanRequestDAONoPersistance(String username) {
        this.loanRequestList = repository.getLoanRequestList();
        this.username = username;
    }

    public LoanPostDAO getLoanPostDAO() {
        return loanPostDAO;
    }

    public void setLoanPostDAO(LoanPostDAO loanPostDAO) {
        this.loanPostDAO = loanPostDAO;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {
        System.out.println("        [DAO] Adding new loanPost to volatile list");
        loanRequestList.add(loanRequest);
        repository.setLoanRequestList(loanRequestList);
    }

    @Override
    public ArrayList<LoanRequest> fetchAll() {
        ArrayList<LoanRequest> buffer;
        ArrayList<LoanRequest> returnee = new ArrayList<>();

        System.out.println("        [DAO] Buffering repository");
        buffer = repository.getLoanRequestList();
        System.out.println("        [DAO] Extracting data of interest");
        for(LoanRequest l : buffer){
            if(!l.getBorrowingUsername().equals(username)){
                returnee.add(l);
            }
        }
        return returnee;
    }
}
