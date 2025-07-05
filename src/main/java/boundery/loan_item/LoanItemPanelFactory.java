package boundery.loan_item;

import controller.loanitem_controller.LoanItemController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class LoanItemPanelFactory {
    SessionInfo sessionInfo;
    public LoanItemPanelFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(LoanItemController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new LoanItemCentralPanelV1(controller);
            case null, default -> throw new FactoryException("[LOANITEM-PANEL-FACTORY] Reached unreachable code");
        }

        return returnee;
    }
}
