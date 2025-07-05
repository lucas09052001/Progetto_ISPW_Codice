package repository;
import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import utilities.PathUtility;

import java.util.ArrayList;

public class LoanPostRepository {

    private static LoanPostRepository instance;
    private ArrayList<LoanPost> loanPostList;

    private LoanPostRepository() {
        loanPostList = new ArrayList<>();

        // Dati di esempio
        loanPostList.add(new LoanPost("alice", "Calcolatrice", "Prestito per compito in classe", LoanInterval.DAY, PathUtility.getPathToEmptyImage()));
        loanPostList.add(new LoanPost("bob", "Appunti di chimica", "Per consultazione durante laboratorio", LoanInterval.HOUR, PathUtility.getPathToEmptyImage()));
        loanPostList.add(new LoanPost("alice", "Libro di matematica", "Uso per studio individuale", LoanInterval.WEEK, PathUtility.getPathToEmptyImage()));
        loanPostList.add(new LoanPost("bob", "Quaderno di storia", "Prestito per una giornata", LoanInterval.DAY, PathUtility.getPathToEmptyImage()));
    }

    public static LoanPostRepository getInstance() {
        if (instance == null) {
            instance = new LoanPostRepository();
        }
        return instance;
    }

    public ArrayList<LoanPost> getLoanPostList() {
        return loanPostList;
    }

    public void setLoanPostList(ArrayList<LoanPost> loanPostList) {
        this.loanPostList = loanPostList;
    }

    public void addLoanPost(LoanPost post) {
        loanPostList.add(post);
    }
}
