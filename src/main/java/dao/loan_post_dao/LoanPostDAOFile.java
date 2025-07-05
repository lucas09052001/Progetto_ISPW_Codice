package dao.loan_post_dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.loan.loan_post.LoanPost;
import exceptions.DAOException;
import utilities.PathUtility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoanPostDAOFile implements LoanPostDAO{
    String pathToJson = PathUtility.getPathToLoanPostJson();
    ObjectMapper mapper = new ObjectMapper();
    File file;

    public LoanPostDAOFile(){
        this.file = new File(pathToJson);
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException {

        System.out.println("        [DAO] Initiating submit operation");
        try{

            System.out.println("        [DAO] Buffering current loan posts");
            ArrayList<LoanPost> currentPosts;
            currentPosts = mapper.readValue(file, new TypeReference<ArrayList<LoanPost>>() {});


            System.out.println("        [DAO] Adding new loan post");
            currentPosts.add(loanPost);

            System.out.println("        [DAO] Writing to Json updated list");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentPosts);
            System.out.println("        [DAO] Done");

        } catch (IOException e) {
            System.out.print("        [DAO][EE] Something went wrong during writing to Json: ");
            System.out.println(e.getMessage());
            throw new DAOException("Something went wrong during writing to Json");
        }

    }

    @Override
    public ArrayList<LoanPost> fetchAll() throws DAOException {
        try {
            return mapper.readValue(new File(PathUtility.getPathToLoanPostJson()), new TypeReference<ArrayList<LoanPost>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException {

        try {
            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanPost> buffer = mapper.readValue(new File("resources/Json/loanPost.json"), new TypeReference<ArrayList<LoanPost>>() {});
            System.out.println("        [DAO] Extracting data of interest");
            for(LoanPost l : buffer){
                if(l.getLendingUsername().equals(lendingUsername) && l.getLoanObjectName().equals(loanObjectName)){
                    return l;
                }
            }
            return null;
        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void deleteByID(LoanPost loanPost) throws DAOException {

        try {
            File filecurr = new File(PathUtility.getPathToLoanPostJson());

            System.out.println("        [DAO] Starting deleteById");

            System.out.println("        [DAO] Buffering Json");
            ArrayList<LoanPost> buffer = mapper.readValue(filecurr, new TypeReference<>() {});

            System.out.println("        [DAO] Deleting entity");
            for(LoanPost l : buffer){
                if(l.getLendingUsername().equals(loanPost.getLendingUsername()) && l.getLoanObjectName().equals(loanPost.getLoanObjectName())){
                    buffer.remove(l);
                    break;
                }
            }

            System.out.println("        [DAO] Saving Json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(filecurr, buffer);
        } catch (IOException e) {
            System.out.println("        [DAO][CE] Something went wrong during Json manipulation");
            throw new DAOException("Something went wrong during the procedure");
        }
    }
}
