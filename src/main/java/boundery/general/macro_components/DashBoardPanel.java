package boundery.general.macro_components;

import boundery.Boundaries;
import utilities.ColorUtility;
import controller.dashboard_controller.DashBoardController;
import utilities.PathUtility;

import javax.swing.*;
import java.awt.*;

public class DashBoardPanel extends JPanel {

    private JButton viewNotificationButton;
    private JButton viewProfileInfo;
    private JButton homepage;
    private JLabel logoImage;
    private transient DashBoardController controller;

    public DashBoardPanel(DashBoardController controller){

        System.out.println("    [DASHBOARD] Starting");

        //Attributes
        this.controller = controller;

        //Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.decode(ColorUtility.getBackgroundColor()));

        //Instantiating Components
        viewNotificationButton = new JButton("Notification");
        viewProfileInfo = new JButton("Profile");
        homepage = new JButton("HomePage");
        logoImage = new JLabel();

        //Homepage Button
        homepage.addActionListener(e -> handleHomepageEvent());
        add(homepage);
        add(Box.createHorizontalGlue());

        //Image label
        ImageIcon icon = new ImageIcon(PathUtility.getPathToLogoImage());
        logoImage.setIcon(icon);
        add(logoImage);
        add(Box.createHorizontalGlue());

        //Notification button
        viewNotificationButton.addActionListener(e -> handleViewNotificationEvent());
        add(viewNotificationButton);

        //Profile button
        viewProfileInfo.addActionListener(e -> handleViewProfileEvent());
        add(viewProfileInfo);

        setVisible(true);
    }

    public void handleHomepageEvent(){
        System.out.println("    [DASHBOARD] Handling 'HomepageButton-Clicked' event");
        controller.callObserver(Boundaries.HOMEPAGE);
    }

    public void handleViewNotificationEvent(){
        System.out.println("    [DASHBOARD] Handling 'ViewNotificationButton-Clicked' event");
        controller.callObserver(Boundaries.NOTIFICATION);
    }

    public void handleViewProfileEvent(){
        System.out.println("    [DASHBOARD] Handling 'ViewProfileButton-Clicked' event");
        controller.callObserver(Boundaries.PROFILE);
    }
}
