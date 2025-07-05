package boundery.error_boundery;

import exceptions.FactoryException;
import utilities.SessionInfo;

import javax.swing.*;

public class ErrorFrameFactory {
    SessionInfo sessionInfo;

    public ErrorFrameFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JFrame generate(String errorMessage) throws FactoryException {
        JFrame returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new ErrorFrameV1(errorMessage);
            }
            case null, default -> {
                throw new FactoryException("[ERROR-FACTORY] Reached unreachable code");
            }
        }
        return returnee;
    }
}
