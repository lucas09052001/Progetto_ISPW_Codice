package boundery.macro_components;

import boundery.BounderyEnum;
import boundery.ColorRepository;
import controller.useCaseController.HomePageController;
import entity.SessionInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DashBoard extends JPanel {

    JButton viewNotificationButton = new JButton("Notification");
    JButton viewProfileInfo = new JButton("Profile");
    JButton homepage = new JButton("HomePage");
    JLabel logoImage = new JLabel();

    public DashBoard(){

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Components
        homepage.addActionListener(e -> handleHomepageEvent());
        add(homepage);
        add(Box.createHorizontalGlue());

        logoImage.setIcon(new ImageIcon
                ("/home/lucas/Documents/Università/Corrente/ISPW/Progetto_Codice/progetto_ISPW/src/main/java/boundery/macro_components/img.png")
        );
        add(logoImage);
        add(Box.createHorizontalGlue());

        viewNotificationButton.addActionListener(e -> handleViewNotificationEvent());
        add(viewNotificationButton);
        //add(Box.createHorizontalGlue());

        viewProfileInfo.addActionListener(e -> handleViewProfileEvent());
        add(viewProfileInfo);

        setVisible(true);
    }

    public void handleHomepageEvent(){
        System.out.println("[SYSTEM] Handling 'HomepageButton-Clicked' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageController().start(BounderyEnum.HOMEPAGE);
    }

    public void handleViewNotificationEvent(){
        System.out.println("[SYSTEM] Handling 'ViewNotificationButton-Clicked' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageController().start(BounderyEnum.NOTIFICATION);
    }

    public void handleViewProfileEvent(){
        System.out.println("[SYSTEM] Handling 'ViewProfileButton-Clicked' event");
        SwingUtilities.getWindowAncestor(this).dispose();
        new HomePageController().start(BounderyEnum.PROFILE);
    }
}
