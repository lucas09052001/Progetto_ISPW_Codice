package boundery.loan_request_boundery;

import controller.loanrequest_controller.LoanRequestController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoanRequestCentralPanelV1 extends JPanel {

    JLabel topTextLabel;
    LoanRequestPanelV1 loanRequestPanel_V1_1;
    LoanRequestPanelV1 loanRequestPanel_V1_2;
    JButton nextItemsButton;
    LoanRequestController controller;

    public LoanRequestCentralPanelV1(LoanRequestController controller){
        //Attributes
        this.controller = controller;

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        loanRequestPanel_V1_1 = new LoanRequestPanelV1();
        loanRequestPanel_V1_2 = new LoanRequestPanelV1();
        nextItemsButton = new JButton();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loanRequestPanel_V1_1.setController(controller);
        loanRequestPanel_V1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        loanRequestPanel_V1_2.setController(controller);
        loanRequestPanel_V1_2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(loanRequestPanel_V1_1);
        add(Box.createVerticalGlue());
        add(loanRequestPanel_V1_2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        loanRequestPanel_V1_1.setDto(controller.handNext());
        loanRequestPanel_V1_1.setUp();

        loanRequestPanel_V1_2.setDto(controller.handNext());
        loanRequestPanel_V1_2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }

}
