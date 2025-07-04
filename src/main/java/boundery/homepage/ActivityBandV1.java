package boundery.homepage;

import boundery.Boundaries;
import repository.ColorRepository;
import controller.homepage_controller.HomePageControllerV1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ActivityBand extends JPanel {

    final JButton onGoingLoans = new JButton("OnGoing Loans");
    final JButton loanHistory = new JButton("Loan History");
    final JButton loanRequests = new JButton("Loan Requests");
    final JLabel activityText = new JLabel("Activity");

    public ActivityBand(){

        //Look and feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Labels
        activityText.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        activityText.setAlignmentX(Component.CENTER_ALIGNMENT);
        activityText.setFont(new Font("Arial", Font.PLAIN, 20));
        activityText.setForeground(Color.decode(ColorRepository.getForegroundColor()));
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
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageControllerV1().start(Boundaries.ON_GOING_LOANS);
    }

    public void handleLoanHistoryEvent(){
        System.out.println("[SYSTEM] Handling 'LoanHistory' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageControllerV1().start(Boundaries.LOAN_HISTORY);
    }

    public void handleLoanRequestsEvent(){
        System.out.println("[SYSTEM] Handling 'LoanRequests' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageControllerV1().start(Boundaries.LOAN_REQUESTS);
    }

}
