package dao.loan_request_DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import repository.PathRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoanRequestDAOFile implements LoanRequestDAO{
    String username;

    ObjectMapper mapper;
    File file;

    public LoanRequestDAOFile(String username){
        this.username = username;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {

        System.out.println("        [DAO] Initiating submitRequest operation");
        try{
            mapper = new ObjectMapper();
            file = new File(PathRepository.getPathToLoanRequestJson());

            System.out.println("        [DAO] Buffering current loan posts");
            ArrayList<LoanRequest> currentRequests;
            currentRequests = mapper.readValue(file, new TypeReference<ArrayList<LoanRequest>>() {});


            System.out.println("        [DAO] Adding new loan request");
            currentRequests.add(loanRequest);

            System.out.println("        [DAO] Writing to Json updated list");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentRequests);
            System.out.println("        [DAO] Done");

        } catch (IOException e) {
            System.out.print("        [DAO][CE] Something went wrong during writing to Json: ");
            System.out.println(e.getMessage());
            throw new DAOException("Something went wrong during writing to Json");
        } catch (NullPointerException e){
            System.out.println("        [DAO][CE] Error: " + e.getMessage());
        }
    }
}
