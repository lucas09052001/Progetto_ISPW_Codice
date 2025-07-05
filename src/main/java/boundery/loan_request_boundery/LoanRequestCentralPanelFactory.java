package boundery.loan_request_boundery;

import controller.loanrequest_controller.LoanRequestController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class LoanRequestCentralPanelFactory {

    SessionInfo sessionInfo;

    public LoanRequestCentralPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(LoanRequestController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new LoanRequestCentralPanelV1(controller);
            case null, default -> throw new FactoryException("[LOANREQUEST-PANEL-FACTORY]  Reached unreachable code");
        }
        return returnee;
    }

}
