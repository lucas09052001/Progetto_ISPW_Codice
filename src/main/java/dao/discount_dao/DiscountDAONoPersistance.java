package dao.discount_dao;

import entity.discount.Discount;
import entity.discount.DiscountDTO;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;
import repository.DiscountRepository;
import repository.LoanPostRepository;

import java.util.ArrayList;
import java.util.Objects;

public class DiscountNoPersistance implements DiscountDAO{

    String username;
    DiscountRepository repository;
    ArrayList<Discount> discounts;

    public DiscountNoPersistance(String username){
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
