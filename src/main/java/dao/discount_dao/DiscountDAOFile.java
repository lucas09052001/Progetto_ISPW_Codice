package dao.discount_dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.discount.Discount;
import exceptions.DAOException;
import repository.PathRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DiscountDAOFile implements DiscountDAO{

    String username;
    File file;
    ObjectMapper mapper;

    public DiscountDAOFile(String username) {
        this.username = username;
        file = new File(PathRepository.getPathToDiscountJson());
        mapper = new ObjectMapper();
    }

    @Override
    public ArrayList<Discount> fetchAll() throws DAOException {
        System.out.println("        [DAO] Fetching all entities");
        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<Discount> buffer = mapper.readValue(file, new TypeReference<>() {});
            ArrayList<Discount> returnee = new ArrayList<>();

            System.out.println("        [DAO] Extracting data of interest");
            for(Discount d : buffer){
                //Put in returnee only available to redeem discounts
                if(d.getOwnerUsername() == null){
                    returnee.add(d);
                }
            }

            System.out.println("        [DAO] Returning");
            return returnee;

        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void redeem(Discount redeemee) throws DAOException {
        System.out.println("        [DAO] Redeeming passed discount");
        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<Discount> buffer = mapper.readValue(file, new TypeReference<>() {});

            System.out.println("        [DAO] Updating entity");
            for(Discount d : buffer){
                if(Objects.equals(d.getName(), redeemee.getName())){
                    d.setOwnerUsername(redeemee.getOwnerUsername());
                    break;
                }
            }

            System.out.println("        [DAO] Updating Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, buffer);
            System.out.println("        [DAO] Returning");

        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
