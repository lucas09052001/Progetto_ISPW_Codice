package main;

import boundery.BounderyEnum;
import entity.SessionInfo;

public class Main {
    public static void main(String[] args) {
        SessionInfo sessionInfo = SessionInfo.getSessionInfo();
        sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);
        AppController.start();
    }
}
