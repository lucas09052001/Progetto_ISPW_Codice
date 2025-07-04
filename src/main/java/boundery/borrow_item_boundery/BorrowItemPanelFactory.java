package boundery.borrow_item_boundery;

import controller.borrowitem_controller.BorrowItemController;
import entity.SessionInfo;

import javax.swing.*;

public class BorrowItemPanelFactory {

    SessionInfo sessionInfo;

    public BorrowItemPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(BorrowItemController controller){
        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new BorrowItemPanelV1(controller);
            }
            case null, default -> {
                System.out.println("[BORROWITEM-PANEL-FACTORY] Reached unreachable code");
                throw new RuntimeException("[BORROWITEM-PANEL-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
