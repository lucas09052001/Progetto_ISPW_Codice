package controller.discount_controller;

import boundery.Boundaries;
import dao.discount_dao.DiscountDAO;
import dao.user_dao.UserDAO;
import entity.SessionInfo;
import entity.discount.Discount;
import entity.discount.DiscountDTO;
import entity.user.User;
import exceptions.DAOException;
import main.Observer;

import java.util.ArrayList;

public class DiscountControllerV1 implements DiscountController{

    SessionInfo sessionInfo;
    DiscountDAO discountDAO;
    UserDAO userDAO;
    ArrayList<Discount> discounts;
    int lastHandedDTO;
    Observer observer;
    String username;

    public DiscountControllerV1(Observer observer, DiscountDAO discountDAO, UserDAO userDAO, String username){
        //Attributes
        this.username = username;
        this.observer = observer;
        this.discountDAO = discountDAO;
        this.userDAO = userDAO;

        lastHandedDTO = 0;
        fetchAll();
    }

    public void fetchAll(){
        System.out.println("    [CONTROLLER] Asking DAO to fetchAll");
        try {
            discounts = discountDAO.fetchAll();
        } catch (DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());
        }
    }

    public void redeemDiscount(DiscountDTO dto){

        System.out.println("    [CONTROLLER] Starting 'redeemDiscount'");

        try {
            User user = userDAO.fetchUserInfo(username);

            System.out.println("    [CONTROLLER] Checking if user has enough points to redeem");
            if(user.getPoints() < dto.getCost()){
               throw new IllegalStateException("Not enough points to redeem !");
            }

            System.out.println("    [CONTROLLER] Scaling points");
            user.addPoints(dto.getCost() * -1);
            userDAO.updateUser(user);

            Discount discount = new Discount();
            discount.setName(dto.getName());
            discount.setOwnerUsername(username);

            System.out.println("    [CONTROLLER] Asking DAO to update redeem discount");
            discountDAO.redeem(discount);

            System.out.println("    [CONTROLLER] Calling observer");
            observer.updateNewBoundery(Boundaries.HOMEPAGE);


        } catch (IllegalStateException | DAOException e) {
            System.out.println("    [CONTROLLER][NCE] Something went wrong during UC execution");
            observer.errorOccurred(e.getMessage());
        }

    }

    public DiscountDTO handNext(){
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
