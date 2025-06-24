package Boundery.Homepage;

import Boundery.MacroComponents.DashBoard;

import javax.swing.*;
import java.awt.*;

public class Homepage_Boundery_MainFrame extends JFrame {
    //MainFrame components
    DashBoard dashBoard = new DashBoard();
    Homepage_Boundery_Central_Pannel centralPanel = new Homepage_Boundery_Central_Pannel();
    JPanel south_pannel = new JPanel();
    JPanel east_pannel = new JPanel();
    JPanel west_pannel = new JPanel();


    public Homepage_Boundery_MainFrame(){
        super("Unishare");

        // Look and Feel
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        south_pannel.setBackground(Color.decode("#3DDC97"));
        east_pannel.setBackground(Color.decode("#3DDC97"));
        west_pannel.setBackground(Color.decode("#3DDC97"));


        //Behavior
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Components
        add(centralPanel, BorderLayout.CENTER);
        add(dashBoard, BorderLayout.NORTH);
        add(east_pannel, BorderLayout.EAST);
        add(west_pannel, BorderLayout.WEST);
        add(south_pannel, BorderLayout.SOUTH);

        setVisible(true);
    }

}
