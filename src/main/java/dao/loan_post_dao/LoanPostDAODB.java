package dao.loan_post_dao;

import dao.ConnectionFactory;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanPostDAODB implements LoanPostDAO{
    String username;

    final String submitQuery = "INSERT INTO LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval) VALUES (?,?,?,?)";
    final String fetchAllQuery = "SELECT lendingUsername, loanObjectName, loanDescription, loanInterval, pathToImage FROM LoanPost";
    final String fetchByIdQuery = "SELECT * FROM LoanPost WHERE lendingUsername = ? AND loanObjectName = ?";
    final String deleteByIdQuery = "DELETE FROM LoanPost WHERE lendingUsername = ? AND loanObjectName = ?";

    public LoanPostDAODB(String username){
        this.username = username;
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException, CriticalException {

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(submitQuery)) {

            System.out.println("        [DAO] Connection to DB established");

            stmt.setString(1, loanPost.getLendingUsername());
            stmt.setString(2, loanPost.getLoanObjectName());
            stmt.setString(3, loanPost.getLoanDescription());
            stmt.setString(4, loanPost.getLoanInterval().name());

            stmt.executeUpdate();
            System.out.println("        [DAO] Query executed");

        } catch (NullPointerException | SQLException e) {
            System.out.println("        [DAO][EE] SQL error " + e.getMessage());
            throw new CriticalException();
        }

    }

    @Override
    public LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException, CriticalException {

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(fetchByIdQuery)) {

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
            throw new CriticalException();
        }
    }

    @Override
    public ArrayList<LoanPost> fetchAllLoanPosts() throws DAOException, CriticalException {

        ArrayList<LoanPost> returnee = new ArrayList<>();

        try(Connection connection = ConnectionFactory.upgrade();
            PreparedStatement stmt = connection.prepareStatement(fetchAllQuery)) {

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
            PreparedStatement stmt = connection.prepareStatement(deleteByIdQuery)) {

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
