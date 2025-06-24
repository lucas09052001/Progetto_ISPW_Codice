package boundery.error_boundery;

import boundery.Color_Repository;

import javax.swing.*;
import java.awt.*;



public class ErrorBounderyCentralPannel extends JPanel {

    JLabel errorLabel;

    ErrorBounderyCentralPannel(String error_Message){

        // Look and Feel

        setSize(300, 150);
        setBackground(Color.decode(Color_Repository.getError_color()));

        // Usa layout verticale
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Label per il messaggio di errore
        errorLabel = new JLabel(error_Message);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Bottone "OK"
        JButton okButton = new JButton("OK");
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        // Look and Feel
        add(errorLabel);
        add(Box.createVerticalStrut(50));
        add(okButton);
    }

}
