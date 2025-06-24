package Boundery.MacroComponents;

import javax.swing.*;
import java.awt.*;

public class DashBoard extends JPanel {

    JButton view_notification_button = new JButton("Notification");
    JButton view_profile_info = new JButton("Profile");
    JLabel logo_Image = new JLabel();

    public DashBoard(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.decode("#3DDC97"));

        //Components
        add(view_notification_button);
        add(Box.createHorizontalGlue());

        logo_Image.setIcon(new ImageIcon("logo.png"));
        add(logo_Image);
        add(Box.createHorizontalGlue());

        add(view_profile_info);

        setVisible(true);
    }

}
