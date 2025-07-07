package boundery.notification_boundery;

import controller.notification_controller.NotificationController;
import utilities.ColorUtility;
import entity.notification.NotificationDTO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationCentralPanelV1 extends JPanel {

    transient NotificationController controller;
    transient List<NotificationDTO> notificationList;
    ArrayList<JLabel> labelList;
    JButton nextButton;

    public NotificationCentralPanelV1(NotificationController controller){
        //Attributes
        this.controller = controller;
        notificationList = new ArrayList<>();
        labelList = new ArrayList<>();

        //Basic Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiate Components
        nextButton = new JButton();
        labelList.add(new JLabel());
        labelList.add(new JLabel());
        labelList.add(new JLabel());

        //Controller interaction
        System.out.println("[BOUNDERY] NotificationBoundery asking controller to fetch notifications... ");

        nextNotifications();

        add(Box.createVerticalGlue());
        for(JLabel l : labelList){
            add(l);
            add(Box.createVerticalGlue());
        }

        nextButton.setText("Next Notifications");
        nextButton.addActionListener(e -> nextNotifications());
        add(nextButton);
        add(Box.createVerticalGlue());

    }


    private void nextNotifications(){
        System.out.println("[BOUNDERY] NextNotification function triggered");

        System.out.println("[BOUNDERY] Building new notifications");
        for(int i = 0; i < 3; i++){
            buildNextNotification(labelList.get(i));
        }

        System.out.println("[BOUNDERY] Refreshing pannel... ");
        //Refresh Central Panel
        revalidate();
        repaint();
        System.out.println("[BOUNDERY] Done.");
    }

    private void buildNextNotification(JLabel l){
        System.out.println("[MAINFRAME] Building new Notifications.");

        NotificationDTO notificationDTO;
        notificationDTO = controller.handNext();

        String text;
        if(notificationDTO != null){
            text = "From: " + notificationDTO.getSenderUsername() + ". Message: " + notificationDTO.getMessage();
        }else{
            text = "No new notification";
        }

        l.setText(text);
        l.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        l.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        l.setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));
        l.setFont(new Font("Arial", Font.PLAIN, 20));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
