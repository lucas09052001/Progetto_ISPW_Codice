package dao.loan_effective_dao;

import entity.loan.loan_effective.LoanEffective;
import exceptions.DAOException;
import repository.LoanEffectiveRepository;

import java.util.ArrayList;

public class LoanEffectiveDAONoPersistance implements LoanEffectiveDAO{
    String username;
    LoanEffectiveRepository repository;

    public LoanEffectiveDAONoPersistance(String username) {
        this.username = username;
        this.repository = LoanEffectiveRepository.getInstance();
    }

    @Override
    public void save(LoanEffective loanEffective) throws DAOException {
        System.out.println("        [DAO] Starting save operation");

        System.out.println("        [DAO] Buffering repository");
        ArrayList<LoanEffective> buffer = repository.getLoanEffectiveList();

        System.out.println("        [DAO] Adding entity");
        buffer.add(loanEffective);

        System.out.println("        [DAO] Saving repository");
        repository.setLoanEffectiveList(buffer);
    }
}
