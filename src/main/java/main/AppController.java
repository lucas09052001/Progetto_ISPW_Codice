package main;

import boundery.*;
import boundery.authenticate_boundery.AuthenticateMainFrame;
import boundery.authenticate_boundery.AuthenticatePanelFactory;
import boundery.error_boundery.ErrorFrameFactory;
import boundery.homepage.HomePagePanelFactory;
import boundery.macro_components.DashBoardPanelFactory;
import boundery.notification_boundery.NotificationPanelFactory;
import boundery.profile_boundery.ProfileCentralPanelFactory;
import controller.notification_controller.NotificationController;
import controller.notification_controller.NotificationControllerFactory;
import controller.profile_controller.ProfileController;
import controller.profile_controller.ProfileControllerFactory;
import controller.authenticate_controller.AuthenticateController;
import controller.authenticate_controller.AuthenticateControllerFactory;
import controller.dashboard_controller.DashBoardController;
import controller.dashboard_controller.DashBoardControllerFactory;
import controller.homepage_controller.HomePageController;
import controller.homepage_controller.HomePageControllerFactory;


import javax.swing.*;

public class AppController implements Observer{
    JPanel nextBoundary;
    MainFrame mainFrame;
    Boundaries current;

    public AppController() {
        DashBoardController dashBoardController = new DashBoardControllerFactory().generate(this);
        JPanel dashbordPanel = new DashBoardPanelFactory().generate(dashBoardController);
        this.mainFrame = new MainFrame(dashbordPanel);
    }

    private void start(){
        System.out.println("[APP-CONTROLLER] Starting application");
        new AuthenticateMainFrame().refresh(nextBoundary);
    }

    private void go() {
        System.out.println("[APP-CONTROLLER] Going to next UC");
        mainFrame.refresh(nextBoundary);
    }

    private void error(){
        System.out.println("[APP-CONTROLLER] Error encountered");
        updateNewBoundery(current);
    }

    @Override
    public void errorOccurred(String errorMessage) {
        System.out.println("[APP-CONTROLLER] Error occurred: " + errorMessage);
        new ErrorFrameFactory().generate(errorMessage);
        updateNewBoundery(current);
    }

    @Override
    public void updateNewBoundery(Boundaries nextBoundary) {

        current = nextBoundary;

        System.out.print("[APP-CONTROLLER] Updating to: ");

        switch (nextBoundary){
            case AUTHENTICATE -> {
                System.out.println("Authenticate");
                AuthenticateController controller = new AuthenticateControllerFactory().generate(this);
                this.nextBoundary = new AuthenticatePanelFactory().generate(controller);
                start();
            }

            case HOMEPAGE -> {
                System.out.println("HomePage");
                HomePageController controller = new HomePageControllerFactory().generate(this);
                this.nextBoundary = new HomePagePanelFactory().generate(controller);
                go();
            }

            case PROFILE -> {
                System.out.println("Profile");
                ProfileController controller = new ProfileControllerFactory().generate(this);
                this.nextBoundary = new ProfileCentralPanelFactory().generate(controller);
                go();
            }

            case NOTIFICATION -> {
                System.out.println("Notification");
                NotificationController controller = new NotificationControllerFactory().generate(this);
                this.nextBoundary = new NotificationPanelFactory().generate(controller);
                go();
            }

            case ERROR -> {

                error();
            }

            case null, default -> {
                System.out.println("[APP-CONTROLLER][CE] Reached unreachable code");
                throw new RuntimeException("[APP-CONTROLLER][CE] Reached unreachable code");
            }

        }

    }
}
