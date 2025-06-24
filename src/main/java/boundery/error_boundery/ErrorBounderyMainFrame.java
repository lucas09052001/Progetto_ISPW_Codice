package boundery.error_boundery;

import boundery.Color_Repository;

import javax.swing.*;
import java.awt.*;

public class ErrorBounderyMainFrame extends JFrame {

    ErrorBounderyCentralPannel centralPannel;
    JPanel north_pannel = new JPanel();
    JPanel south_pannel = new JPanel();
    JPanel east_pannel = new JPanel();
    JPanel west_pannel = new JPanel();

    public ErrorBounderyMainFrame(String error_Message){

        // Behavior
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //Look and Feel
        setTitle("ERRORE !");
        setLocationRelativeTo(null); // Centra la finestra sullo schermo
        setBackground(Color.decode("#ed4e50"));
        setLayout(new BorderLayout());
        setSize(300, 200);

        //Components

        centralPannel = new ErrorBounderyCentralPannel(error_Message);

        north_pannel.setBackground(Color.decode(Color_Repository.getError_color()));
        south_pannel.setBackground(Color.decode(Color_Repository.getError_color()));
        east_pannel.setBackground(Color.decode(Color_Repository.getError_color()));
        west_pannel.setBackground(Color.decode(Color_Repository.getError_color()));

        add(centralPannel, BorderLayout.CENTER);
        add(north_pannel, BorderLayout.NORTH);
        add(east_pannel, BorderLayout.EAST);
        add(west_pannel, BorderLayout.WEST);
        add(south_pannel, BorderLayout.SOUTH);

        setAlwaysOnTop(true);
        setVisible(true);
    }

}
