package entity.loan.loan_request;

import entity.loan.loan_post.LoanPost;
import entity.user.User;

public class LoanRequest {
    private String borrowingUsername;
    private LoanPost loanPost;

    public LoanRequest(){}

    public LoanRequest(String borrowingUsername, LoanPost loanPost) {
        this.borrowingUsername = borrowingUsername;
        this.loanPost = loanPost;
    }

    public String getBorrowingUsername() {
        return borrowingUsername;
    }

    public void setBorrowingUsername(String borrowingUsername) {
        this.borrowingUsername = borrowingUsername;
    }

    public LoanPost getLoanPost() {
        return loanPost;
    }

    public void setLoanPost(LoanPost loanPost) {
        this.loanPost = loanPost;
    }
}
