package repository;

import entity.loan.LoanInterval;
import entity.loan.loan_post.LoanPost;
import entity.loan.loan_request.LoanRequest;
import utilities.PathUtility;

import java.util.ArrayList;

public class LoanRequestRepository {

    private static LoanRequestRepository instance;
    private ArrayList<LoanRequest> loanRequestList;

    private LoanRequestRepository() {
        loanRequestList = new ArrayList<>();

        // Dati di esempio
        loanRequestList.add(new LoanRequest(
                "alice",
                new LoanPost("bob", "Calcolatrice", "Prestito per compito in classe", LoanInterval.DAY, PathUtility.getPathToEmptyImage())
        ));

        loanRequestList.add(new LoanRequest(
                "bob",
                new LoanPost("alice", "Libro di matematica", "Uso per studio individuale", LoanInterval.WEEK, PathUtility.getPathToEmptyImage())
        ));

        loanRequestList.add(new LoanRequest(
                "alice",
                new LoanPost("bob", "Appunti di chimica", "Consultazione laboratorio", LoanInterval.HOUR, PathUtility.getPathToEmptyImage())
        ));
    }

    public static LoanRequestRepository getInstance() {
        if (instance == null) {
            instance = new LoanRequestRepository();
        }
        return instance;
    }

    public ArrayList<LoanRequest> getLoanRequestList() {
        return loanRequestList;
    }

    public void setLoanRequestList(ArrayList<LoanRequest> loanRequestList) {
        this.loanRequestList = loanRequestList;
    }

    public void addLoanRequest(LoanRequest request) {
        loanRequestList.add(request);
    }
}

