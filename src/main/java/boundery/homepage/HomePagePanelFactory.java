package boundery.homepage;

import controller.homepage_controller.HomePageController;
import controller.homepage_controller.HomePageControllerV1;
import entity.SessionInfo;

import javax.swing.*;

public class HomePagePanelFactory {
    SessionInfo sessionInfo;

    public HomePagePanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(HomePageController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new HomepageBounderyCentralPannelV1(controller);
            }

            case null, default -> {
                System.out.println("    [HOMEPAGE-PANEL-FACTORY][CE] Reached unreachable code");
                throw new RuntimeException("[HOMEPAGE-PANEL-FACTORY][CE] Reached unreachable code");
            }
        }

        return returnee;

    }
}
