package boundery.borrow_item_boundery;

import controller.borrowitem_controller.BorrowItemController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BorrowItemPanelV1 extends JPanel {
    JLabel topTextLabel;
    BorrowObjectPanel borrowObjectPanel1;
    BorrowObjectPanel borrowObjectPanel2;
    JButton nextItemsButton;
    transient BorrowItemController controller;

    public BorrowItemPanelV1(BorrowItemController controller){
        //Attributes
        this.controller = controller;

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        borrowObjectPanel1 = new BorrowObjectPanel();
        borrowObjectPanel2 = new BorrowObjectPanel();
        nextItemsButton = new JButton();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        borrowObjectPanel1.setController(controller);
        borrowObjectPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        borrowObjectPanel2.setController(controller);
        borrowObjectPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(borrowObjectPanel1);
        add(Box.createVerticalGlue());
        add(borrowObjectPanel2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        borrowObjectPanel1.setLoanPostDTO(controller.handNext());
        borrowObjectPanel1.setUp();

        borrowObjectPanel2.setLoanPostDTO(controller.handNext());
        borrowObjectPanel2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }

}
