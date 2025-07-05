package boundery.authenticate_boundery;

import utilities.ColorUtility;

import javax.swing.*;
import java.awt.*;

public class AuthenticateMainFrame extends JFrame {
    //MainFrame components
    JPanel centralPanel;
    JPanel northPannel;
    JPanel southPannel;
    JPanel eastPannel = new JPanel();
    JPanel westPannel;


    public AuthenticateMainFrame() {
        super("Unishare");

        // Static Look and Feel + Behavior
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Instantiating Components
        northPannel = new JPanel();
        southPannel = new JPanel();
        eastPannel = new JPanel();
        westPannel = new JPanel();

        //Forming components
        northPannel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        southPannel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        eastPannel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));
        westPannel.setBackground(Color.decode(ColorUtility.getBackgroundColor()));

        //Adding Components to frame
        add(northPannel, BorderLayout.NORTH);
        add(eastPannel, BorderLayout.EAST);
        add(westPannel, BorderLayout.WEST);
        add(southPannel, BorderLayout.SOUTH);

    }

    public void refresh(JPanel newCentralPanel){

        if(centralPanel != null){
            remove(centralPanel);
        }
        centralPanel = newCentralPanel;
        add(centralPanel);

        revalidate();
        repaint();


        setVisible(true);
    }

}
