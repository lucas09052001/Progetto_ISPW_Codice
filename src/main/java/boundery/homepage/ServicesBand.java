package boundery.homepage;

import boundery.BounderyEnum;
import boundery.ColorRepository;
import controller.useCaseController.HomePageController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ServicesBand extends JPanel{

    final JButton discounts = new JButton("Discounts");
    final JLabel servicesText = new JLabel("Services");

    public ServicesBand(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Label
        servicesText.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        servicesText.setAlignmentX(Component.CENTER_ALIGNMENT);
        servicesText.setFont(new Font("Arial", Font.PLAIN, 20));
        servicesText.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        add(servicesText);
        add(Box.createVerticalGlue());

        //Buttons
        discounts.addActionListener(e -> handleDiscountsEvent());
        discounts.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(discounts);
        add(Box.createVerticalGlue());

        setVisible(true);
    }

    public void handleDiscountsEvent(){
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageController().start(BounderyEnum.DISCOUNTS);
    }
}
