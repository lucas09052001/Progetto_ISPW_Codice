package dao.discount_dao;

import dao.ConnectionFactory;
import entity.discount.Discount;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscountDAODB implements DiscountDAO{


    static final String FETCH_ALL_QUERY = "SELECT * FROM Discount WHERE ownerUsername IS NULL";   //Fetching only available to redeem discounts
    static final String REDEEM_QUERY = "UPDATE Discount SET ownerUsername = ? WHERE name = ?";

    public DiscountDAODB(){
        //No set up needed
    }

    @Override
    public ArrayList<Discount> fetchAll() throws DAOException{

        ArrayList<Discount> returnee = new ArrayList<>();

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(FETCH_ALL_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            ResultSet rs = stmt.executeQuery();
            System.out.println("        [DAO] Query executed");

            System.out.println("        [DAO] Handling query output");
            while(rs.next()){
                String name = rs.getString("name");
                String pathToImage = rs.getString("pathToImage");
                int percentage = rs.getInt("percentage");
                int cost = rs.getInt("cost");
                String ownerUsername = rs.getString("ownerUsername");
                returnee.add(new Discount(name, pathToImage, percentage, cost, ownerUsername));
            }

            return returnee;

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] Error during DAO procedure " + e.getMessage());
            throw new DAOException(e.getMessage());
        }

    }

    @Override
    public void redeem(Discount discount) throws DAOException {

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(REDEEM_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            stmt.setString(1, discount.getOwnerUsername());
            stmt.setString(2, discount.getName());

            if(stmt.executeUpdate() == 0){
                throw new DAOException("DB didn't update");
            }
            System.out.println("        [DAO] Query executed");

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] Error during DAO procedure " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
}
