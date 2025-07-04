package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import entity.SessionInfo;

import javax.swing.*;

public class ProfileCentralPanelFactory {

    SessionInfo sessionInfo;

    public ProfileCentralPanelFactory() {
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(ProfileController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new ProfileCentralPanelV1(controller);
            }
            case null, default -> {
                System.out.println("    [PROFILE-PANEL-FACTORY] Reached unreachable code");
                throw new RuntimeException("[PROFILE-PANEL-FACTORY] Reached unreachable code");
            }
        }

        return returnee;
    }
}
