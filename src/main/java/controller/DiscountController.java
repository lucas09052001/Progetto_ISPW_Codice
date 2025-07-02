package controller;

import boundery.BounderyEnum;
import dao.discount_dao.DiscountDAO;
import dao.discount_dao.DiscountDAODB;
import dao.discount_dao.DiscountDAOFile;
import dao.discount_dao.DiscountNoPersistance;
import dao.user_dao.UserDAO;
import dao.user_dao.UserDAODB;
import dao.user_dao.UserDAOFile;
import dao.user_dao.UserDAONoPersistance;
import entity.SessionInfo;
import entity.discount.Discount;
import entity.discount.DiscountDTO;
import entity.user.User;
import exceptions.DAOException;
import main.AppController;

import java.util.ArrayList;

public class DiscountController {

    SessionInfo sessionInfo;
    DiscountDAO discountDAO;
    UserDAO userDAO;
    ArrayList<Discount> discounts;
    int lastHandedDTO;

    public DiscountController(){
        this.sessionInfo = SessionInfo.getSessionInfo();

        switch (sessionInfo.getPersistencyPolicy()){
            case DB -> {
                discountDAO = new DiscountDAODB(sessionInfo.getUsername());
                userDAO = new UserDAODB(sessionInfo.getUsername());
            }
            case FILE -> {
                discountDAO = new DiscountDAOFile(sessionInfo.getUsername());
                userDAO = new UserDAOFile(sessionInfo.getUsername());
            }
            case NO_PERSISTANCE -> {
                discountDAO = new DiscountNoPersistance(sessionInfo.getUsername());
                userDAO = new UserDAONoPersistance(sessionInfo.getUsername());
            }
            case NULL -> {  throw new RuntimeException();   //Unreachable code
            }
        }

        lastHandedDTO = 0;
        fetchAll();
    }

    public void fetchAll(){
        System.out.println("    [CONTROLLER] Asking DAO to fetchAll");
        try {
            discounts = discountDAO.fetchAll();
        } catch (DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
            AppController.useCaseCompletion();
        }
    }

    public void redeemDiscount(DiscountDTO dto){

        System.out.println("    [CONTROLLER] Starting 'redeemDiscount'");

        try {
            User user = userDAO.fetchUserInfo(sessionInfo.getUsername());

            System.out.println("    [CONTROLLER] Checking if user has enough points to redeem");
            if(user.getPoints() < dto.getCost()){
               throw new IllegalStateException("Not enough points to redeem !");
            }

            System.out.println("    [CONTROLLER] Scaling points");
            user.addPoints(dto.getCost() * -1);
            userDAO.updateUser(user);

            Discount discount = new Discount();
            discount.setName(dto.getName());
            discount.setOwnerUsername(sessionInfo.getUsername());

            System.out.println("    [CONTROLLER] Asking DAO to update redeem discount");
            discountDAO.redeem(discount);

            System.out.println("    [CONTROLLER] Update SessionInfo with nextBoundery");
            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);

            /*
            METTI TASK COMPLETED SE HAI TEMPO
             */

        } catch (IllegalStateException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
        } finally {
            System.out.println("    [CONTROLLER] Completed");
            AppController.useCaseCompletion();
        }

    }

    public DiscountDTO handNextDiscount(){
        DiscountDTO discountDTO;

        if(!(lastHandedDTO == discounts.size())){
            discountDTO = new DiscountDTO(discounts.get(lastHandedDTO));
            lastHandedDTO++;
        }else{
            discountDTO = null;
        }

        return discountDTO;
    }

}
