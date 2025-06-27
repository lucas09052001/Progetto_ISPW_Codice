package boundery.profile_boundery;

import boundery.ColorRepository;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProfileBounderyCentralPannel extends JPanel {

    ProfileImagePanel profileImagePanel = new ProfileImagePanel();
    ProfileTextPanel profileTextPanel = new ProfileTextPanel();

    public ProfileBounderyCentralPannel(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Components
        add(Box.createHorizontalGlue());
        add(profileImagePanel);
        add(Box.createHorizontalGlue());
        add(profileTextPanel);
        add(Box.createHorizontalGlue());

        setVisible(true);
    }

}
