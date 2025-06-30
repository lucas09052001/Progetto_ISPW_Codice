package entity.loan.loan_post;

import entity.GenericDTO;
import entity.loan.LoanInterval;

public class LoanPostDTO extends GenericDTO {
    int loanPostId;
    String lendingUsername;
    String loanObjectName;
    String loanDescription;
    LoanInterval loanInterval;
    String pathToIcon;

    public LoanPostDTO(LoanPost loanPost) {
        this.lendingUsername = loanPost.getLendingUsername();
        this.loanObjectName = loanPost.getLoanObjectName();
        this.loanDescription = loanPost.getLoanDescription();
        this.loanInterval = loanPost.getLoanInterval();
        this.pathToIcon = loanPost.getPathToImage();
    }

    public LoanPost toEntity(){
        return new LoanPost(lendingUsername, loanObjectName, loanDescription, loanInterval, pathToIcon);
    }

    public int getLoanPostId() {
        return loanPostId;
    }

    public void setLoanPostId(int loanPostId) {
        this.loanPostId = loanPostId;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public void setPathToIcon(String pathToIcon) {
        this.pathToIcon = pathToIcon;
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
