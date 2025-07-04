package boundery.general.macro_components;

import controller.dashboard_controller.DashBoardController;
import entity.SessionInfo;

import javax.swing.*;

public class DashBoardPanelFactory {

    SessionInfo sessionInfo;

    public DashBoardPanelFactory(){
        sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(DashBoardController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new DashBoardPanelV1(controller);
            }

            case null, default -> {
                System.out.println("    [DASHBOARD-FACTORY][CE] Reached unreachable code");
                throw new RuntimeException("[DASHBOARD-FACTORY][CE] Reached unreachable code");
            }
        }

        return returnee;

    }

}
