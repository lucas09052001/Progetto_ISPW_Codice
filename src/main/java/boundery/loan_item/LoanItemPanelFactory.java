package boundery.loan_item;

import controller.loanitem_controller.LoanItemController;
import entity.SessionInfo;

import javax.management.remote.JMXPrincipal;
import javax.swing.*;

public class LoanItemPanelFactory {
    SessionInfo sessionInfo;
    public LoanItemPanelFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(LoanItemController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new LoanItemCentralPanelV1(controller);
            }
            case null, default -> {
                System.out.println("[LOANITEM-PANEL-FACTORY] Reached unreachable code");
                throw new RuntimeException("[LOANITEM-PANEL-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
