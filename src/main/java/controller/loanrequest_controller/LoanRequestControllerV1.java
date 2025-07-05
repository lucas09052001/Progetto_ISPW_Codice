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

    ArrayList<LoanRequest> loanRequests;
    Observer observer;
    LoanRequestDAO loanRequestDAO;
    LoanPostDAO loanPostDAO;
    LoanEffectiveDAO loanEffectiveDAO;
    int lastHandedLoanRequest;

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
    public void fetchAll(){
        try {
            loanRequests = loanRequestDAO.fetchAll();
        } catch (DAOException e) {
            observer.errorOccurred(e.getMessage());
        }
    }

    public LoanRequestDTO handNext(){
        LoanRequestDTO loanRequestDTO;

        if(!(lastHandedLoanRequest == loanRequests.size())){
            loanRequestDTO = new LoanRequestDTO(loanRequests.get(lastHandedLoanRequest));
            lastHandedLoanRequest++;
        }else{
            loanRequestDTO = null;
        }

        return loanRequestDTO;
    }

    @Override
    public void acceptRequest(LoanRequestDTO dto){

        System.out.println("    [CONTROLLER] Starting acceptRequest");
        LoanRequest loanRequest = new LoanRequest(dto.getBorrowingUsername(), dto.getLoanPost());

        try {

            System.out.println("    [CONTROLLER] Asking loanPostDAO to delete relative LoanPost");
            loanPostDAO.deleteByID(loanRequest.getLoanPost());

            System.out.println("    [CONTROLLER] Asking loanRequestDAO to delete all relative LoanRequests");
            loanRequestDAO.deleteAllRelative(loanRequest);

            System.out.println("    [CONTROLLER] Instantiating new LoanEffective");
            LoanEffective loanEffective = new LoanEffective(loanRequest.getBorrowingUsername(), loanRequest.getLoanPost().getLendingUsername(), loanRequest.getLoanPost().getLoanObjectName());

            System.out.println("    [CONTROLLER] Asking loanEffectiveDAO to save on persistency");
            loanEffectiveDAO.save(loanEffective);


            System.out.println("    [CONTROLLER] Calling observer");
            observer.updateNewBoundery(Boundaries.HOMEPAGE);

        } catch (DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());

        }
    }

}
