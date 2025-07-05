package boundery.general.macro_components;

import controller.dashboard_controller.DashBoardController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class DashBoardPanelFactory {

    SessionInfo sessionInfo;

    public DashBoardPanelFactory(){
        sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(DashBoardController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new DashBoardPanelV1(controller);
            }

            case null, default -> {
                throw new FactoryException("[DASHBOARD-FACTORY][CE] Reached unreachable code");
            }
        }

        return returnee;

    }

}
