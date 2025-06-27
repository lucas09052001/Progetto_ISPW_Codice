package controller;

import boundery.BounderyEnum;
import entity.SessionInfo;
import main.AppController;

public class DashBoardController {

    public void start(BounderyEnum nextBoundery) {
        SessionInfo.getSessionInfo().setNextBoundery(nextBoundery);
        AppController.useCaseCompletion();
    }

}
