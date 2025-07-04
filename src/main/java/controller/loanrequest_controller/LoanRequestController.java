package controller.loanrequest_controller;

import entity.loan.loan_request.LoanRequestDTO;

public interface LoanRequestController {

    void fetchAll();
    void acceptRequest(LoanRequestDTO dto);
    LoanRequestDTO handNext();

}
