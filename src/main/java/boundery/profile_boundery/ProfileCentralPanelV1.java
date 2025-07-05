package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProfileCentralPanelV1 extends JPanel {

    ProfileImagePanel profileImagePanel;
    ProfileTextPanel profileTextPanel;
    transient ProfileController controller;

    public ProfileCentralPanelV1(ProfileController controller){
        //Attributes
        this.controller = controller;

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiating components
        profileImagePanel = new ProfileImagePanel();
        profileTextPanel = new ProfileTextPanel(controller);

        //Components
        add(Box.createHorizontalGlue());
        add(profileImagePanel);
        add(Box.createHorizontalGlue());
        add(profileTextPanel);
        add(Box.createHorizontalGlue());

        setVisible(true);
    }

}
