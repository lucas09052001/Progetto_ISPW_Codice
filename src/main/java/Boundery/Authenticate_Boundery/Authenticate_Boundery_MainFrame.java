package Boundery.Authenticate_Boundery;

import Boundery.CustomComponents.Custom_Button;
import Boundery.CustomComponents.Custom_TextField;
import Boundery.Generic_Boundery;
import Controller.Authenticate_Controller;
import Entity.Persistency_Policy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Authenticate_Boundery_MainFrame extends JFrame implements Generic_Boundery {

    Authenticate_Controller controller;

    //Compoentni del MainFrame
    Custom_Button loginButton = new Custom_Button((ActionListener) this); //Giusto con il casting ???
    Custom_Button registerButton = new Custom_Button((ActionListener) this); //Giusto con il casting ???
    Custom_TextField username_TextField = new Custom_TextField();
    Custom_TextField password_TextField = new Custom_TextField();

    //Info
    String username;
    String password;
    Persistency_Policy persistency_policy;


    public Authenticate_Boundery_MainFrame() {
        super("Unishare");

        // Look and Feel
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Parametri comportamentali
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Componenti
        loginButton.setText("LogIn");
        this.add(loginButton);
        registerButton.setText("LogIn");
        this.add(registerButton);
        username_TextField.setText("username");
        this.add(username_TextField);
        password_TextField.setText("password");
        this.add(password_TextField);
    }

    @Override
    public void display_and_listen() {

        // Rendi visibile la finestra
        setVisible(true);

        //ON ACTION: Acquire username, password and persistency policy
        controller = new Authenticate_Controller();
        controller.start(username, password,persistency policy);


    }
}
