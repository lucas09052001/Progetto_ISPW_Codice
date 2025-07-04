package controller.dashboard_controller;

import boundery.Boundaries;
import entity.SessionInfo;
import main.Observer;

public class DashBoardControllerV1 implements DashBoardController{

    Observer observer;

    public DashBoardControllerV1(Observer observer){
        this.observer = observer;
    }

    @Override
    public void callObserver(Boundaries boundary) {
        observer.updateNewBoundery(boundary);
    }
}
