package Main;

import boundery.BounderyEnum;
import controller.Dispatcher;
import entity.SessionInfo;

public class Main {
    public static void main(String[] args) {
        SessionInfo instance = SessionInfo.getSessionInfo();
        instance.setBounderyEnum(BounderyEnum.AUTHENTICATE);
        Dispatcher.start();
    }
}
