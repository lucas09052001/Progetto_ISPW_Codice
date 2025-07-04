package controller.loanitem_controller;

import boundery.Boundaries;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAODB;
import dao.loan_post_dao.LoanPostDAOFile;
import dao.loan_post_dao.LoanPostDAONoPersistance;
import entity.SessionInfo;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;

import static entity.loan.LoanInterval.NULL;

public class LoanItemController {

    SessionInfo sessionInfo = SessionInfo.getSessionInfo();
    String username = sessionInfo.getUsername();
    LoanPost loanPost;
    LoanPostDAO dao;

    public LoanItemController(){

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> dao = new LoanPostDAODB(username);
            case FILE -> dao = new LoanPostDAOFile(username);
            case NO_PERSISTANCE -> dao = new LoanPostDAONoPersistance(username);
        }

    }

    public void submit(String loanObjectName, String loanDescription, LoanInterval loanInterval, String pathToIcon){

        System.out.println("    [CONTROLLER] Starting submit operation");
        try {
            if(loanObjectName.isEmpty() || loanDescription.isEmpty() || (loanInterval == NULL)){
                System.out.println("    [CONTROLLER] An illegal argument was passed to controller");
                throw new IllegalArgumentException("One of your inputs was considered invalid. Please try again.");
            }

            loanPost = new LoanPost(sessionInfo.getUsername(), loanObjectName, loanDescription, loanInterval, pathToIcon);
            System.out.println("    [CONTROLLER] Asking DAO to save on persistency");
            dao.submit(loanPost);

//            IMPLEMENTA TASKCOMPLETED SOLO SE HAI TEMPO
//            System.out.println("    [CONTROLLER] Update SessionInfo with nextBoundery = TaskCompleted");
//            sessionInfo.setNextBoundery(BounderyEnum.TASK_COMPLETED);
//            AppController.taskCompleted();

            System.out.println("    [CONTROLLER] Update SessionInfo with nextBoundery");
            sessionInfo.setNextBoundery(Boundaries.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(Boundaries.ERROR);

            //AppController.errorEncounterd();

            sessionInfo.setNextBoundery(Boundaries.LOAN_ITEM);
        } finally {
            System.out.println("    [CONTROLLER] Completed");
            //AppController.useCaseCompletion();
        }


    }


}
