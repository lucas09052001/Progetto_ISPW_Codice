package boundery.error_boundery;

import boundery.ColorRepository;
import entity.SessionInfo;

import javax.swing.*;
import java.awt.*;

public class ErrorBounderyMainFrame extends JFrame {

    ErrorBounderyCentralPannel centralPannel;
    JPanel northPannel = new JPanel();
    JPanel southPannel = new JPanel();
    JPanel eastPannel = new JPanel();
    JPanel westPannel = new JPanel();
    String errorMessage = SessionInfo.getSessionInfo().getLastError();

    public ErrorBounderyMainFrame(){

        // Behavior
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //Look and Feel
        setTitle("ERRORE !");
        setLocationRelativeTo(null); // Centra la finestra sullo schermo
        setBackground(Color.decode("#ed4e50"));
        setLayout(new BorderLayout());
        setSize(300, 200);

        //Components

        centralPannel = new ErrorBounderyCentralPannel(errorMessage);

        northPannel.setBackground(Color.decode(ColorRepository.getErrorColor()));
        southPannel.setBackground(Color.decode(ColorRepository.getErrorColor()));
        eastPannel.setBackground(Color.decode(ColorRepository.getErrorColor()));
        westPannel.setBackground(Color.decode(ColorRepository.getErrorColor()));

        add(centralPannel, BorderLayout.CENTER);
        add(northPannel, BorderLayout.NORTH);
        add(eastPannel, BorderLayout.EAST);
        add(westPannel, BorderLayout.WEST);
        add(southPannel, BorderLayout.SOUTH);

        setAlwaysOnTop(true);
        setVisible(true);
    }

}
