package controller.borrowitem_controller;

import boundery.Boundaries;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_request_dao.LoanRequestDAO;
import utilities.SessionInfo;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_post.LoanPostDTO;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import main.Observer;

import java.util.ArrayList;

public class BorrowItemControllerV1 implements BorrowItemController{

    ArrayList<LoanPost> loanPostList;
    Observer observer;
    SessionInfo sessionInfo;
    LoanPostDAO loanPostDAO;
    LoanRequestDAO loanRequestDAO;
    int lastHandedLoanPost;

    public BorrowItemControllerV1(Observer observer, LoanPostDAO loanPostDAO, LoanRequestDAO loanRequestDAO){
        //Attributes
        sessionInfo = SessionInfo.getSessionInfo();
        this.observer = observer;
        this.loanPostDAO = loanPostDAO;
        this.loanRequestDAO = loanRequestDAO;

        loanPostList = new ArrayList<>();

        lastHandedLoanPost = 0;

        getAllLoanPosts();
    }

    @Override
    public void getAllLoanPosts(){

        try {
            System.out.println("[CONTROLLER] BorrowItemController asking dao layer to fetch loan posts... ");
            loanPostList = loanPostDAO.fetchAll();
        } catch (DAOException e) {
            sessionInfo.setLastError(e.getMessage());
            observer.errorOccurred(e.getMessage());
        }

    }

    @Override
    public LoanPostDTO handNext(){
        LoanPostDTO loanPostDTO;
        
        if(lastHandedLoanPost != loanPostList.size()){
            loanPostDTO = new LoanPostDTO(loanPostList.get(lastHandedLoanPost));
            lastHandedLoanPost++;
        }else{
            loanPostDTO = null;
        }

        return loanPostDTO;
    }

    @Override
    public void submitRequest(LoanPostDTO loanPostDTO){

        System.out.println("    [CONTROLLER] Starting 'submitRequest'");

        try {

            if(loanPostDTO == null){
                throw new IllegalArgumentException("You're trying to submit a request for nothingness. You've got that already in your brain");
            }

            LoanPost requestedLoanPost = loanPostDTO.toEntity();
            LoanRequest loanRequest = new LoanRequest(sessionInfo.getUsername(), requestedLoanPost);

            loanRequestDAO.submitRequest(loanRequest);

            System.out.println("    [CONTROLLER] Calling observer");
            observer.updateNewBoundery(Boundaries.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            observer.errorOccurred(e.getMessage());

        }
    }

}
