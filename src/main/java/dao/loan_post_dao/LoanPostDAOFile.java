package dao.loan_post_dao;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

public class LoanPostDAOFile implements LoanPostDAO{

    String pathToJson = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/resources/Json/loanPost.Json";
    ObjectMapper mapper = new ObjectMapper();
    File file;

    public LoanPostDAOFile(){
        file = new File(pathToJson);
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException, CriticalException {

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
}
