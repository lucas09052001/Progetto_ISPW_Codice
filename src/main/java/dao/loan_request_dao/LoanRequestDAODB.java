package dao.loan_request_dao;

import dao.ConnectionFactory;
import dao.loan_post_dao.LoanPostDAO;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;

public class LoanRequestDAODB implements LoanRequestDAO{

    private String username;
    private LoanPostDAO loanPostDAO;
    static final String INSERTION_QUERY = "INSERT INTO LoanRequest (borrowingUsername, lendingUsername, loanObjectName) VALUES (?, ?, ?)";
    static final String FETCH_ALL_QUERY = "SELECT * FROM LoanRequest WHERE borrowingUsername <> ?";


    public LoanRequestDAODB(String username, LoanPostDAO loanPostDAO){
        this.username = username;
        this.loanPostDAO = loanPostDAO;
    }

    @Override
    public ArrayList<LoanRequest> fetchAll() throws  DAOException{
        //Get all entities from persistency (except the requests made by the current user)
        ArrayList<LoanRequest> returnee = new ArrayList<>();

        try (Connection conn = ConnectionFactory.upgrade();
             PreparedStatement stmt = conn.prepareStatement(FETCH_ALL_QUERY)) {

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
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {

        try (Connection conn = ConnectionFactory.upgrade();
             PreparedStatement stmt = conn.prepareStatement(INSERTION_QUERY)) {

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
        //This method is empty as its action can be performed via DBMS means ("on delete cascade")
    }

}
