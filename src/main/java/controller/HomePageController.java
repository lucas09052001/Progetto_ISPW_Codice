package controller;

import boundery.BounderyEnum;
import main.AppController;
import entity.SessionInfo;

public class HomePageController {

    SessionInfo sessionInfo;

    public HomePageController(){
        sessionInfo = SessionInfo.getSessionInfo();
    }

    public void start(BounderyEnum nextBoundery) {
        System.out.println("    [CONTROLLER] HomePage Controller started");
        try {
            if(nextBoundery == BounderyEnum.ON_GOING_LOANS || nextBoundery == BounderyEnum.LOAN_HISTORY){
                System.out.println("    [CONTROLLER][NCE] This feature is not implmented yet.");
                throw new IllegalStateException("Not yet implemented");
            }
            SessionInfo.getSessionInfo().setNextBoundery(nextBoundery);

        } catch (IllegalStateException e) {

            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(BounderyEnum.ERROR);

            AppController.errorEncounterd();

            sessionInfo.setNextBoundery(BounderyEnum.HOMEPAGE);
        } finally {
            AppController.useCaseCompletion();
        }
    }
}
