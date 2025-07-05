package dao.loan_post_dao;

import exceptions.FactoryException;
import utilities.SessionInfo;

public class LoanPostDAOFactory {
    SessionInfo sessionInfo;

    public LoanPostDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public LoanPostDAO generate() throws FactoryException {

        LoanPostDAO returnee;

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                returnee = new LoanPostDAODB();
            }
            case FILE -> {
                returnee = new LoanPostDAOFile();
            }
            case NO_PERSISTANCE -> {
                returnee = new LoanPostDAONoPersistance();
            }
            case null, default -> {
                throw new FactoryException("[LOANPOST-DAO-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
