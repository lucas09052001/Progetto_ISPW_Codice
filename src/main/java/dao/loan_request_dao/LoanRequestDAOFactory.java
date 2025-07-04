package dao.loan_request_dao;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import entity.SessionInfo;

public class LoanRequestDAOFactory {

    SessionInfo sessionInfo;
    LoanPostDAOFactory loanPostDAOFactory;

    public LoanRequestDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        loanPostDAOFactory = new LoanPostDAOFactory();
    }

    public LoanRequestDAO generate(){

        LoanRequestDAO returnee;
        String username = sessionInfo.getUsername();
        LoanPostDAO loanPostDAO = loanPostDAOFactory.generate();

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                returnee = new LoanRequestDAODB(username, loanPostDAO);
            }
            case FILE -> {
                returnee = new LoanRequestDAOFile(username);
            }
            case NO_PERSISTANCE -> {
                returnee = new LoanRequestDAONoPersistance(username);
            }
            case null, default -> {
                System.out.println("[LOANREQUEST-DAO-FACTORY] Reached unreachable code");
                throw new RuntimeException("[LOANREQUEST-DAO-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
