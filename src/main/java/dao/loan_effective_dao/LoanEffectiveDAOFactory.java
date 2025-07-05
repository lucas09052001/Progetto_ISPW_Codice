package dao.loan_effective_dao;

import exceptions.FactoryException;
import utilities.SessionInfo;

public class LoanEffectiveDAOFactory {
    SessionInfo sessionInfo;

    public LoanEffectiveDAOFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public LoanEffectiveDAO generate() throws FactoryException {

        LoanEffectiveDAO returnee;
        String username = sessionInfo.getUsername();

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                returnee = new LoanEffectiveDAODB(username);
            }
            case FILE -> {
                returnee = new LoanEffectiveDAOFile(username);
            }
            case NO_PERSISTANCE -> {
                returnee = new LoanEffectiveDAONoPersistance(username);
            }
            case null, default -> {
                throw new FactoryException("[LOANEFFECTIVE-DAO-FACTORY] Reached unreachable code");
            }

        }

        return returnee;
    }

}
