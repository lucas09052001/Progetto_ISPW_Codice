package controller;

import boundery.BounderyEnum;
import dao.loan_effective_dao.LoanEffectiveDAO;
import dao.loan_effective_dao.LoanEffectiveDAODB;
import dao.loan_effective_dao.LoanEffectiveDAOFile;
import dao.loan_effective_dao.LoanEffectiveDAONoPersistance;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAODB;
import dao.loan_post_dao.LoanPostDAOFile;
import dao.loan_post_dao.LoanPostDAONoPersistance;
import dao.loan_request_dao.LoanRequestDAO;
import dao.loan_request_dao.LoanRequestDAODB;
import dao.loan_request_dao.LoanRequestDAOFile;
import dao.loan_request_dao.LoanRequestDAONoPersistance;
import entity.SessionInfo;
import entity.loan.loan_effective.LoanEffective;
import entity.loan.loan_request.LoanRequest;
import entity.loan.loan_request.LoanRequestDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import main.AppController;

import java.util.ArrayList;

public class LoanRequestController {

    ArrayList<LoanRequest> loanRequests;
    LoanRequestDAO loanRequestDAO;
    LoanPostDAO loanPostDAO;
    LoanEffectiveDAO loanEffectiveDAO;
    SessionInfo sessionInfo = SessionInfo.getSessionInfo();
    int lastHandedLoanRequest;

    public LoanRequestController(){
        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                loanRequestDAO = new LoanRequestDAODB(sessionInfo.getUsername());
                loanPostDAO = new LoanPostDAODB(sessionInfo.getUsername());
                loanEffectiveDAO = new LoanEffectiveDAODB(sessionInfo.getUsername());
            }
            case FILE -> {
                loanRequestDAO = new LoanRequestDAOFile(sessionInfo.getUsername());
                loanPostDAO = new LoanPostDAOFile(sessionInfo.getUsername());
                loanEffectiveDAO = new LoanEffectiveDAOFile(sessionInfo.getUsername());
            }
            case NO_PERSISTANCE -> {
                loanRequestDAO = new LoanRequestDAONoPersistance(sessionInfo.getUsername());
                loanPostDAO = new LoanPostDAONoPersistance(sessionInfo.getUsername());
                loanEffectiveDAO = new LoanEffectiveDAONoPersistance(sessionInfo.getUsername());
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


            System.out.println("    [CONTROLLER] Update SessionInfo with nextBoundery");
            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
            /*
            METTI TASK COMPLETED SE HAI TEMPO
             */

        } catch (DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
        } finally {
            System.out.println("    [CONTROLLER] Completed");
            AppController.useCaseCompletion();
        }


    }

}
