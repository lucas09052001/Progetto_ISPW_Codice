package controller;

import boundery.Boundaries;
import main.AppController;
import entity.SessionInfo;

public class HomePageController {

    SessionInfo sessionInfo;

    public HomePageController(){
        sessionInfo = SessionInfo.getSessionInfo();
    }

    public void start(Boundaries nextBoundery) {
        System.out.println("    [CONTROLLER] HomePage Controller started");
        try {
            if(nextBoundery == Boundaries.ON_GOING_LOANS || nextBoundery == Boundaries.LOAN_HISTORY){
                System.out.println("    [CONTROLLER][NCE] This feature is not implmented yet.");
                throw new IllegalStateException("Not yet implemented");
            }
            SessionInfo.getSessionInfo().setNextBoundery(nextBoundery);

        } catch (IllegalStateException e) {

            sessionInfo.setLastError(e.getMessage());
            sessionInfo.setNextBoundery(Boundaries.ERROR);

            //AppController.errorEncounterd();

            sessionInfo.setNextBoundery(Boundaries.HOMEPAGE);
        } finally {
            //AppController.useCaseCompletion();
        }
    }
}
