package boundery.notification_boundery;

import boundery.ColorRepository;
import controller.NotificationController;
import entity.notification.CustomNotificationDTO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationBounderyCentralPanel extends JPanel {

    transient NotificationController controller = new NotificationController();
    transient List<CustomNotificationDTO> notificationList = new ArrayList<>();
    ArrayList<JLabel> labelList = new ArrayList<>();
    JButton nextButton = new JButton();

    public NotificationBounderyCentralPanel(){

        //Basic Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

        //Instantiate Labels
        labelList.add(new JLabel());
        labelList.add(new JLabel());
        labelList.add(new JLabel());

        //Controller interaction
        System.out.println("[BOUNDERY] NotificationBoundery asking controller to fetch notifications... ");
        controller.fetchAllNotification();

        nextNotifications();

        add(Box.createVerticalGlue());
        for(JLabel l : labelList){
            add(l);
            add(Box.createVerticalGlue());
        }

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
        //Refresh Centra Panel
        revalidate();
        repaint();
        System.out.println("[BOUNDERY] Done.");
    }

    private void buildNextNotification(JLabel l){

        CustomNotificationDTO notificationDTO;

        System.out.println("[BOUNDERY] Asking controller to handout next notification.");
        notificationDTO = controller.handNextNotification();
        System.out.println("[BOUNDERY] Done.");

        String text;
        if(!(notificationDTO == null)){
            text = "From: " + notificationDTO.getSenderUsername() + ". Message: " + notificationDTO.getMessage();
        }else{
            text = "No new notification";
        }

        l.setText(text);
        l.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        l.setForeground(Color.decode(ColorRepository.getForegroundColor()));
        l.setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));
        l.setFont(new Font("Arial", Font.PLAIN, 20));
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

}
