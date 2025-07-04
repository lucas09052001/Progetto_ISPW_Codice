package controller.loanitem_controller;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import entity.SessionInfo;
import main.Observer;

public class LoanItemControllerFactory {

    SessionInfo sessionInfo;
    LoanPostDAOFactory daoFactory;

    public LoanItemControllerFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        daoFactory = new LoanPostDAOFactory();
    }

    public LoanItemController generate(Observer observer){

        LoanItemController returnee;
        LoanPostDAO dao = daoFactory.generate();

        switch(sessionInfo.getVersion()){
            case V1 -> {
                returnee = new LoanItemControllerV1(observer, dao, sessionInfo.getUsername());
            }
            case null, default -> {
                System.out.println("[LOANITEM-CONTROLLER-FACTORY] Reached unreachable code");
                throw new RuntimeException("[LOANITEM-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
