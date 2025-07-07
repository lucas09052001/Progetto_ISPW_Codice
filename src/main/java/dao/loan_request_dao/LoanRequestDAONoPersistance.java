package dao.loan_request_dao;

import dao.loan_post_dao.LoanPostDAO;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import repository.LoanRequestRepository;

import java.util.ArrayList;

public class LoanRequestDAONoPersistance implements LoanRequestDAO{

    String username;
    LoanRequestRepository repository = LoanRequestRepository.getInstance(); //Persistency layer
    ArrayList<LoanRequest> loanRequestList; //Buffered entities from persistency

    public LoanRequestDAONoPersistance(String username) {
        this.loanRequestList = repository.getLoanRequestList();
        this.username = username;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {
        //Adding new loanPost to volatile list (buffered entities)
        loanRequestList.add(loanRequest);
        repository.setLoanRequestList(loanRequestList);
    }

    @Override
    public ArrayList<LoanRequest> fetchAll() {
        ArrayList<LoanRequest> buffer;
        ArrayList<LoanRequest> returnee = new ArrayList<>();

        System.out.println("[LOAN-REQUEST-DAO] Buffering repository");
        buffer = repository.getLoanRequestList();
        System.out.println("[LOAN-REQUEST-DAO] Extracting data of interest");
        for(LoanRequest l : buffer){
            if(!l.getBorrowingUsername().equals(username)){
                returnee.add(l);
            }
        }
        return returnee;
    }

    @Override
    public void deleteAllRelative(LoanRequest loanRequest) throws DAOException {
        System.out.println("[LOAN-REQUEST-DAO] Starting deleteAllRelative");
        ArrayList<LoanRequest> buffer = repository.getLoanRequestList();

        //Array that hold the indexes to be removed
        ArrayList<Integer> indexesToBeRemoved = new ArrayList<>();
        Integer index = 0;

        //Find indexes to be removed
        for(LoanRequest l : buffer){

            if(l.getLoanPost().getLendingUsername().equals(loanRequest.getLoanPost().getLendingUsername()) && l.getLoanPost().getLoanObjectName().equals(loanRequest.getLoanPost().getLoanObjectName())){
                indexesToBeRemoved.add(index);
            }

            index++;
        }

        //Remove indexes
        for (Integer i : indexesToBeRemoved){
            int removee = i;
            buffer.remove(removee);
        }

        //Update repository
        repository.setLoanRequestList(buffer);
    }

}
