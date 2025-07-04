package controller.borrowitem_controller;

import dao.loan_post_dao.LoanPostDAO;
import entity.loan.loan_post.LoanPostDTO;

public interface BorrowItemController {
    void getAllLoanPosts();
    LoanPostDTO handNext();
    void submitRequest(LoanPostDTO loanPostDTO);
}
