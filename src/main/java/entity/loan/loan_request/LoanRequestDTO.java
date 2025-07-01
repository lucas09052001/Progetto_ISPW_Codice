package entity.loan.loan_request;

import entity.loan.loan_post.LoanPost;

public class LoanRequestDTO {
    private String borrowingUsername;
    private LoanPost loanPost;

    public LoanRequestDTO(){}

    public LoanRequestDTO(LoanRequest loanRequest) {
        this.borrowingUsername = loanRequest.getBorrowingUsername();
        this.loanPost = loanRequest.getLoanPost();
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
