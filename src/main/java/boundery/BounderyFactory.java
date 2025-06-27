package boundery;

import boundery.authenticate_boundery.AuthenticateBounderyMainFrame;
import boundery.error_boundery.ErrorBounderyMainFrame;
import boundery.homepage.HomepageBounderyMainFrame;
import boundery.profile_boundery.ProfileBounderyMainFrame;
import entity.SessionInfo;
import exceptions.CriticalException;

import javax.swing.*;

public class BounderyFactory {

    private BounderyFactory() {
        throw new IllegalStateException("Utility class");
    }


    public static JFrame generateBoundery() {

        System.out.println("[SYSTEM] BounderyFactory fetching next boundery from SessionInfo");
        BounderyEnum bounderyEnum = SessionInfo.getSessionInfo().getNextBoundery();
        System.out.println("[SYSTEM] Fetched: " + bounderyEnum);

        switch (bounderyEnum){
            case HOMEPAGE:
                System.out.println("[SYSTEM] BounderyFactory creating HomepageBoundery");
                return new HomepageBounderyMainFrame();
            case AUTHENTICATE:
                System.out.println("[SYSTEM] BounderyFactory creating AuthenticateBoundery");
                return new AuthenticateBounderyMainFrame();
            case PROFILE:
                System.out.println("[SYSTEM] BounderyFactory creating ProfileBoundery");
                return new ProfileBounderyMainFrame();
            case ERROR:
                System.out.println("[SYSTEM] BounderyFactory creating ErrorBoundery");
                return new ErrorBounderyMainFrame();
            default:
                throw new CriticalException();
        }

    }

}
