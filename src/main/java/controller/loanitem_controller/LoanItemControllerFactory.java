package controller.loanitem_controller;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import exceptions.FactoryException;
import utilities.SessionInfo;
import main.Observer;

public class LoanItemControllerFactory {

    SessionInfo sessionInfo;
    LoanPostDAOFactory daoFactory;

    public LoanItemControllerFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        daoFactory = new LoanPostDAOFactory();
    }

    public LoanItemController generate(Observer observer) throws FactoryException {

        LoanItemController returnee;
        LoanPostDAO dao = daoFactory.generate();

        switch(sessionInfo.getVersion()){
            case V1 -> {
                returnee = new LoanItemControllerV1(observer, dao, sessionInfo.getUsername());
            }
            case null, default -> {
                throw new FactoryException("[LOANITEM-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
