package boundery.loan_request_boundery;

import boundery.loan_item.LoanItemCentralPanelV1;
import controller.loanrequest_controller.LoanRequestController;
import entity.SessionInfo;

import javax.swing.*;

public class LoanRequestCentralPanelFactory {

    SessionInfo sessionInfo;

    public LoanRequestCentralPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(LoanRequestController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new LoanRequestCentralPanelV1(controller);
            }
            case null, default -> {
                throw new RuntimeException("[LOANREQUEST-PANEL-FACTORY]  Reached unreachable code");
            }
        }
        return returnee;
    }

}
