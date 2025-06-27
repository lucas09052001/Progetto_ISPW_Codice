package boundery.homepage;

import boundery.BounderyEnum;
import boundery.ColorRepository;
import controller.HomePageController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoansBand extends JPanel {
    final JButton loanItem = new JButton("Loan Item");
    final JButton borrowItem = new JButton("Borrow Item");
    final JLabel loansText = new JLabel("Loans");

    public LoansBand(){

        //Look and feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Labels
        loansText.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        loansText.setAlignmentX(Component.CENTER_ALIGNMENT);
        loansText.setFont(new Font("Arial", Font.PLAIN, 20));
        loansText.setForeground(Color.decode(ColorRepository.getForegroundColor()));
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
        new HomePageController().start(BounderyEnum.LOAN_ITEM);
    }

    public void handleBorrowItemEvent(){
        System.out.println("[SYSTEM] Handling 'BorrowItem' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageController().start(BounderyEnum.BORROW_ITEM);
    }

}
