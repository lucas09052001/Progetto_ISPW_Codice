package dao.discount_dao;

import entity.SessionInfo;

public class DiscountDAOFactory {

    SessionInfo sessionInfo;

    public DiscountDAOFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public DiscountDAO generate(){

        DiscountDAO returnee;

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                returnee = new DiscountDAODB(sessionInfo.getUsername());
            }
            case FILE -> {
                returnee = new DiscountDAOFile(sessionInfo.getUsername());
            }
            case NO_PERSISTANCE -> {
                returnee = new DiscountDAONoPersistance(sessionInfo.getUsername());
            }
            case null, default -> {
                throw new RuntimeException("[DISCOUNT-DAO-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
