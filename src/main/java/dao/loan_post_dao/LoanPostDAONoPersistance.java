package dao.loan_post_dao;

import entity.loan.loan_post.LoanPost;
import exceptions.CriticalException;
import exceptions.DAOException;
import repository.LoanPostRepository;

import java.util.ArrayList;

public class LoanPostDAONoPersistance implements LoanPostDAO{
    String username;
    LoanPostRepository repository = LoanPostRepository.getInstance();
    ArrayList<LoanPost> loanPostList;

    public LoanPostDAONoPersistance(String username) {
        this.loanPostList = repository.getLoanPostList();
        this.username = username;
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException, CriticalException {
        System.out.println("        [DAO] Adding new loanPost to volatile list");
        loanPostList.add(loanPost);
        repository.setLoanPostList(loanPostList);
    }

    @Override
    public ArrayList<LoanPost> fetchAllLoanPosts() throws DAOException, CriticalException {
        return repository.getLoanPostList();
    }
}
