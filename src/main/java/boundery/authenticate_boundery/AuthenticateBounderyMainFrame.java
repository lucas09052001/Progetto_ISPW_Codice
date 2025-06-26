package boundery.authenticate_boundery;

import boundery.ColorRepository;

import javax.swing.*;
import java.awt.*;

public class AuthenticateBounderyMainFrame extends JFrame{

    //MainFrame components
    AuthenticateBounderyCentralPannel centralPanel = new AuthenticateBounderyCentralPannel();
    JPanel northPannel = new JPanel();
    JPanel southPannel = new JPanel();
    JPanel eastPannel = new JPanel();
    JPanel westPannel = new JPanel();


    public AuthenticateBounderyMainFrame() {
        super("Unishare");

        // Look and Feel
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        northPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        southPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        eastPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        westPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));


        //Behavior
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Components
        add(centralPanel, BorderLayout.CENTER);
        add(northPannel, BorderLayout.NORTH);
        add(eastPannel, BorderLayout.EAST);
        add(westPannel, BorderLayout.WEST);
        add(southPannel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
