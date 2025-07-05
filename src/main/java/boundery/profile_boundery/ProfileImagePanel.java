package boundery.profile_boundery;

import utilities.ColorUtility;

import javax.swing.*;
import java.awt.*;

public class ProfileImagePanel extends JPanel {

    JLabel profileImage = new JLabel();

    public ProfileImagePanel(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));

        //Components
        profileImage.setIcon(new ImageIcon
                ("/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/NoProfilePicture.png")
        );
        profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileImage.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(profileImage);

        setVisible(true);
    }

}
