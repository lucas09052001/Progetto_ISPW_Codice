package boundery.profile_boundery;

import controller.profile_controller.ProfileController;
import entity.user.User;
import repository.ColorRepository;
import controller.profile_controller.ProfileControllerV1;
import entity.user.UserDTO;
import exceptions.CriticalException;
import repository.PathRepository;

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
        setBackground(Color.decode(ColorRepository.getDynamicColor()));

        //Instantiating components
        usernameLabel = new JLabel();
        ratingLabel = new JLabel();
        pointsLabel = new JLabel();

        //Username label
        usernameLabel.setText("Username: " + dto.getUsername());
        usernameLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        usernameLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        //Rating label
        int rating = (int) dto.getRating();
        String pathToRatingImage = PathRepository.getPathToEmptyImage();
        switch (rating){
            case 0 -> pathToRatingImage = PathRepository.getPathToZeroStar();
            case 1 -> pathToRatingImage = PathRepository.getPathToOneStar();
            case 2 -> pathToRatingImage = PathRepository.getPathToTwoStar();
            case 3 -> pathToRatingImage = PathRepository.getPathToThreeStar();
        }
        ratingLabel.setIcon(new ImageIcon(pathToRatingImage));
        ratingLabel.setBackground(Color.decode(ColorRepository.getDynamicColor()));

        //Points label
        String points = Integer.toString(dto.getPoints());
        pointsLabel.setText("Owned points " + points);
        pointsLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        pointsLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
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
