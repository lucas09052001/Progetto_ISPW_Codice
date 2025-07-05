package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class ProfileCentralPanelFactory {

    SessionInfo sessionInfo;

    public ProfileCentralPanelFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(ProfileController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> returnee = new ProfileCentralPanelV1(controller);
            case null, default -> throw new FactoryException("[PROFILE-PANEL-FACTORY] Reached unreachable code");
        }

        return returnee;
    }
}
