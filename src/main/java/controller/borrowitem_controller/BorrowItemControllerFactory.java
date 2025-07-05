package controller.borrowitem_controller;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import dao.loan_request_dao.LoanRequestDAO;
import dao.loan_request_dao.LoanRequestDAOFactory;
import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class BorrowItemControllerFactory {

    SessionInfo sessionInfo;
    LoanPostDAOFactory loanPostDAOFactory;
    LoanRequestDAOFactory loanRequestDAOFactory;

    public BorrowItemControllerFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        loanPostDAOFactory = new LoanPostDAOFactory();
        loanRequestDAOFactory = new LoanRequestDAOFactory();
    }

    public BorrowItemController generate(Observer observer) throws FactoryException {

        BorrowItemController returnee;
        LoanPostDAO loanPostDAO = loanPostDAOFactory.generate();
        LoanRequestDAO loanRequestDAO = loanRequestDAOFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new BorrowItemControllerV1(observer, loanPostDAO, loanRequestDAO);
            case null, default -> throw new FactoryException("[BORROWITEM-CONTROLLER-FACTORY] Reached unreachable code");
        }

        return returnee;
    }
}
