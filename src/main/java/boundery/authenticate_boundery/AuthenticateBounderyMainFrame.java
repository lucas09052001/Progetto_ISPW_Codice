package boundery.authenticate_boundery;

import boundery.Color_Repository;
import entity.PersistencyPolicy;

import javax.swing.*;
import java.awt.*;

public class AuthenticateBounderyMainFrame extends JFrame{

    //MainFrame components
    AuthenticateBounderyCentralPannel centralPanel = new AuthenticateBounderyCentralPannel();
    JPanel north_pannel = new JPanel();
    JPanel south_pannel = new JPanel();
    JPanel east_pannel = new JPanel();
    JPanel west_pannel = new JPanel();

    //Info
    String username;
    String password;
    PersistencyPolicy persistency_policy;


    public AuthenticateBounderyMainFrame() {
        super("Unishare");

        // Look and Feel
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        north_pannel.setBackground(Color.decode(Color_Repository.getBackground_color()));
        south_pannel.setBackground(Color.decode(Color_Repository.getBackground_color()));
        east_pannel.setBackground(Color.decode(Color_Repository.getBackground_color()));
        west_pannel.setBackground(Color.decode(Color_Repository.getBackground_color()));


        //Behavior
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Components
        add(centralPanel, BorderLayout.CENTER);
        add(north_pannel, BorderLayout.NORTH);
        add(east_pannel, BorderLayout.EAST);
        add(west_pannel, BorderLayout.WEST);
        add(south_pannel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
