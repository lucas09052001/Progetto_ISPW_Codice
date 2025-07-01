package controller;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAODB;
import dao.loan_post_dao.LoanPostDAOFile;
import dao.loan_post_dao.LoanPostDAONoPersistance;
import dao.loan_request_DAO.LoanRequestDAO;
import dao.loan_request_DAO.LoanRequestDAODB;
import dao.loan_request_DAO.LoanRequestDAOFile;
import dao.loan_request_DAO.LoanRequestDAONoPersistance;
import entity.SessionInfo;
import entity.loan.loan_request.LoanRequest;
import entity.loan.loan_request.LoanRequestDTO;
import exceptions.CriticalException;

import java.util.ArrayList;

public class LoanRequestController {

    ArrayList<LoanRequest> loanRequests;
    LoanRequestDAO loanRequestDAO;
    SessionInfo sessionInfo = SessionInfo.getSessionInfo();
    int lastHandedLoanRequest;

    public LoanRequestController(){
        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                loanRequestDAO = new LoanRequestDAODB(sessionInfo.getUsername());
            }
            case FILE -> {
                loanRequestDAO = new LoanRequestDAOFile(sessionInfo.getUsername());
            }
            case NO_PERSISTANCE -> {
                loanRequestDAO = new LoanRequestDAONoPersistance(sessionInfo.getUsername());
            }
            case NULL -> {
                System.out.println("        [DAO][CE] Persistency non trovata");
                throw new CriticalException("Persistency non trovata");   //Unreachable code
            }
        }

        fetchAll();

        lastHandedLoanRequest = 0;
    }

    private void fetchAll(){
       loanRequests = loanRequestDAO.fetchAll();
    }

    public LoanRequestDTO handNextLoanRequest(){
        LoanRequestDTO loanRequestDTO;

        if(!(lastHandedLoanRequest == loanRequests.size())){
            loanRequestDTO = new LoanRequestDTO(loanRequests.get(lastHandedLoanRequest));
            lastHandedLoanRequest++;
        }else{
            loanRequestDTO = null;
        }

        return loanRequestDTO;
    }

    public void acceptRequest(LoanRequestDTO dto){

    }

}
