package repository;

import entity.loan.loan_effective.LoanEffective;

import java.util.ArrayList;

public class LoanEffectiveRepository {

    private static LoanEffectiveRepository instance;
    private ArrayList<LoanEffective> loanEffectiveList;

    private LoanEffectiveRepository() {
        loanEffectiveList = new ArrayList<>();

        // Dati di esempio
        loanEffectiveList.add(new LoanEffective("bob", "alice", "Borsa per computer"));
        loanEffectiveList.add(new LoanEffective("alice", "bob", "Temperino"));
        loanEffectiveList.add(new LoanEffective("carlos", "bob", "Borraccia"));
        loanEffectiveList.add(new LoanEffective("carlos", "alice", "Appunti Chimica"));

    }

    public static LoanEffectiveRepository getInstance() {
        if (instance == null) {
            instance = new LoanEffectiveRepository();
        }
        return instance;
    }

    public ArrayList<LoanEffective> getLoanEffectiveList() {
        return loanEffectiveList;
    }

    public void setLoanEffectiveList(ArrayList<LoanEffective> loanEffectiveList) {
        this.loanEffectiveList = loanEffectiveList;
    }

    public void addLoanEffective(LoanEffective loanEffective) {
        loanEffectiveList.add(loanEffective);
    }

    public boolean removeLoanEffective(String borrowingUsername, String lendingUsername, String loanObjectName) {
        return loanEffectiveList.removeIf(e ->
                e.getBorrowingUsername().equals(borrowingUsername) &&
                        e.getLendingUsername().equals(lendingUsername) &&
                        e.getLoanObjectName().equals(loanObjectName)
        );
    }
}
