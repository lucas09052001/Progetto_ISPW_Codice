package boundery.notification_boundery;

import controller.notification_controller.NotificationController;
import entity.SessionInfo;

import javax.swing.*;

public class NotificationPanelFactory {

    SessionInfo sessionInfo;

    public NotificationPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(NotificationController controller){

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new NotificationCentralPanelV1(controller);
            }
            case null, default -> {
                System.out.println("    [NOTIFICATION-PANEL-FACTORY] Reached unreachable code");
                throw new RuntimeException("Reached unreachable code");
            }
        }
        return returnee;
    }


}
