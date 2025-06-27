package boundery.profile_boundery;

import boundery.ColorRepository;
import entity.SessionInfo;
import entity.User;
import exceptions.CriticalException;

import javax.swing.*;
import java.awt.*;

public class ProfileTextPanel extends JPanel {

    JLabel usernameLabel = new JLabel();
    JLabel ratingLabel = new JLabel();
    JLabel pointsLabel = new JLabel();
    User user = SessionInfo.getSessionInfo().getUser();
    final String pathToEmptyStar = "./EmptyStar.png";
    final String pathToFullStar = "./FullStar.png";

    public ProfileTextPanel(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));

        //Username label
        usernameLabel.setText("Username: " + user.getUsername());
        usernameLabel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        usernameLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 25));

        //Rating label
        int rating = (int) user.getRating();
        String pathToRatingImage;
        switch (rating){
            case 0 -> pathToRatingImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/NoRating.png";
            case 1 -> pathToRatingImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/1star.png";
            case 2 -> pathToRatingImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/2star.png";
            case 3 -> pathToRatingImage = "/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/profile_boundery/3star.png";
            default -> throw new CriticalException();
        }
        ratingLabel.setIcon(new ImageIcon(pathToRatingImage));
        ratingLabel.setBackground(Color.decode(ColorRepository.getDynamicColor()));

        //Points label
        String points = Integer.toString(user.getPoints());
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
