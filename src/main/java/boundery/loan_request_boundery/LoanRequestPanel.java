package boundery.loan_request_boundery;

import boundery.micro_components.InteractivePanel;
import controller.LoanRequestController;
import entity.loan.loan_request.LoanRequestDTO;
import repository.PathRepository;

import javax.swing.*;
import java.util.ArrayList;


public class LoanRequestPanel extends InteractivePanel {
    LoanRequestDTO dto;
    LoanRequestController controller;

    public LoanRequestPanel() {
        super();
    }

    public LoanRequestDTO getDto() {
        return dto;
    }

    public void setDto(LoanRequestDTO dto) {
        this.dto = dto;
    }

    public LoanRequestController getController() {
        return controller;
    }

    public void setController(LoanRequestController controller) {
        this.controller = controller;
    }

    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    protected String getButtonText() {
        if(dto == null){
            return "None";
        }else{
            return "Accept";
        }
    }

    @Override
    protected String getPathToIcon() {
        if(dto == null){
            return PathRepository.getPathToEmptyImage();
        }else{
            return dto.getLoanPost().getPathToImage();
        }
    }

    @Override
    protected ArrayList<String> gatherStringsToShow() {
        ArrayList<String> returnee = new ArrayList<>();

        if(dto == null){
            returnee.add("None");
            returnee.add("No new Loan Requests");
        }else{
            returnee.add(dto.getBorrowingUsername());
            returnee.add(dto.getLoanPost().getLoanObjectName());
        }
        return returnee;
    }

    @Override
    protected void handleButtonEvent() {
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.acceptRequest(dto);
    }
}
