package boundery.authenticate_boundery;

import controller.authenticate_controller.AuthenticateController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class AuthenticatePanelFactory {
    SessionInfo sessionInfo;

    public AuthenticatePanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(AuthenticateController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){

            case V1 -> {
                returnee = new AuthenticateCentralPannel(controller);
            }

            case null, default -> {
                throw new FactoryException("[AUTHENTICATE-PANEL-FACTORY] Reached unreachable code");
            }

        }

        return returnee;
    }

}
