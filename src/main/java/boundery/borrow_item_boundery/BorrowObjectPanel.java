package boundery.borrow_item_boundery;

import boundery.general.micro_components.InteractivePanel;
import controller.borrowitem_controller.BorrowItemController;
import entity.loan.loan_post.LoanPostDTO;
import repository.PathRepository;

import javax.swing.*;
import java.util.ArrayList;

public class BorrowObjectPanel extends InteractivePanel {

    LoanPostDTO loanPostDTO;
    BorrowItemController controller;

    BorrowObjectPanel(){
        super();
    }

    @Override
    public void setUp() {
        super.setUp();
    }

    public void setController(BorrowItemController controller){
        this.controller = controller;
    }

    public void setLoanPostDTO(LoanPostDTO loanPostDTO) {
        this.loanPostDTO = loanPostDTO;
    }

    @Override
    protected String getButtonText() {
        if(loanPostDTO == null){
            return "None";
        }else{
            return "Submit";
        }
    }

    @Override
    protected String getPathToIcon() {
        if(loanPostDTO == null){
            return PathRepository.getPathToEmptyImage();
        }else{
            return loanPostDTO.getPathToIcon();
        }
    }

    @Override
    protected ArrayList<String> gatherStringsToShow() {
        ArrayList<String> curr = new ArrayList<>();

        if(loanPostDTO == null){
            curr.add("None");
            curr.add("No new loan Post");
            curr.add("No new loan Post");
            return curr;
        }else{
            curr.add(loanPostDTO.getLendingUsername());
            curr.add(loanPostDTO.getLoanObjectName());
            curr.add(loanPostDTO.getLoanDescription());
            return curr;
        }

    }

    @Override
    protected void handleButtonEvent() {
        System.out.println("[BOUNDERY] Handling submitRequest event");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.submitRequest(loanPostDTO);
    }
}
