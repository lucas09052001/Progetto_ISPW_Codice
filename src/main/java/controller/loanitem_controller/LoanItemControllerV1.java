package controller.loanitem_controller;

import boundery.Boundaries;
import dao.loan_post_dao.LoanPostDAO;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;
import main.Observer;

import static entity.loan.LoanInterval.NULL;

public class LoanItemControllerV1 implements LoanItemController{

    private LoanPostDAO dao;
    private Observer observer;
    private String username;

    public LoanItemControllerV1(Observer observer, LoanPostDAO dao, String username){
        this.observer = observer;
        this.dao = dao;
        this.username = username;
    }

    @Override
    public void submit(String loanObjectName, String loanDescription, LoanInterval loanInterval, String pathToIcon){
        //Submit loan post
        LoanPost loanPost;

        System.out.println("[LOAN-ITEM-CONTROLLER] Starting submit operation");
        try {
            if(loanObjectName.isEmpty() || loanDescription.isEmpty() || (loanInterval == NULL)){
                System.out.println("[LOAN-ITEM-CONTROLLER] An illegal argument was passed to controller");
                throw new IllegalArgumentException("One of your inputs was considered invalid. Please try again.");
            }

            loanPost = new LoanPost(username, loanObjectName, loanDescription, loanInterval, pathToIcon);
            dao.submit(loanPost);

            observer.updateNewBoundary(Boundaries.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("[LOAN-ITEM-CONTROLLER][EE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());

        }


    }


}
