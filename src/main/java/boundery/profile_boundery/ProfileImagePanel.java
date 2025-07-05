package boundery.profile_boundery;

import utilities.ColorUtility;
import utilities.PathUtility;

import javax.swing.*;
import java.awt.*;

public class ProfileImagePanel extends JPanel {

    JLabel profileImage = new JLabel();

    public ProfileImagePanel(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));

        ImageIcon imageIcon = new ImageIcon(PathUtility.getPathToEmptyProfilePicture());


        //Components
        profileImage.setIcon(imageIcon);
        profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileImage.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(profileImage);

        setVisible(true);
    }

}
