package dao.loan_effective_dao;

import dao.loan_request_dao.LoanRequestDAO;
import entity.SessionInfo;

public class LoanEffectiveDAOFactory {
    SessionInfo sessionInfo;

    public LoanEffectiveDAOFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public LoanEffectiveDAO generate(){

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
                System.out.println("[LOANEFFECTIVE-DAO-FACTORY] Reached unreachable code");
                throw new RuntimeException("[LOANEFFECTIVE-DAO-FACTORY] Reached unreachable code");
            }

        }

        return returnee;
    }

}
