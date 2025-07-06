package entity.loan.loan_post;

import entity.loan.LoanInterval;

public class LoanPost {
    private String lendingUsername;
    private String loanObjectName;
    private String loanDescription;
    private LoanInterval loanInterval;
    private String pathToImage;

    public LoanPost(){}

    public LoanPost(String loanUsername, String loanObjectName, String loanDescription, LoanInterval loanInterval, String pathToImage) {
        this.lendingUsername = loanUsername;
        this.loanObjectName = loanObjectName;
        this.loanDescription = loanDescription;
        this.loanInterval = loanInterval;
        this.pathToImage = pathToImage;
    }

    public String getLendingUsername() {
        return lendingUsername;
    }

    public void setLendingUsername(String loanUsername) {
        this.lendingUsername = loanUsername;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getLoanObjectName() {
        return loanObjectName;
    }

    public void setLoanObjectName(String loanObjectName) {
        this.loanObjectName = loanObjectName;
    }

    public String getLoanDescription() {
        return loanDescription;
    }

    public void setLoanDescription(String loanDescription) {
        this.loanDescription = loanDescription;
    }

    public LoanInterval getLoanInterval() {
        return loanInterval;
    }

    public void setLoanInterval(LoanInterval loanInterval) {
        this.loanInterval = loanInterval;
    }

}
