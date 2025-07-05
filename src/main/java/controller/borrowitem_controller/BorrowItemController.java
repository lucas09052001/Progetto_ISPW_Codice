package controller.borrowitem_controller;

import entity.loan.loan_post.LoanPostDTO;

public interface BorrowItemController {
    void getAllLoanPosts();
    LoanPostDTO handNext();
    void submitRequest(LoanPostDTO loanPostDTO);
}
