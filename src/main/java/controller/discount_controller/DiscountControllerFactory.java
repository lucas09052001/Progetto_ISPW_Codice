package controller.discount_controller;

import dao.discount_dao.DiscountDAO;
import dao.discount_dao.DiscountDAOFactory;
import dao.discount_dao.DiscountDAOFile;
import dao.user_dao.UserDAO;
import dao.user_dao.UserDAOFactory;
import entity.SessionInfo;
import main.Observer;

public class DiscountControllerFactory {
    SessionInfo sessionInfo;
    UserDAOFactory userDAOFactory;
    DiscountDAOFactory discountDAOFactory;

    public DiscountControllerFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
        this.userDAOFactory = new UserDAOFactory();
        this.discountDAOFactory = new DiscountDAOFactory();
    }

    public DiscountController generate(Observer observer){

        DiscountController returnee;
        UserDAO userDAO = userDAOFactory.generate();
        DiscountDAO discountDAO = discountDAOFactory.generate();

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new DiscountControllerV1(observer, discountDAO, userDAO, sessionInfo.getUsername());
            }
            case null, default -> {
                throw new RuntimeException("[DISCOUNT-CONTROLLER-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
