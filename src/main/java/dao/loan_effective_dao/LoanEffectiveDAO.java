package dao.loan_effective_dao;

import entity.loan.loan_effective.LoanEffective;
import exceptions.DAOException;

public interface LoanEffectiveDAO {
    void save(LoanEffective loanEffective) throws DAOException;
}
