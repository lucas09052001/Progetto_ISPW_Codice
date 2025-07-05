package boundery.loan_request_boundery;

import boundery.general.micro_components.InteractivePanel;
import controller.loanrequest_controller.LoanRequestController;
import entity.loan.loan_request.LoanRequestDTO;
import utilities.PathUtility;

import java.util.ArrayList;


public class LoanRequestPanelV1 extends InteractivePanel {
    transient LoanRequestDTO dto;
    transient LoanRequestController controller;

    public LoanRequestPanelV1() {
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
            return PathUtility.getPathToEmptyImage();
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

        if(!(dto == null)){
            controller.acceptRequest(dto);
        }

    }
}
