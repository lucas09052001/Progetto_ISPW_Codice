package boundery.discounts_boundery;

import controller.discount_controller.DiscountController;
import entity.SessionInfo;

import javax.swing.*;

public class DiscountCentralPanelFactory {

    SessionInfo sessionInfo;

    public DiscountCentralPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(DiscountController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new DiscountCentralPanelV1(controller);
            }
            case null, default -> {
                throw new RuntimeException("[DISCOUNT-PANEL-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
