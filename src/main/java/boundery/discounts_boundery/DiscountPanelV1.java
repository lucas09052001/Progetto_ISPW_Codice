package boundery.discounts_boundery;

import boundery.general.micro_components.InteractivePanel;
import controller.discount_controller.DiscountController;
import controller.discount_controller.DiscountControllerV1;
import entity.discount.DiscountDTO;
import repository.PathRepository;

import javax.swing.*;
import java.util.ArrayList;

public class DiscountPanelV1 extends InteractivePanel {

    DiscountDTO discountDTO;
    DiscountController controller;

    public DiscountPanelV1() {
        super();
    }

    public DiscountDTO getDiscountDTO() {
        return discountDTO;
    }

    public void setDiscountDTO(DiscountDTO discountDTO) {
        this.discountDTO = discountDTO;
    }

    public DiscountController getController() {
        return controller;
    }

    public void setController(DiscountController controller) {
        this.controller = controller;
    }

    @Override
    public void setUp() {
        super.setUp();
    }

    @Override
    protected String getButtonText() {
        if(discountDTO == null){
            return "None";
        }else{
            return "Redeem!";
        }
    }

    @Override
    protected String getPathToIcon() {
        if(discountDTO == null){
            return PathRepository.getPathToEmptyImage();
        }else{
            return discountDTO.getPathToImage();
        }
    }

    @Override
    protected ArrayList<String> gatherStringsToShow() {
        ArrayList<String> returnee = new ArrayList<>();
        if(discountDTO == null){
            returnee.add("None");
            returnee.add("No new discounts");
            returnee.add("No new discounts");
        }else{
            returnee.add("Discount Name: " + discountDTO.getName());
            returnee.add("Discount percentage: " + discountDTO.getPercentage() + "%");
            returnee.add("Cost in points: " + discountDTO.getCost() + "points!");
        }
        return returnee;
    }

    @Override
    protected void handleButtonEvent() {

        if(!(discountDTO == null)){
            System.out.println("[BOUNDERY] Handling Redeem event");
            controller.redeemDiscount(discountDTO);
        }

    }
}
