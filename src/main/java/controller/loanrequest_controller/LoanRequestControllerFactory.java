package controller.loanrequest_controller;

import dao.loan_effective_dao.LoanEffectiveDAO;
import dao.loan_effective_dao.LoanEffectiveDAOFactory;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import dao.loan_request_dao.LoanRequestDAO;
import dao.loan_request_dao.LoanRequestDAOFactory;
import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class LoanRequestControllerFactory {
    SessionInfo sessionInfo;
    LoanPostDAOFactory loanPostDAOFactory;
    LoanRequestDAOFactory loanRequestDAOFactory;
    LoanEffectiveDAOFactory loanEffectiveDAOFactory;

    public LoanRequestControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
        loanPostDAOFactory = new LoanPostDAOFactory();
        loanRequestDAOFactory = new LoanRequestDAOFactory();
        loanEffectiveDAOFactory = new LoanEffectiveDAOFactory();
    }

    public LoanRequestController generate(Observer observer) throws FactoryException {

        LoanRequestController returnee;
        LoanPostDAO loanPostDAO = loanPostDAOFactory.generate();
        LoanRequestDAO loanRequestDAO = loanRequestDAOFactory.generate();
        LoanEffectiveDAO loanEffectiveDAO = loanEffectiveDAOFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new LoanRequestControllerV1(observer, loanPostDAO, loanRequestDAO, loanEffectiveDAO);
            }
            case null, default -> {
                throw new FactoryException("[LOANREQUEST-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;

    }
}
