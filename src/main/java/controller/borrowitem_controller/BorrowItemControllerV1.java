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

    private ArrayList<LoanPost> loanPostList;   //Buffered list of entities
    private Observer observer;  //Reference to AppController via Interface
    private String username;    //Current user's username
    private LoanPostDAO loanPostDAO;
    private LoanRequestDAO loanRequestDAO;
    private int lastHandedLoanPost; //Holds the information about the last handed loanPost from list

    public BorrowItemControllerV1(Observer observer, LoanPostDAO loanPostDAO, LoanRequestDAO loanRequestDAO, String username){
        //Attributes
        this.observer = observer;
        this.loanPostDAO = loanPostDAO;
        this.loanRequestDAO = loanRequestDAO;
        this.username = username;

        loanPostList = new ArrayList<>();

        lastHandedLoanPost = 0;

        getAllLoanPosts();
    }

    public ArrayList<LoanPost> getLoanPostList() {
        return loanPostList;
    }

    @Override
    public void getAllLoanPosts(){ //Buffer all loanPosts from persistency to list
        try {
            System.out.println("[CONTROLLER] BorrowItemController asking dao layer to fetch loan posts... ");
            loanPostList = loanPostDAO.fetchAll();
        } catch (DAOException e) {
            observer.errorOccurred(e.getMessage());
        }

    }

    @Override
    public LoanPostDTO handNext(){  //Hand next loan post to boundary
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
    public void submitRequest(LoanPostDTO loanPostDTO){ //Submit a loan request relative to the passed loan post

        System.out.println("[LOANPOST-CONTROLLER] Starting 'submitRequest'");

        try {

            if(loanPostDTO == null){
                throw new IllegalArgumentException("You're trying to submit a request for nothingness. You've got that already in your brain");
            }

            LoanPost requestedLoanPost = loanPostDTO.toEntity();
            LoanRequest loanRequest = new LoanRequest(username, requestedLoanPost);

            loanRequestDAO.submitRequest(loanRequest);

            System.out.println("[LOANPOST-CONTROLLER] Calling observer");
            observer.updateNewBoundary(Boundaries.HOMEPAGE);

        } catch (IllegalArgumentException | DAOException e) {
            System.out.println("[LOANPOST-CONTROLLER][EE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());

        }
    }

}
