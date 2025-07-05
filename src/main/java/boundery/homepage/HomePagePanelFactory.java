package boundery.homepage;

import controller.homepage_controller.HomePageController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class HomePagePanelFactory {
    SessionInfo sessionInfo;

    public HomePagePanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(HomePageController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new HomepageBounderyCentralPannelV1(controller);
            }

            case null, default -> {
                throw new FactoryException("[HOMEPAGE-PANEL-FACTORY][CE] Reached unreachable code");
            }
        }

        return returnee;

    }
}
