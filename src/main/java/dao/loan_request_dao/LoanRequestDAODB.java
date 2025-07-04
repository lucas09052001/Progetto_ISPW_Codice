package dao.loan_request_dao;

import dao.ConnectionFactory;
import dao.loan_post_dao.LoanPostDAO;
import dao.loan_post_dao.LoanPostDAODB;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class LoanRequestDAODB implements LoanRequestDAO{

    final String username;
    LoanPostDAO loanPostDAO;
    final String insertionQuery = "INSERT INTO LoanRequest (borrowingUsername, lendingUsername, loanObjectName) VALUES (?, ?, ?)";
    final String fetchAllQuery = "SELECT * FROM LoanRequest WHERE borrowingUsername <> ?";


    public LoanRequestDAODB(String username, LoanPostDAO loanPostDAO){
        this.username = username;
        this.loanPostDAO = loanPostDAO;
    }

    @Override
    public ArrayList<LoanRequest> fetchAll() {  //Except the requests made by the current user

        ArrayList<LoanRequest> returnee = new ArrayList<>();

        try (Connection conn = ConnectionFactory.upgrade();
             PreparedStatement stmt = conn.prepareStatement(fetchAllQuery)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            System.out.println("        [DAO] Query executed");

            while (rs.next()){

                LoanPost loanPost;
                String borrowingUsername = rs.getString("borrowingUsername");
                String lendingUsername = rs.getString("lendingUsername");
                String loanObjectName = rs.getString("loanObjectName");

                loanPost  = loanPostDAO.fetchById(lendingUsername, loanObjectName);
                returnee.add(new LoanRequest(borrowingUsername, loanPost));

            }

            return returnee;

        } catch (DAOException | SQLException e) {
            System.out.println("        [DAO][CE] SQL ERROR: " + e.getMessage());
            throw new CriticalException("SQL ERROR: " + e.getMessage());
        }
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {

        try (Connection conn = ConnectionFactory.upgrade();
             PreparedStatement stmt = conn.prepareStatement(insertionQuery)) {

            stmt.setString(1, loanRequest.getBorrowingUsername());
            stmt.setString(2, loanRequest.getLoanPost().getLendingUsername());
            stmt.setString(3, loanRequest.getLoanPost().getLoanObjectName());

            if(stmt.executeUpdate() == 0){
                System.out.println("        [DAO][NCE] La query non ha aggionrato nessuna riga");
                throw new DAOException("Qualcosa è andato storto nell'esecuzione della query");
            }
            System.out.println("        [DAO] Query executed");

        } catch (SQLException e) {
            System.out.println("        [DAO][CE] SQL ERROR: " + e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void deleteAllRelative(LoanRequest loanRequest) throws DAOException {
        //This method is empty as its action can be performed via DBMS means
    }

}
