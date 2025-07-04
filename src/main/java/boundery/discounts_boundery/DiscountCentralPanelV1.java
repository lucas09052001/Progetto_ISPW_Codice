package boundery.discounts_boundery;

import repository.ColorRepository;
import controller.DiscountController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DiscountBounderyCentralPanel extends JPanel {
    JLabel topTextLabel;
    DiscountPanel discountPanel1;
    DiscountPanel discountPanel2;
    JButton nextItemsButton;
    DiscountController controller;

    public DiscountBounderyCentralPanel(){

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Instantiating components
        topTextLabel = new JLabel();
        discountPanel1 = new DiscountPanel();
        discountPanel2 = new DiscountPanel();
        nextItemsButton = new JButton();
        controller = new DiscountController();

        //Static set up
        topTextLabel.setText("Available Loans:");
        topTextLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        topTextLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        topTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        topTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        discountPanel1.setController(controller);
        discountPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        discountPanel2.setController(controller);
        discountPanel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        nextItemsButton.setText("Next items");
        nextItemsButton.addActionListener(e -> handleNextItemEvent());
        nextItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Adding components
        add(Box.createVerticalGlue());
        add(topTextLabel);
        add(Box.createVerticalGlue());
        add(discountPanel1);
        add(Box.createVerticalGlue());
        add(discountPanel2);
        add(Box.createVerticalGlue());
        add(nextItemsButton);
        add(Box.createVerticalGlue());

        refresh();

        setVisible(true);
    }

    public void refresh(){

        System.out.println("[BOUNDERY] Populating BorrowObjects Panels");

        discountPanel1.setDiscountDTO(controller.handNextDiscount());
        discountPanel1.setUp();

        discountPanel2.setDiscountDTO(controller.handNextDiscount());
        discountPanel2.setUp();

        System.out.println("[BOUNDERY] Refreshing");
        revalidate();
        repaint();
    }

    private void handleNextItemEvent(){
        refresh();
    }
}
