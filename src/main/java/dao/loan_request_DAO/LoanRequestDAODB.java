package dao.loan_request_DAO;

import com.fasterxml.jackson.databind.node.FloatNode;
import dao.ConnectionFactory;
import entity.loan.loan_request.LoanRequest;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanRequestDAODB implements LoanRequestDAO{

    String username;

    public LoanRequestDAODB(String username){
        this.username = username;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {
        String insertionQuery = "INSERT INTO LoanRequest (borrowingUsername, lendingUsername, loanObjectName) VALUES (?, ?, ?)";

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
            throw new CriticalException("SQL ERROR: " + e.getMessage());
        }
    }
}
