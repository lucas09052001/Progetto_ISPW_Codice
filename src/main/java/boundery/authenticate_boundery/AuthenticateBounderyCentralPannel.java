package boundery.authenticate_boundery;

import controller.AuthenticateController;
import entity.PersistencyPolicy;

import javax.swing.*;
import java.awt.*;

public class AuthenticateBounderyCentralPannel extends JPanel {

    private transient AuthenticateController controller = new AuthenticateController();

    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JTextField username_TextField = new JTextField("Username");
    JTextField password_TextField = new JTextField("Password");
    JLabel text_DB = new JLabel("Choose the persistency policy: ");
    JRadioButton persistency_policy_DB = new JRadioButton("DB");
    JRadioButton persistency_policy_File = new JRadioButton("File");
    JRadioButton persistency_policy_NoPersistency = new JRadioButton("No Persistency");
    ButtonGroup gruppo_bottoni = new ButtonGroup();

    PersistencyPolicy persistency_policy = PersistencyPolicy.NULL;

    public AuthenticateBounderyCentralPannel() {

        // Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#3DDC97"));

        //TextFields
        Dimension fieldSize = new Dimension(400, 50);
        username_TextField.setMaximumSize(fieldSize);
        password_TextField.setMaximumSize(fieldSize);

        username_TextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        password_TextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(30)); // spaziatura
        add(username_TextField);
        add(Box.createVerticalStrut(30));
        add(password_TextField);

        //RadioButtons for persistency policy
        text_DB.setAlignmentX(Component.CENTER_ALIGNMENT);
        text_DB.setBackground(Color.decode("#3DDC97"));
        add(text_DB);

        gruppo_bottoni.add(persistency_policy_DB);
        gruppo_bottoni.add(persistency_policy_File);
        gruppo_bottoni.add(persistency_policy_NoPersistency);

        persistency_policy_DB.setAlignmentX(Component.CENTER_ALIGNMENT);
        persistency_policy_File.setAlignmentX(Component.CENTER_ALIGNMENT);
        persistency_policy_NoPersistency.setAlignmentX(Component.CENTER_ALIGNMENT);

        persistency_policy_DB.addActionListener(e -> persistency_policy = PersistencyPolicy.DB);
        persistency_policy_File.addActionListener(e -> persistency_policy = PersistencyPolicy.FILE);
        persistency_policy_NoPersistency.addActionListener(e -> persistency_policy = PersistencyPolicy.NO_PERSISTANCE);

        add(persistency_policy_DB);
        add(persistency_policy_File);
        add(persistency_policy_NoPersistency);

        //Action buttons
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> handle_login_event());
        registerButton.addActionListener(e -> handle_register_event());
        add(loginButton);
        add(registerButton);

        setVisible(true);
    }

    public void handle_login_event(){
        System.out.println("[SYSTEM] Initializing 'login' op in Authenticate_Controller");
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.login(username_TextField.getText(), password_TextField.getText(), persistency_policy);
    }

    public void handle_register_event(){    //NON IMPLEMENTATO, DA NON CONSIDERARE
        System.out.println("[EE] Not yet implemented");
        System.exit(0);
    }

}
