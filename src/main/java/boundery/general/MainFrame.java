package boundery.general;

import boundery.authenticate_boundery.AuthenticateCentralPannel;
import repository.ColorRepository;

import javax.swing.*;
import java.awt.*;

public final class MainFrame extends JFrame {

    //MainFrame components
    JPanel dashBoard;
    JPanel centralPanel;
    JPanel northPannel;
    JPanel southPannel;
    JPanel eastPannel = new JPanel();
    JPanel westPannel;


    public MainFrame(JPanel dashboard) {
        //Parent constructor
        super("Unishare");

        //Attributes
        this.dashBoard = dashboard;

        // Static Look and Feel + Behavior
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Instantiating Components
        southPannel = new JPanel();
        eastPannel = new JPanel();
        westPannel = new JPanel();

        //Forming components
        southPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        eastPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        westPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Adding Components to frame
        add(dashboard, BorderLayout.NORTH);
        add(eastPannel, BorderLayout.EAST);
        add(westPannel, BorderLayout.WEST);
        add(southPannel, BorderLayout.SOUTH);

    }

    public void refresh(JPanel newCentralPanel){

        if(!(centralPanel == null)){
            remove(centralPanel);
        }
        centralPanel = newCentralPanel;
        add(centralPanel, BorderLayout.CENTER);

        if(newCentralPanel instanceof AuthenticateCentralPannel){
            dashBoard.setVisible(false);
        }

        revalidate();
        repaint();

        setVisible(true);
    }


}
