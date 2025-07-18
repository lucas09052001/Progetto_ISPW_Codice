package boundery.homepage;

import boundery.Boundaries;
import controller.homepage_controller.HomePageController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ServicesBandV1 extends JPanel{

    JButton discounts = new JButton("Discounts");
    JLabel servicesText = new JLabel("Services");
    transient HomePageController controller;

    public ServicesBandV1(HomePageController controller){
        //Attributes
        this.controller = controller;

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Label
        servicesText.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        servicesText.setAlignmentX(Component.CENTER_ALIGNMENT);
        servicesText.setFont(new Font("Arial", Font.PLAIN, 20));
        servicesText.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        add(servicesText);
        add(Box.createVerticalGlue());

        //Buttons
        discounts.addActionListener(e -> handleDiscountEvent());
        discounts.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(discounts);
        add(Box.createVerticalGlue());

        setVisible(true);
    }

    public void handleDiscountEvent(){
        System.out.println("[MAINFRAME] Handling DiscountButtonClicked event");
        controller.callObserver(Boundaries.DISCOUNTS);
    }

}
