package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import controller.profile_controller.ProfileControllerFactory;
import repository.ColorRepository;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProfileCentralPanelV1 extends JPanel {

    ProfileImagePanel profileImagePanel;
    ProfileTextPanel profileTextPanel;
    ProfileController controller;

    public ProfileCentralPanelV1(ProfileController controller){
        //Attributes
        this.controller = controller;

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

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
