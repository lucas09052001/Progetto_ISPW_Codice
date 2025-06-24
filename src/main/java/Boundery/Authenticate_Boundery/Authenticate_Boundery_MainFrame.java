package Boundery.Authenticate_Boundery;

import Entity.Persistency_Policy;

import javax.swing.*;
import java.awt.*;

public class Authenticate_Boundery_MainFrame extends JFrame{

    //MainFrame components
    Authenticate_Boundery_Central_Pannel centralPanel = new Authenticate_Boundery_Central_Pannel();
    JPanel north_pannel = new JPanel();
    JPanel south_pannel = new JPanel();
    JPanel east_pannel = new JPanel();
    JPanel west_pannel = new JPanel();

    //Info
    String username;
    String password;
    Persistency_Policy persistency_policy;


    public Authenticate_Boundery_MainFrame() {
        super("Unishare");

        // Look and Feel
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        north_pannel.setBackground(Color.decode("#3DDC97"));
        south_pannel.setBackground(Color.decode("#3DDC97"));
        east_pannel.setBackground(Color.decode("#3DDC97"));
        west_pannel.setBackground(Color.decode("#3DDC97"));


        //Behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Components
        add(centralPanel, BorderLayout.CENTER);
        add(north_pannel, BorderLayout.NORTH);
        add(east_pannel, BorderLayout.EAST);
        add(west_pannel, BorderLayout.WEST);
        add(south_pannel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
