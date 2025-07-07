package boundery.homepage;

import boundery.Boundaries;
import controller.homepage_controller.HomePageController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class ActivityBandV1 extends JPanel {

    JButton onGoingLoans = new JButton("OnGoing Loans");
    JButton loanHistory = new JButton("Loan History");
    JButton loanRequests = new JButton("Loan Requests");
    JLabel activityText = new JLabel("Activity");
    transient HomePageController controller;

    public ActivityBandV1(HomePageController controller){

        //Attributes
        this.controller = controller;

        //Look and feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Labels
        activityText.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        activityText.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityText.setFont(new Font("Arial", Font.PLAIN, 20));
        activityText.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        add(activityText);
        add(Box.createVerticalGlue());

        //Buttons
        onGoingLoans.addActionListener(e -> handleOnGoingLoansEvent());
        onGoingLoans.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(onGoingLoans);
        add(Box.createVerticalGlue());

        loanHistory.addActionListener(e -> handleLoanHistoryEvent());
        loanHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loanHistory);
        add(Box.createVerticalGlue());

        loanRequests.addActionListener(e -> handleLoanRequestsEvent());
        loanRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loanRequests);
        add(Box.createVerticalGlue());

        setVisible(true);
    }

    public void handleOnGoingLoansEvent(){
        System.out.println("[SYSTEM] Handling 'OnGoingLoans' event");
        controller.callObserver(Boundaries.ON_GOING_LOANS);
    }

    public void handleLoanHistoryEvent(){
        System.out.println("[SYSTEM] Handling 'LoanHistory' event");
        controller.callObserver(Boundaries.LOAN_HISTORY);
    }

    public void handleLoanRequestsEvent(){
        System.out.println("[BOUNDARY] Handling 'LoanRequests' event");
        controller.callObserver(Boundaries.LOAN_REQUESTS);
    }

}
