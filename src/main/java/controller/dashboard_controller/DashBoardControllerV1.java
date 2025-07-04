package controller;

import boundery.Boundaries;
import entity.SessionInfo;
import main.AppController;

public class DashBoardController {

    public void start(Boundaries nextBoundery) {
        SessionInfo.getSessionInfo().setNextBoundery(nextBoundery);
        //AppController.useCaseCompletion();
    }

}
