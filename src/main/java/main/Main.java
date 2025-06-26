package main;

import boundery.BounderyEnum;
import entity.SessionInfo;

public class Main {
    public static void main(String[] args) {
        SessionInfo sessionInfo = SessionInfo.getSessionInfo();
        //sessionInfo.setNextBoundery(BounderyEnum.AUTHENTICATE);   QUESTA E' QUELLA CORRETTA

        sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE); //PER EVITARE LO SBATTI DI LOGGARE OGNI RUN
                                                                    /*TBR*/
        AppController.start();
    }
}
