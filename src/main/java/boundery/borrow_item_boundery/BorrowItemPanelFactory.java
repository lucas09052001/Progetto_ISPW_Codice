package boundery.borrow_item_boundery;

import controller.borrowitem_controller.BorrowItemController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class BorrowItemPanelFactory {

    SessionInfo sessionInfo;

    public BorrowItemPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(BorrowItemController controller) throws FactoryException {
        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new BorrowItemPanelV1(controller);
            }
            case null, default -> {
                throw new FactoryException("[BORROWITEM-PANEL-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }

}
