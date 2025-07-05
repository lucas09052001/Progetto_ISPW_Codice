package controller.homepage_controller;

import boundery.Boundaries;
import main.Observer;

public class HomePageControllerV1 implements HomePageController {

    Observer observer;

    public HomePageControllerV1(Observer observer){
        this.observer = observer;
    }

    @Override
    public void callObserver(Boundaries boundary) {
        System.out.println("    [CONTROLLER] HomePage Controller calling Observer");
        observer.updateNewBoundery(boundary);
    }
}
