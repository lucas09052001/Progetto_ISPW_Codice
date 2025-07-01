package dao.loan_request_DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.loan_post_dao.LoanPostDAO;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_post.LoanPostDTO;
import entity.loan.loan_request.LoanRequest;
import entity.loan.loan_request.LoanRequestDTO;
import exceptions.CriticalException;
import exceptions.DAOException;
import repository.PathRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class LoanRequestDAOFile implements LoanRequestDAO{
    String username;

    ObjectMapper mapper;
    File file;

    LoanPostDAO loanPostDAO;

    public LoanRequestDAOFile(String username){
        this.username = username;
        mapper = new ObjectMapper();
    }

    public LoanPostDAO getLoanPostDAO() {
        return loanPostDAO;
    }

    public void setLoanPostDAO(LoanPostDAO loanPostDAO) {
        this.loanPostDAO = loanPostDAO;
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {

        System.out.println("        [DAO] Initiating submitRequest operation");
        try{
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


    @Override
    public ArrayList<LoanRequest> fetchAll() {

        ArrayList<LoanRequest> returnee = new ArrayList<>();

        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanRequest> buffer = mapper.readValue(new File(PathRepository.getPathToLoanRequestJson()), new TypeReference<ArrayList<LoanRequest>>() {});
            System.out.println("        [DAO] Extracting entities of interest");
            for(LoanRequest l : buffer){
                if(!l.getBorrowingUsername().equals(username)){
                    returnee.add(l);
                }
            }

            return returnee;

        } catch (IOException e) {
            System.out.println("        [DAO][CE] Something went wrong while reading Json file:");
            System.out.println(e.getMessage());
            throw new CriticalException();
        }
    }

    @Override
    public void deleteAllRelative(LoanRequest loanRequest) throws DAOException {
        File file = new File(PathRepository.getPathToLoanRequestJson());
        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanRequest> buffer = mapper.readValue(file, new TypeReference<ArrayList<LoanRequest>>() {});
            System.out.println("        [DAO] Deleting entities of interest");

            String compareeUsername = loanRequest.getLoanPost().getLendingUsername();
            String compareeName = loanRequest.getLoanPost().getLoanObjectName();

            ArrayList<Integer> indexesToBeRemoved = new ArrayList<>();
            Integer index = 0;
            for(LoanRequest l : buffer){
                String usernameToBeCompared = l.getLoanPost().getLendingUsername();
                String objectNameToBeCompared = l.getLoanPost().getLoanObjectName();

                if(usernameToBeCompared.equals(compareeUsername) && objectNameToBeCompared.equals(compareeName)){
                    indexesToBeRemoved.add(index);
                }

                index++;
            }

            for (Integer i : indexesToBeRemoved){
                int removee = i;
                buffer.remove(removee);
            }

            System.out.println("        [DAO] Updating Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, buffer);

        } catch (IOException e) {
            System.out.println("        [DAO][CE] Something went wrong while reading Json file:");
            System.out.println(e.getMessage());
            throw new CriticalException();
        }

    }

}
