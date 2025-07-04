package controller;

import boundery.Boundaries;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAODB;
import dao.loan_post_dao.LoanPostDAOFile;
import dao.loan_post_dao.LoanPostDAONoPersistance;
import dao.loan_request_dao.LoanRequestDAO;
import dao.loan_request_dao.LoanRequestDAODB;
import dao.loan_request_dao.LoanRequestDAOFile;
import dao.loan_request_dao.LoanRequestDAONoPersistance;
import entity.PersistencyPolicy;
import entity.SessionInfo;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_post.LoanPostDTO;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import main.AppController;

import java.util.ArrayList;

public class BorrowItemController {

    ArrayList<LoanPost> loanPostList;
    SessionInfo sessionInfo;
    LoanPostDAO loanPostDAO;
    LoanRequestDAO loanRequestDAO;
    int lastHandedLoanPost;

    public BorrowItemController(){
        loanPostList = new ArrayList<>();
        sessionInfo = SessionInfo.getSessionInfo();

        PersistencyPolicy persistencyPolicy = sessionInfo.getPersistencyPolicy();
        switch (persistencyPolicy){
            case DB -> {
                //loanPostDAO = new LoanPostDAODB(sessionInfo.getUsername());
                loanRequestDAO = new LoanRequestDAODB(sessionInfo.getUsername());
            }
            case FILE -> {
                //loanPostDAO = new LoanPostDAOFile(sessionInfo.getUsername());
                loanRequestDAO = new LoanRequestDAOFile(sessionInfo.getUsername());
            }
            case NO_PERSISTANCE -> {
                //loanPostDAO = new LoanPostDAONoPersistance(sessionInfo.getUsername());
                loanRequestDAO = new LoanRequestDAONoPersistance(sessionInfo.getUsername());
            }
        }

        lastHandedLoanPost = 0;

        fetchAllLoanPosts();
    }

    public void fetchAllLoanPosts(){

        try {
            System.out.println("[CONTROLLER] BorrowItemController asking dao layer to fetch loan posts... ");
            loanPostList = loanPostDAO.fetchAllLoanPosts();
        } catch (DAOException e) {
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(Boundaries.ERROR);

            //AppController.errorEncounterd();

            sessionInfo.setNextBoundery(Boundaries.AUTHENTICATE);
        }

    }
    
    public LoanPostDTO handNextLoanPost(){
        LoanPostDTO loanPostDTO;
        
        if(!(lastHandedLoanPost == loanPostList.size())){
            loanPostDTO = new LoanPostDTO(loanPostList.get(lastHandedLoanPost));
            lastHandedLoanPost++;
        }else{
            loanPostDTO = null;
        }

        return loanPostDTO;
    }

    public void submitRequest(LoanPostDTO loanPostDTO){

        System.out.println("    [CONTROLLER] Starting 'submitRequest'");

        try {

            if(loanPostDTO == null){
                throw new IllegalArgumentException("You're trying to submit a request for nothingness. You've got that already in your brain");
            }

            LoanPost requestedLoanPost = loanPostDTO.toEntity();
            LoanRequest loanRequest = new LoanRequest(sessionInfo.getUsername(), requestedLoanPost);

            loanRequestDAO.submitRequest(loanRequest);

            System.out.println("    [CONTROLLER] Update SessionInfo with nextBoundery");
            sessionInfo.setNextBoundery(Boundaries.HOMEPAGE);

            /*
            METTI TASK COMPLETED SE HAI TEMPO
             */

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(Boundaries.ERROR);

            //AppController.errorEncounterd();

            sessionInfo.setNextBoundery(Boundaries.BORROW_ITEM);
        } finally {
            System.out.println("    [CONTROLLER] Completed");
            //AppController.useCaseCompletion();
        }




    }

}
