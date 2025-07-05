package boundery.homepage;

import boundery.Boundaries;
import controller.homepage_controller.HomePageController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoansBandV1 extends JPanel {
    final JButton loanItem = new JButton("Loan Item");
    final JButton borrowItem = new JButton("Borrow Item");
    final JLabel loansText = new JLabel("Loans");
    final HomePageController controller;

    public LoansBandV1(HomePageController controller){
        //Attributes
        this.controller = controller;

        //Look and feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Labels
        loansText.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        loansText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loansText.setFont(new Font("Arial", Font.PLAIN, 20));
        loansText.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        add(loansText);
        add(Box.createVerticalGlue());

        //Buttons
        loanItem.addActionListener(e -> handleLoanItemEvent());
        loanItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loanItem);
        add(Box.createVerticalGlue());

        borrowItem.addActionListener(e -> handleBorrowItemEvent());
        borrowItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(borrowItem);
        add(Box.createVerticalGlue());

        setVisible(true);
    }

    public void handleLoanItemEvent(){
        System.out.println("[SYSTEM] Handling 'LoanItem' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.callObserver(Boundaries.LOAN_ITEM);
    }

    public void handleBorrowItemEvent(){
        System.out.println("[SYSTEM] Handling 'BorrowItem' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.callObserver(Boundaries.BORROW_ITEM);
    }

}
