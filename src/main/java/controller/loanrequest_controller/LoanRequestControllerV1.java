package controller.loanrequest_controller;

import boundery.Boundaries;
import dao.loan_effective_dao.LoanEffectiveDAO;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_request_dao.LoanRequestDAO;
import entity.loan.loan_effective.LoanEffective;
import entity.loan.loan_request.LoanRequest;
import entity.loan.loan_request.LoanRequestDTO;
import exceptions.DAOException;
import main.Observer;

import java.util.ArrayList;

public class LoanRequestControllerV1 implements LoanRequestController{

    ArrayList<LoanRequest> loanRequests;    //Buffered entities
    Observer observer;  //Reference to app controller via inteface
    LoanRequestDAO loanRequestDAO;
    LoanPostDAO loanPostDAO;
    LoanEffectiveDAO loanEffectiveDAO;
    int lastHandedLoanRequest;  //Information about the last item on list that was handed to boundary

    public LoanRequestControllerV1(Observer observer, LoanPostDAO loanPostDAO, LoanRequestDAO loanRequestDAO, LoanEffectiveDAO loanEffectiveDAO){

        //Attributes
        this.observer = observer;
        this.loanPostDAO = loanPostDAO;
        this.loanRequestDAO = loanRequestDAO;
        this.loanEffectiveDAO = loanEffectiveDAO;

        lastHandedLoanRequest = 0;

        fetchAll();
    }

    @Override
    public void fetchAll(){ //Buffer all entities from persistency
        try {
            loanRequests = loanRequestDAO.fetchAll();
        } catch (DAOException e) {
            observer.errorOccurred(e.getMessage());
        }
    }

    public LoanRequestDTO handNext(){   //Hand next loan request to boundary
        LoanRequestDTO loanRequestDTO;

        if(lastHandedLoanRequest != loanRequests.size()){
            loanRequestDTO = new LoanRequestDTO(loanRequests.get(lastHandedLoanRequest));
            lastHandedLoanRequest++;
        }else{
            loanRequestDTO = null;
        }

        return loanRequestDTO;
    }

    @Override
    public void acceptRequest(LoanRequestDTO dto){  //Accept passed request

        System.out.println("[LOAN-REQUEST-CONTROLLER] Starting acceptRequest");

        //Get entity from DTO
        LoanRequest loanRequest = new LoanRequest(dto.getBorrowingUsername(), dto.getLoanPost());

        try {

            //Deleteing relative loanPost (as a request has been accepted)
            loanPostDAO.deleteByID(loanRequest.getLoanPost());

            //Deleting all others requests relative to passed loan post(only one can be accepted)
            loanRequestDAO.deleteAllRelative(loanRequest);

            //Creating a loan effective entity (an on going loan)
            LoanEffective loanEffective = new LoanEffective(loanRequest.getBorrowingUsername(), loanRequest.getLoanPost().getLendingUsername(), loanRequest.getLoanPost().getLoanObjectName());
            loanEffectiveDAO.save(loanEffective);

            //Updating Observer
            observer.updateNewBoundary(Boundaries.HOMEPAGE);

        } catch (DAOException e) {
            System.out.println("[LOAN-REQUEST-CONTROLLER][EE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());

        }
    }

}
