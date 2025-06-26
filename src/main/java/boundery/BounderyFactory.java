package boundery;

import boundery.authenticate_boundery.AuthenticateBounderyMainFrame;
import boundery.error_boundery.ErrorBounderyMainFrame;
import boundery.homepage.HomepageBounderyMainFrame;
import entity.SessionInfo;

import javax.swing.*;

public class BounderyFactory {

    private BounderyFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static JFrame generateBoundery(BounderyEnum boundery){

        switch (boundery){
            case HOMEPAGE:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery HomePage");
                return new HomepageBounderyMainFrame();
            case AUTHENTICATE:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery Authenticate");
                return new AuthenticateBounderyMainFrame();
            case ERROR:
                System.out.println("[SYSTEM] La fabbrica di Boundery sta creando una boundery Error");
                SessionInfo instance = SessionInfo.getSessionInfo();
                return new ErrorBounderyMainFrame(instance.getLastError());
            default:
                return null;
        }

    }

}
