package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import exceptions.CriticalException;
import exceptions.DAOException;
import repository.LoanPostRepository;

import java.util.ArrayList;

public class LoanPostDAONoPersistance implements LoanPostDAO{
    LoanPostRepository repository = LoanPostRepository.getInstance();
    ArrayList<LoanPost> loanPostList;

    public LoanPostDAONoPersistance() {
        this.loanPostList = repository.getLoanPostList();
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException, CriticalException {
        System.out.println("        [DAO] Adding new loanPost to volatile list");
        loanPostList.add(loanPost);
        repository.setLoanPostList(loanPostList);
    }

    @Override
    public ArrayList<LoanPost> fetchAll() throws DAOException, CriticalException {
        return repository.getLoanPostList();
    }

    @Override
    public LoanPost fetchById(String lendingUsername, String loanObjectName) throws DAOException, CriticalException {
        ArrayList<LoanPost> buffer;

        System.out.println("        [DAO] Buffering repository");
        buffer = repository.getLoanPostList();
        System.out.println("        [DAO] Extracting data of interest");
        for(LoanPost l : buffer){
            if(l.getLendingUsername().equals(lendingUsername) && l.getLoanObjectName().equals(loanObjectName)){
                return l;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(LoanPost loanPost) throws DAOException {

        System.out.println("        [DAO] Starting deleteById");
        System.out.println("        [DAO] Buffering repository");
        ArrayList<LoanPost> buffer = repository.getLoanPostList();

        for (LoanPost l : buffer){
            if (l.getLendingUsername().equals(loanPost.getLendingUsername()) && l.getLoanObjectName().equals(loanPost.getLoanObjectName())){
                buffer.remove(l);
                break;
            }
        }
    }
}
