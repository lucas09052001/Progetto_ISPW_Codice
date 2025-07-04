package boundery.borrow_item_boundery;

import controller.borrowitem_controller.BorrowItemController;
import controller.borrowitem_controller.BorrowItemControllerFactory;
import repository.ColorRepository;
import controller.borrowitem_controller.BorrowItemControllerV1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BorrowItemCentralPannelV1 extends JPanel {
    JLabel topTextLabel;
    BorrowObjectPanel borrowObjectPanel_1;
    BorrowObjectPanel borrowObjectPanel_2;
    JButton nextItemsButton;
    BorrowItemController controller;

    public BorrowItemCentralPannelV1(BorrowItemController controller){
        //Attributes
        this.controller = controller;

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        borrowObjectPanel_1 = new BorrowObjectPanel();
        borrowObjectPanel_2 = new BorrowObjectPanel();
        nextItemsButton = new JButton();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        borrowObjectPanel_1.setController(controller);
        borrowObjectPanel_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        borrowObjectPanel_2.setController(controller);
        borrowObjectPanel_2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(borrowObjectPanel_1);
        add(Box.createVerticalGlue());
        add(borrowObjectPanel_2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        borrowObjectPanel_1.setLoanPostDTO(controller.handNext());
        borrowObjectPanel_1.setUp();

        borrowObjectPanel_2.setLoanPostDTO(controller.handNext());
        borrowObjectPanel_2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }

}
