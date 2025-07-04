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
import main.Observer;

import static entity.loan.LoanInterval.NULL;

public class LoanItemControllerV1 implements LoanItemController{

    SessionInfo sessionInfo = SessionInfo.getSessionInfo();
    String username;
    LoanPost loanPost;
    LoanPostDAO dao;
    Observer observer;

    public LoanItemControllerV1(Observer observer, LoanPostDAO dao, String username){
        this.observer = observer;
        this.dao = dao;
        this.username = username;
    }

    @Override
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

            System.out.println("    [CONTROLLER] Calling Observer");
            observer.updateNewBoundery(Boundaries.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());

        }


    }


}
