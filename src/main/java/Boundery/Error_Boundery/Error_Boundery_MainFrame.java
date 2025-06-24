package Boundery.Error_Boundery;

import javax.swing.*;
import java.awt.*;

public class Error_Boundery_MainFrame extends JFrame {

    Error_Boundery_Central_Pannel centralPannel;
    JPanel north_pannel = new JPanel();
    JPanel south_pannel = new JPanel();
    JPanel east_pannel = new JPanel();
    JPanel west_pannel = new JPanel();

    public Error_Boundery_MainFrame(String error_Message){

        // Behavior
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiude solo questa finestra

        //Look and Feel
        setTitle("ERRORE !");
        setLocationRelativeTo(null); // Centra la finestra sullo schermo
        setBackground(Color.decode("#ed4e50"));
        setLayout(new BorderLayout());
        setSize(300, 200);

        //Components

        centralPannel = new Error_Boundery_Central_Pannel(error_Message);

        north_pannel.setBackground(Color.decode("#ed4e50"));
        south_pannel.setBackground(Color.decode("#ed4e50"));
        east_pannel.setBackground(Color.decode("#ed4e50"));
        west_pannel.setBackground(Color.decode("#ed4e50"));

        add(centralPannel, BorderLayout.CENTER);
        add(north_pannel, BorderLayout.NORTH);
        add(east_pannel, BorderLayout.EAST);
        add(west_pannel, BorderLayout.WEST);
        add(south_pannel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
