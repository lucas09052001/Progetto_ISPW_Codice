package dao.loan_request_dao;

import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAOFactory;
import exceptions.FactoryException;
import utilities.SessionInfo;

public class LoanRequestDAOFactory {

    SessionInfo sessionInfo;
    LoanPostDAOFactory loanPostDAOFactory;

    public LoanRequestDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
        loanPostDAOFactory = new LoanPostDAOFactory();
    }

    public LoanRequestDAO generate() throws FactoryException {

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
                throw new FactoryException("[LOANREQUEST-DAO-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
