package boundery.authenticate_boundery;

import controller.authenticate_controller.AuthenticateController;
import entity.SessionInfo;

import javax.swing.*;

public class AuthenticatePanelFactory {
    SessionInfo sessionInfo;

    public AuthenticatePanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(AuthenticateController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){

            case V1 -> {
                returnee = new AuthenticateCentralPannel(controller);
            }

            case null, default -> {   //Questo codice è da togliere dato che SessionInfo avrà necessariaemente Version settato (da constructor)
                throw new RuntimeException();
            }

        }

        return returnee;
    }

}
