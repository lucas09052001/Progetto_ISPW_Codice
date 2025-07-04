package boundery.discounts_boundery;

import controller.discount_controller.DiscountController;
import repository.ColorRepository;
import controller.discount_controller.DiscountControllerV1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DiscountCentralPanelV1 extends JPanel {
    JLabel topTextLabel;
    DiscountPanelV1 discountPanelV11;
    DiscountPanelV1 discountPanelV12;
    JButton nextItemsButton;
    DiscountController controller;

    public DiscountCentralPanelV1(DiscountController controller){
        //Attributes
        this.controller = controller;

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        discountPanelV11 = new DiscountPanelV1();
        discountPanelV12 = new DiscountPanelV1();
        nextItemsButton = new JButton();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        discountPanelV11.setController(controller);
        discountPanelV11.setAlignmentX(Component.CENTER_ALIGNMENT);
        discountPanelV12.setController(controller);
        discountPanelV12.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(discountPanelV11);
        add(Box.createVerticalGlue());
        add(discountPanelV12);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        discountPanelV11.setDiscountDTO(controller.handNext());
        discountPanelV11.setUp();

        discountPanelV12.setDiscountDTO(controller.handNext());
        discountPanelV12.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }
}
