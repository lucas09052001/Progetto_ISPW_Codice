package controller.loanitem_controller;

import entity.loan.LoanInterval;

public interface LoanItemController {
    void submit(String loanObjectName, String loanDescription, LoanInterval loanInterval, String pathToIcon);
}
