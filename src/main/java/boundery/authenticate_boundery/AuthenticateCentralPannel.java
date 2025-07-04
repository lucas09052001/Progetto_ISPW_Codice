package boundery.authenticate_boundery;

import repository.ColorRepository;
import controller.authenticate_controller.AuthenticateController;
import entity.PersistencyPolicy;

import javax.swing.*;
import java.awt.*;

public class AuthenticateCentralPannel extends JPanel {

    private final transient AuthenticateController controller;
    PersistencyPolicy persistencyPolicy;

    JButton loginButton;
    JButton registerButton;
    JTextField usernameTextField;
    JTextField passwordTextField;
    JLabel textPersistency;
    JRadioButton persistencyPolicyDB;
    JRadioButton persistencyPolicyFile;
    JRadioButton persistencyPolicyNoPersistency;
    ButtonGroup gruppoBottoni;


    public AuthenticateCentralPannel(AuthenticateController controller) {

        //Attributes
        this.controller = controller;
        this.persistencyPolicy = PersistencyPolicy.NULL;

        // Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#3DDC97"));

        //Instantiating components
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        usernameTextField = new JTextField("bob");
        passwordTextField = new JTextField("bob");
        textPersistency = new JLabel("Choose the persistency policy: ");
        persistencyPolicyDB = new JRadioButton("DB");
        persistencyPolicyFile = new JRadioButton("File");
        persistencyPolicyNoPersistency = new JRadioButton("No Persistency");
        gruppoBottoni = new ButtonGroup();

        //TextFields
        Dimension fieldSize = new Dimension(400, 50);
        usernameTextField.setMaximumSize(fieldSize);
        passwordTextField.setMaximumSize(fieldSize);

        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(30)); // spaziatura
        add(usernameTextField);
        add(Box.createVerticalStrut(30));
        add(passwordTextField);

        //RadioButtons for persistency policy
        textPersistency.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPersistency.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        add(textPersistency);

        gruppoBottoni.add(persistencyPolicyDB);
        gruppoBottoni.add(persistencyPolicyFile);
        gruppoBottoni.add(persistencyPolicyNoPersistency);

        persistencyPolicyDB.setAlignmentX(Component.CENTER_ALIGNMENT);
        persistencyPolicyFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        persistencyPolicyNoPersistency.setAlignmentX(Component.CENTER_ALIGNMENT);

        persistencyPolicyDB.addActionListener(e -> persistencyPolicy = PersistencyPolicy.DB);
        persistencyPolicyFile.addActionListener(e -> persistencyPolicy = PersistencyPolicy.FILE);
        persistencyPolicyNoPersistency.addActionListener(e -> persistencyPolicy = PersistencyPolicy.NO_PERSISTANCE);

        add(persistencyPolicyDB);
        add(persistencyPolicyFile);
        add(persistencyPolicyNoPersistency);

        //Action buttons
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> handleLoginEvent());
        registerButton.addActionListener(e -> handleRegisterEvent());
        add(loginButton);
        add(registerButton);

    }

    public void handleLoginEvent(){
        System.out.println("[BOUNDARY] Asking CONTROLLER to perform 'login'");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.login(usernameTextField.getText(), passwordTextField.getText(), persistencyPolicy);
    }

    public void handleRegisterEvent(){
        System.out.println("[BOUNDERY] Not yet implemented");
    }

}
