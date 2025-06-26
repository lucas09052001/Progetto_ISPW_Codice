package controller.useCaseController;

import boundery.BounderyEnum;
import main.AppController;
import entity.SessionInfo;

public class HomePageController {
    public void start(BounderyEnum nextBoundery) {
        SessionInfo.getSessionInfo().setNextBoundery(nextBoundery);
        AppController.useCaseCompletion();
    }
}
