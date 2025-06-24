package boundery.macro_components;

import boundery.ColorRepository;

import javax.swing.*;
import java.awt.*;

public class DashBoard extends JPanel {

    JButton viewNotificationButton = new JButton("Notification");
    JButton viewProfileInfo = new JButton("Profile");
    JLabel logoImage = new JLabel();

    public DashBoard(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Components
        add(viewNotificationButton);
        add(Box.createHorizontalGlue());

        logoImage.setIcon(new ImageIcon("/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/Boundery/MacroComponents/logo.png"));
        add(logoImage);
        add(Box.createHorizontalGlue());

        add(viewProfileInfo);

        setVisible(true);
    }

}
