package dao.loan_effective_dao;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.loan.loan_effective.LoanEffective;
import entity.loan.loan_request.LoanRequest;
import exceptions.CriticalException;
import exceptions.DAOException;
import repository.PathRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoanEffectiveDAOFile implements LoanEffectiveDAO{
    String username;
    ObjectMapper mapper = new ObjectMapper();

    public LoanEffectiveDAOFile(String username) {
        this.username = username;
    }

    @Override
    public void save(LoanEffective loanEffective) throws DAOException {
        File file = new File(PathRepository.getPathToLoanEffectiveJson());
        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanEffective> buffer = mapper.readValue(file, new TypeReference<ArrayList<LoanEffective>>() {});

            System.out.println("        [DAO] Inserting entity");
            buffer.add(loanEffective);

            System.out.println("        [DAO] Saving Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, buffer);

        } catch (IOException e) {
            System.out.println("        [DAO][CE] Something went wrong while reading Json file:");
            System.out.println(e.getMessage());
            throw new CriticalException();
        }
    }
}
