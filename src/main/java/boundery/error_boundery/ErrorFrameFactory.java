package boundery.error_boundery;

import entity.SessionInfo;

import javax.swing.*;

public class ErrorFrameFactory {
    SessionInfo sessionInfo;

    public ErrorFrameFactory(){
        this.sessionInfo = SessionInfo.getSessionInfo();
    }

    public JFrame generate(String errorMessage){
        JFrame returnee;

        switch (sessionInfo.getVersion()){
            case V1 -> {
                returnee = new ErrorFrameV1(errorMessage);
            }
            case null, default -> {
                throw new RuntimeException("ErrorFrameFactory reached unreachable code");
            }
        }
        return returnee;
    }
}
