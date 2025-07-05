package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import utilities.ColorUtility;
import entity.user.UserDTO;
import utilities.PathUtility;

import javax.swing.*;
import java.awt.*;

public class ProfileTextPanel extends JPanel {

    JLabel usernameLabel;
    JLabel ratingLabel;
    JLabel pointsLabel;

    transient ProfileController controller;

    public ProfileTextPanel(ProfileController controller){
        //Attributes
        this.controller = controller;

        //Controller interaction
        UserDTO dto  = controller.getUserInfo();
        if(dto == null){
            SwingUtilities.getWindowAncestor(this).dispose();
        }

        //Basic Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));

        //Instantiating components
        usernameLabel = new JLabel();
        ratingLabel = new JLabel();
        pointsLabel = new JLabel();

        //Username label
        usernameLabel.setText("Username: " + dto.getUsername());
        usernameLabel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        usernameLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        //Rating label
        int rating = (int) dto.getRating();
        String pathToRatingImage = PathUtility.getPathToEmptyImage();
        switch (rating){
            case 0 -> pathToRatingImage = PathUtility.getPathToZeroStar();
            case 1 -> pathToRatingImage = PathUtility.getPathToOneStar();
            case 2 -> pathToRatingImage = PathUtility.getPathToTwoStar();
            case 3 -> pathToRatingImage = PathUtility.getPathToThreeStar();
        }
        ratingLabel.setIcon(new ImageIcon(pathToRatingImage));
        ratingLabel.setBackground(Color.decode(ColorUtility.getDynamicColor()));

        //Points label
        String points = Integer.toString(dto.getPoints());
        pointsLabel.setText("Owned points " + points);
        pointsLabel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        pointsLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        //Components
        add(Box.createVerticalGlue());
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(usernameLabel);
        add(Box.createVerticalGlue());
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(ratingLabel);
        add(Box.createVerticalGlue());
        pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(pointsLabel);
        add(Box.createVerticalGlue());

        setVisible(true);
    }

}
