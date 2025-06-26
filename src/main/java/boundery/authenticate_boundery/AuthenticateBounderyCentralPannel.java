package boundery.authenticate_boundery;

import boundery.ColorRepository;
import controller.useCaseController.AuthenticateController;
import entity.PersistencyPolicy;

import javax.swing.*;
import java.awt.*;

public class AuthenticateBounderyCentralPannel extends JPanel {

    private transient AuthenticateController controller = new AuthenticateController();

    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JTextField usernameTextField = new JTextField("Username");
    JTextField passwordTextField = new JTextField("Password");
    JLabel textPersistency = new JLabel("Choose the persistency policy: ");
    JRadioButton persistencyPolicyDB = new JRadioButton("DB");
    JRadioButton persistencyPolicyFile = new JRadioButton("File");
    JRadioButton persistencyPolicyNoPersistency = new JRadioButton("No Persistency");
    ButtonGroup gruppoBottoni = new ButtonGroup();

    PersistencyPolicy persistencyPolicy = PersistencyPolicy.NULL;

    public AuthenticateBounderyCentralPannel() {

        // Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#3DDC97"));

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

        setVisible(true);
    }

    public void handleLoginEvent(){
        System.out.println("[SYSTEM] Initializing 'login' op in Authenticate_Controller");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.login(usernameTextField.getText(), passwordTextField.getText(), persistencyPolicy);
    }

    public void handleRegisterEvent(){    //NON IMPLEMENTATO, DA NON CONSIDERARE
        System.out.println("[EE] Not yet implemented");
        System.exit(0);
    }

}
