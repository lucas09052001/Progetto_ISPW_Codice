package dao.loan_post_dao;

import entity.SessionInfo;

public class LoanPostDAOFactory {
    SessionInfo sessionInfo;

    public LoanPostDAOFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public LoanPostDAO generate(){

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
                System.out.println("[LOANPOST-DAO-FACTORY] Reached unreachable code");
                throw new RuntimeException("[LOANPOST-DAO-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
