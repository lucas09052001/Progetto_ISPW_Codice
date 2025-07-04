package boundery.loan_request_boundery;

import repository.ColorRepository;
import controller.loanrequest_controller.LoanRequestControllerV1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoanRequestBounderyPanel extends JPanel {

    JLabel topTextLabel;
    LoanRequestPanel loanRequestPanel_1;
    LoanRequestPanel loanRequestPanel_2;
    JButton nextItemsButton;
    LoanRequestControllerV1 controller;

    public LoanRequestBounderyPanel(){

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        loanRequestPanel_1 = new LoanRequestPanel();
        loanRequestPanel_2 = new LoanRequestPanel();
        nextItemsButton = new JButton();
        controller = new LoanRequestControllerV1();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loanRequestPanel_1.setController(controller);
        loanRequestPanel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        loanRequestPanel_2.setController(controller);
        loanRequestPanel_2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(loanRequestPanel_1);
        add(Box.createVerticalGlue());
        add(loanRequestPanel_2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        loanRequestPanel_1.setDto(controller.handNextLoanRequest());
        loanRequestPanel_1.setUp();

        loanRequestPanel_2.setDto(controller.handNextLoanRequest());
        loanRequestPanel_2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }

}
