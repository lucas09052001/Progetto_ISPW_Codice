package boundery.notification_boundery;

import controller.notification_controller.NotificationController;
import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class NotificationPanelFactory {

    SessionInfo sessionInfo;

    public NotificationPanelFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JPanel generate(NotificationController controller) throws FactoryException {

        JPanel returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new NotificationCentralPanelV1(controller);
            }
            case null, default -> {
                throw new FactoryException("Reached unreachable code");
            }
        }
        return returnee;
    }


}
