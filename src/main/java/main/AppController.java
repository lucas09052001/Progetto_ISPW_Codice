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
    JPanel nextPanel;
    MainFrame mainFrame;
    Boundaries current;

    public AppController() {
        DashBoardController dashBoardController = new DashBoardControllerFactory().generate(this);
        JPanel dashbordPanel = new DashBoardPanelFactory().generate(dashBoardController);
        this.mainFrame = new MainFrame(dashbordPanel);
    }

    public Boundaries getCurrent() {
        return current;
    }

    public void setCurrent(Boundaries current) {
        this.current = current;
    }

    public JPanel getNextPanel() {
        return nextPanel;
    }

    public void setNextPanel(JPanel nextPanel) {
        this.nextPanel = nextPanel;
    }

    private void start(){
        System.out.println("[APP-CONTROLLER] Starting application");
        new AuthenticateMainFrame().refresh(nextPanel);
    }


    private void go() {
        System.out.println("[APP-CONTROLLER] Going to next UC");
        mainFrame.refresh(nextPanel);
    }

    @Override
    public void errorOccurred(String errorMessage) {
        System.out.println("[APP-CONTROLLER] Error occurred: " + errorMessage);
        new ErrorFrameFactory().generate(errorMessage);
        updateNewBoundary(current);
    }

    @Override
    public void updateNewBoundary(Boundaries nextBoundary) {

        setCurrent(nextBoundary);

        System.out.print("[APP-CONTROLLER] Updating to: ");

        try {
            switch (nextBoundary){
                case AUTHENTICATE -> {
                    System.out.println("Authenticate");
                    AuthenticateController controller = new AuthenticateControllerFactory().generate(this);
                    this.nextPanel = new AuthenticatePanelFactory().generate(controller);
                    start();
                }

                case HOMEPAGE -> {
                    System.out.println("HomePage");
                    HomePageController controller = new HomePageControllerFactory().generate(this);
                    this.nextPanel = new HomePagePanelFactory().generate(controller);
                    go();
                }

                case PROFILE -> {
                    System.out.println("Profile");
                    ProfileController controller = new ProfileControllerFactory().generate(this);
                    this.nextPanel = new ProfileCentralPanelFactory().generate(controller);
                    go();
                }

                case NOTIFICATION -> {
                    System.out.println("Notification");
                    NotificationController controller = new NotificationControllerFactory().generate(this);
                    this.nextPanel = new NotificationPanelFactory().generate(controller);
                    go();
                }

                case LOAN_ITEM -> {
                    System.out.println("Loan Item");
                    LoanItemController controller = new LoanItemControllerFactory().generate(this);
                    this.nextPanel = new LoanItemPanelFactory().generate(controller);
                    go();
                }

                case BORROW_ITEM ->  {
                    System.out.println("Borrow item");
                    BorrowItemController controller = new BorrowItemControllerFactory().generate(this);
                    this.nextPanel = new BorrowItemPanelFactory().generate(controller);
                    go();
                }

                case LOAN_REQUESTS -> {
                    System.out.println("Loan request");
                    LoanRequestController controller = new LoanRequestControllerFactory().generate(this);
                    this.nextPanel = new LoanRequestCentralPanelFactory().generate(controller);
                    go();
                }

                case LOAN_HISTORY, ON_GOING_LOANS -> throw new RuntimeException("Not Yet implmented");

                case DISCOUNTS -> {
                    System.out.println("Discounts");
                    DiscountController controller = new DiscountControllerFactory().generate(this);
                    this.nextPanel = new DiscountCentralPanelFactory().generate(controller);
                    go();
                }

                case null, default -> throw new RuntimeException("[APP-CONTROLLER][CE] Reached unreachable code");

            }
        } catch (FactoryException e) {
            errorOccurred("A critical error occurred in system dynamic");
        }

    }
}
