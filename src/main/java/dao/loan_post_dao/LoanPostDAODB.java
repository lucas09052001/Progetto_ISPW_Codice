package dao.loan_post_dao;

import dao.ConnectionFactory;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanPostDAODB implements LoanPostDAO{

    static final String SUBMIT_QUERY = "INSERT INTO LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage) VALUES (?,?,?,?,?)";
    static final String FETCH_ALL_QUERY = "SELECT lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage FROM LoanPost";
    static final String FETCH_BY_ID_QUERY = "SELECT * FROM LoanPost WHERE lendingUsername = ? AND loanObjectName = ?";
    static final String DELETE_BY_ID_QUERY = "DELETE FROM LoanPost WHERE lendingUsername = ? AND loanObjectName = ?";

    public LoanPostDAODB(){
        //No set up needed
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException {

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(SUBMIT_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            stmt.setString(1, loanPost.getLendingUsername());
            stmt.setString(2, loanPost.getLoanObjectName());
            stmt.setString(3, loanPost.getLoanDescription());
            stmt.setString(4, loanPost.getLoanInterval().name());
            stmt.setString(5, loanPost.getPathToImage());

            stmt.executeUpdate();
            System.out.println("        [DAO] Query executed");

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] SQL error " + e.getMessage());
            throw new DAOException(e.getMessage());
        }

    }

    @Override
    public LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException {

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(FETCH_BY_ID_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            stmt.setString(1, lendingUsername);
            stmt.setString(2, loanObjectName);

            ResultSet rs = stmt.executeQuery();
            System.out.println("        [DAO] Query executed");

            rs.next();
            String loanDescription = rs.getString("loanDescription");
            String pathToImage = rs.getString("pathToImage");
            LoanInterval loanInterval = LoanInterval.valueOf(rs.getString("loanInterval"));

            return new LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage);

        } catch (SQLException e) {
            System.out.println("        [DAO][EE] SQL error " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public ArrayList<LoanPost> fetchAll() throws DAOException {

        ArrayList<LoanPost> returnee = new ArrayList<>();

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(FETCH_ALL_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            ResultSet rs = stmt.executeQuery();
            System.out.println("        [DAO] Query executed");

            System.out.println("        [DAO] Handling query output");
            while(rs.next()){
                String lendingUsername = rs.getString("lendingUsername");
                String loanObjectName = rs.getString("loanObjectName");
                String loanDescription = rs.getString("loanDescription");
                LoanInterval loanInterval = LoanInterval.valueOf(rs.getString("loanInterval"));
                String pathToImage = rs.getString("pathToImage");
                returnee.add(new LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage));
            }

            return returnee;

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] Error during DAO procedure " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void deleteByID(LoanPost loanPost) throws DAOException{

        /*
        THIS QUERY ALSO DELETES RELATES LOAN REQUESTS VIA DBMS MEANS
         */

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID_QUERY)) {

            System.out.println("        [DAO] Connection to DB established");

            stmt.setString(1, loanPost.getLendingUsername());
            stmt.setString(2, loanPost.getLoanObjectName());

            if(stmt.executeUpdate() == 0 ){
                throw new DAOException("Something went wrong during delete procedure");
            }
            System.out.println("        [DAO] Query executed");

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] Error during DAO procedure " + e.getMessage());
            throw new DAOException(e.getMessage());
        }

    }

}
