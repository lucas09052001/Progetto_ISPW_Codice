package entity.loan.loan_effective;

public class LoanEffective {
    String borrowingUsername;
    String lendingUsername;
    String loanObjectName;

    public LoanEffective(){}

    public LoanEffective(String borrowingUsername, String lendingUsername, String loanObjectName) {
        this.borrowingUsername = borrowingUsername;
        this.lendingUsername = lendingUsername;
        this.loanObjectName = loanObjectName;
    }

    public String getBorrowingUsername() {
        return borrowingUsername;
    }

    public void setBorrowingUsername(String borrowingUsername) {
        this.borrowingUsername = borrowingUsername;
    }

    public String getLendingUsername() {
        return lendingUsername;
    }

    public void setLendingUsername(String lendingUsername) {
        this.lendingUsername = lendingUsername;
    }

    public String getLoanObjectName() {
        return loanObjectName;
    }

    public void setLoanObjectName(String loanObjectName) {
        this.loanObjectName = loanObjectName;
    }
}
