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

        System.out.println("[LOAN-REQUEST-DAO] Initiating submitRequest operation");
        try{
            file = new File(PathUtility.getPathToLoanRequestJson());

            ArrayList<LoanRequest> currentRequests;
            currentRequests = mapper.readValue(file, new TypeReference<>() {});

            currentRequests.add(loanRequest);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentRequests);

        } catch (NullPointerException | IOException e) {
            System.out.print("[LOAN-REQUEST-DAO][EE] Something went wrong during writing to Json: ");
            throw new DAOException(e.getMessage());
        }
    }


    @Override
    public ArrayList<LoanRequest> fetchAll() throws DAOException{
        //Fetch all entities from persistency (the ones related to loan posts made by logged user)
        ArrayList<LoanRequest> returnee = new ArrayList<>();

        try {
            System.out.println("[LOAN-REQUEST-DAO] Buffering Json");
            ArrayList<LoanRequest> buffer = mapper.readValue(new File(PathUtility.getPathToLoanRequestJson()), new TypeReference<>() {});
            System.out.println("[LOAN-REQUEST-DAO] Extracting entities of interest");
            for(LoanRequest l : buffer){
                if(!l.getBorrowingUsername().equals(username)){
                    returnee.add(l);
                }
            }

            return returnee;

        } catch (IOException e) {
            System.out.println("[LOAN-REQUEST-DAO][EE] Something went wrong while reading Json file");
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void deleteAllRelative(LoanRequest loanRequest) throws DAOException {
        //Delete all loan requests relative to a certain loan post
        File file = new File(PathUtility.getPathToLoanRequestJson());
        try {
            System.out.println("[LOAN-REQUEST-DAO] Buffering Json");
            ArrayList<LoanRequest> buffer = mapper.readValue(file, new TypeReference<>() {});

            //Get key to loan post through passed loan request
            String compareeUsername = loanRequest.getLoanPost().getLendingUsername();
            String compareeName = loanRequest.getLoanPost().getLoanObjectName();

            //Initialize array that holds the indexes to be removed
            ArrayList<Integer> indexesToBeRemoved = new ArrayList<>();
            Integer index = 0;

            //Find indexes to be removed
            for(LoanRequest l : buffer){
                String usernameToBeCompared = l.getLoanPost().getLendingUsername();
                String objectNameToBeCompared = l.getLoanPost().getLoanObjectName();

                if(usernameToBeCompared.equals(compareeUsername) && objectNameToBeCompared.equals(compareeName)){
                    indexesToBeRemoved.add(index);
                }

                index++;
            }

            //Remove indexes from buffered entities
            for (Integer i : indexesToBeRemoved){
                int removee = i;
                buffer.remove(removee);
            }

            //Update persistency layer
            System.out.println("[LOAN-REQUEST-DAO] Updating Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, buffer);

        } catch (IOException e) {
            System.out.println("[LOAN-REQUEST-DAO][EE] Something went wrong while reading Json file");
            throw new DAOException();
        }

    }

}
