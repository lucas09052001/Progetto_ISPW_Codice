package dao.loan_post_dao;

import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import entity.user.User;
import exceptions.CriticalException;
import exceptions.DAOException;
import repository.LoanPostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class LoanPostDAONoPersistance implements LoanPostDAO{

    LoanPostRepository repository = LoanPostRepository.getInstance();
    ArrayList<LoanPost> loanPostList;

    public LoanPostDAONoPersistance() {
        loanPostList = repository.getLoanPostList();
    }

    @Override
    public void submit(LoanPost loanPost) throws DAOException, CriticalException {
        System.out.println("        [DAO] Adding new loanPost to volatile list");
        loanPostList.add(loanPost);
        repository.setLoanPostList(loanPostList);
    }
}
