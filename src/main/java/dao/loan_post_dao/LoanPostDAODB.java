package dao.loan_post_dao;

import dao.ConnectionFactory;
import entity.loan.loan_post.LoanPost;
import entity.notification.CustomNotification;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanPostDAODB implements LoanPostDAO{

    final String submitQuery = "INSERT INTO LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval)" +
            "VALUES (?,?,?,?)";

    public LoanPostDAODB(){
        //No set up required
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
}
