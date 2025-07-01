package dao.loan_effective_dao;


import dao.ConnectionFactory;
import entity.loan.loan_effective.LoanEffective;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanEffectiveDAODB implements LoanEffectiveDAO {

    String username;
    final String saveQuery = "INSERT INTO LoanEffective(borrowingUsername, lendingUsername, loanObjectName) VALUES(?,?,?)";

    public LoanEffectiveDAODB(String username){
        this.username = username;
    }

    @Override
    public void save(LoanEffective loanEffective) throws DAOException {
        try (Connection conn = ConnectionFactory.upgrade();
             PreparedStatement stmt = conn.prepareStatement(saveQuery)) {

            stmt.setString(1, loanEffective.getBorrowingUsername());
            stmt.setString(2, loanEffective.getLendingUsername());
            stmt.setString(3, loanEffective.getLoanObjectName());

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
