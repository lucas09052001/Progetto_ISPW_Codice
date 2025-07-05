package boundery.loan_request_boundery;

import controller.loanrequest_controller.LoanRequestController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoanRequestCentralPanelV1 extends JPanel {

    JLabel topTextLabel;
    LoanRequestPanel loanRequestPanel1;
    LoanRequestPanel loanRequestPanel2;
    JButton nextItemsButton;
    transient LoanRequestController controller;

    public LoanRequestCentralPanelV1(LoanRequestController controller){
        //Attributes
        this.controller = controller;

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        loanRequestPanel1 = new LoanRequestPanel();
        loanRequestPanel2 = new LoanRequestPanel();
        nextItemsButton = new JButton();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loanRequestPanel1.setController(controller);
        loanRequestPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        loanRequestPanel2.setController(controller);
        loanRequestPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(loanRequestPanel1);
        add(Box.createVerticalGlue());
        add(loanRequestPanel2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        loanRequestPanel1.setDto(controller.handNext());
        loanRequestPanel1.setUp();

        loanRequestPanel2.setDto(controller.handNext());
        loanRequestPanel2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }

}
