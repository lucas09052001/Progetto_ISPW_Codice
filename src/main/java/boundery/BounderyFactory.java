package boundery;

import boundery.authenticate_boundery.AuthenticateBounderyMainFrame;
import boundery.error_boundery.ErrorBounderyMainFrame;
import boundery.homepage.HomepageBounderyMainFrame;
import boundery.loan_item.LoanItemBounderyMainFrame;
import boundery.notification_boundery.NotificationBounderyMainFrame;
import boundery.profile_boundery.ProfileBounderyMainFrame;
import entity.SessionInfo;
import exceptions.CriticalException;

import javax.swing.*;

public class BounderyFactory {

    private BounderyFactory() {
        throw new IllegalStateException("Utility class");
    }


    public static JFrame generateBoundery() {

        BounderyEnum bounderyEnum = SessionInfo.getSessionInfo().getNextBoundery();
        switch (bounderyEnum){
            case HOMEPAGE:
                System.out.println("[SYSTEM] BounderyFactory creating HomepageBoundery");
                return new HomepageBounderyMainFrame();
            case AUTHENTICATE:
                System.out.println("[SYSTEM] BounderyFactory creating AuthenticateBoundery");
                return new AuthenticateBounderyMainFrame();
            case PROFILE:
                System.out.println("    [BOUNDERY-FACTORY] BounderyFactory creating ProfileBoundery");
                return new ProfileBounderyMainFrame();
            case NOTIFICATION:
                System.out.println("    [BOUNDERY-FACTORY] BounderyFactory creating NotificationBoundery");
                return new NotificationBounderyMainFrame();
            case LOAN_ITEM:
                System.out.println("    [BOUNDERY-FACTORY] BounderyFactory creating LoanItemBoundery");
                return new LoanItemBounderyMainFrame();
            case ERROR:
                System.out.println("    [BOUNDERY-FACTORY] BounderyFactory creating ErrorBoundery");
                return new ErrorBounderyMainFrame();
            default:
                System.out.println("    [BOUNDERY-FACTORY][CE] BounderyFactory does not know this boundery !");
                throw new CriticalException("BounderyFactory crashed the system");
        }

    }

}
