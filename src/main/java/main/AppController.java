package main;

import boundery.*;
import boundery.authenticate_boundery.AuthenticateMainFrame;
import boundery.authenticate_boundery.AuthenticatePanelFactory;
import boundery.borrow_item_boundery.BorrowItemPanelFactory;
import boundery.discounts_boundery.DiscountCentralPanelFactory;
import boundery.error_boundery.ErrorFrameFactory;
import boundery.general.MainFrame;
import boundery.homepage.HomePagePanelFactory;
import boundery.loan_item.LoanItemPanelFactory;
import boundery.general.macro_components.DashBoardPanelFactory;
import boundery.loan_request_boundery.LoanRequestCentralPanelFactory;
import boundery.notification_boundery.NotificationPanelFactory;
import boundery.profile_boundery.ProfileCentralPanelFactory;
import controller.discount_controller.DiscountController;
import controller.discount_controller.DiscountControllerFactory;
import controller.loanrequest_controller.LoanRequestController;
import controller.loanrequest_controller.LoanRequestControllerFactory;
import controller.borrowitem_controller.BorrowItemController;
import controller.borrowitem_controller.BorrowItemControllerFactory;
import controller.loanitem_controller.LoanItemController;
import controller.loanitem_controller.LoanItemControllerFactory;
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
import exceptions.FactoryException;


import javax.swing.*;

public class AppController implements Observer{
    JPanel currentPanel;
    Boundaries currentBoundary;
    MainFrame mainFrame;


    public AppController() {
        //App controller creates an instance of logged in mainframe in advance
        DashBoardController dashBoardController = new DashBoardControllerFactory().generate(this);
        JPanel dashbordPanel = new DashBoardPanelFactory().generate(dashBoardController);
        this.mainFrame = new MainFrame(dashbordPanel);
    }


    private void start(){//start() is called if the user is not logged in, as such it calls the specific mainframe to log in
        System.out.println("[APP-CONTROLLER] Starting application");
        new AuthenticateMainFrame().refresh(currentPanel);
    }

    private void go() {
        System.out.println("[APP-CONTROLLER] Going to next UC");
        mainFrame.refresh(currentPanel); //Refreshes mainFrame to update with new centralPanel
    }

    @Override
    public void errorOccurred(String errorMessage) { //This function gets called by controller when app flow has to be diverted because of an error
        System.out.println("[APP-CONTROLLER] Error occurred: " + errorMessage);
        new ErrorFrameFactory().generate(errorMessage);
        updateNewBoundary(currentBoundary);
    }

    @Override
    public void updateNewBoundary(Boundaries nextBoundary) {    //Called by subject to inform the observer (this) to divert app flow

        this.currentBoundary = nextBoundary;

        System.out.print("[APP-CONTROLLER] Updating to: ");

        try {
            switch (nextBoundary){
                case AUTHENTICATE -> {  //Log in UC
                    System.out.println("Authenticate");
                    AuthenticateController controller = new AuthenticateControllerFactory().generate(this);
                    this.currentPanel = new AuthenticatePanelFactory().generate(controller);
                    start();
                }

                case HOMEPAGE -> {  //HomePage
                    System.out.println("HomePage");
                    HomePageController controller = new HomePageControllerFactory().generate(this);
                    this.currentPanel = new HomePagePanelFactory().generate(controller);
                    go();
                }

                case PROFILE -> { //View Profile UC
                    System.out.println("Profile");
                    ProfileController controller = new ProfileControllerFactory().generate(this);
                    this.currentPanel = new ProfileCentralPanelFactory().generate(controller);
                    go();
                }

                case NOTIFICATION -> { //View notifications UC
                    System.out.println("Notification");
                    NotificationController controller = new NotificationControllerFactory().generate(this);
                    this.currentPanel = new NotificationPanelFactory().generate(controller);
                    go();
                }

                case LOAN_ITEM -> { //Loan item UC
                    System.out.println("Loan Item");
                    LoanItemController controller = new LoanItemControllerFactory().generate(this);
                    this.currentPanel = new LoanItemPanelFactory().generate(controller);
                    go();
                }

                case BORROW_ITEM ->  { //Borrow item UC
                    System.out.println("Borrow item");
                    BorrowItemController controller = new BorrowItemControllerFactory().generate(this);
                    this.currentPanel = new BorrowItemPanelFactory().generate(controller);
                    go();
                }

                case LOAN_REQUESTS -> { //Loan request UC
                    System.out.println("Loan request");
                    LoanRequestController controller = new LoanRequestControllerFactory().generate(this);
                    this.currentPanel = new LoanRequestCentralPanelFactory().generate(controller);
                    go();
                }

                case LOAN_HISTORY, ON_GOING_LOANS -> throw new RuntimeException("Not Yet implmented");

                case DISCOUNTS -> { //Discounts UC
                    System.out.println("Discounts");
                    DiscountController controller = new DiscountControllerFactory().generate(this);
                    this.currentPanel = new DiscountCentralPanelFactory().generate(controller);
                    go();
                }

                case null, default -> throw new RuntimeException("[APP-CONTROLLER][CE] Reached unreachable code");

            }
        } catch (FactoryException e) {  //This exception is thrown by factories if they do not know arguments being passed to them
            errorOccurred("A critical error occurred in system dynamic");
        }

    }
}
