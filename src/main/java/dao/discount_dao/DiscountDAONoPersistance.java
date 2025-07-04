package dao.discount_dao;

import entity.discount.Discount;
import exceptions.DAOException;
import repository.DiscountRepository;

import java.util.ArrayList;
import java.util.Objects;

public class DiscountDAONoPersistance implements DiscountDAO{

    String username;
    DiscountRepository repository;
    ArrayList<Discount> discounts;

    public DiscountDAONoPersistance(String username){
        this.username = username;
        repository = DiscountRepository.getInstance();
    }

    @Override
    public ArrayList<Discount> fetchAll() throws DAOException {
        System.out.println("        [DAO] Fetching all entities");

        System.out.println("        [DAO] Buffering repository");
        ArrayList<Discount> buffer = repository.getDiscountList();

        System.out.println("        [DAO] Extracting data of interest");
        ArrayList<Discount> returnee = new ArrayList<>();
        for(Discount d : buffer){
            if(d.getOwnerUsername() == null){
                returnee.add(d);
            }
        }

        System.out.println("        [DAO] Returning");
        return returnee;
    }

    @Override
    public void redeem(Discount redeemee) throws DAOException {
        System.out.println("        [DAO] Redeeming passed discount");

        System.out.println("        [DAO] Buffering repository");
        ArrayList<Discount> buffer = repository.getDiscountList();

        System.out.println("        [DAO] Updating data of interest");
        for(Discount d : buffer){
            if(Objects.equals(d.getName(), redeemee.getName())){
                d.setOwnerUsername(redeemee.getOwnerUsername());
                break;
            }
        }

        System.out.println("        [DAO] Updating repository");
        repository.setDiscountList(buffer);

        System.out.println("        [DAO] Returning");
    }
}
