package dao.loan_request_dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.loan.loan_request.LoanRequest;
import exceptions.DAOException;
import utilities.PathUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoanRequestDAOFile implements LoanRequestDAO{
    String username;

    ObjectMapper mapper;
    File file;

    public LoanRequestDAOFile(String username){
        this.username = username;

        mapper = new ObjectMapper();
    }

    @Override
    public void submitRequest(LoanRequest loanRequest) throws DAOException {

        System.out.println("        [DAO] Initiating submitRequest operation");
        try{
            file = new File(PathUtility.getPathToLoanRequestJson());

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
    public ArrayList<LoanRequest> fetchAll() throws DAOException{

        ArrayList<LoanRequest> returnee = new ArrayList<>();

        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanRequest> buffer = mapper.readValue(new File(PathUtility.getPathToLoanRequestJson()), new TypeReference<>() {});
            System.out.println("        [DAO] Extracting entities of interest");
            for(LoanRequest l : buffer){
                if(!l.getBorrowingUsername().equals(username)){
                    returnee.add(l);
                }
            }

            return returnee;

        } catch (IOException e) {
            System.out.println("        [DAO][CE] Something went wrong while reading Json file");
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void deleteAllRelative(LoanRequest loanRequest) throws DAOException {
        File file = new File(PathUtility.getPathToLoanRequestJson());
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
            System.out.println("        [DAO][CE] Something went wrong while reading Json file");
            throw new DAOException();
        }

    }

}
