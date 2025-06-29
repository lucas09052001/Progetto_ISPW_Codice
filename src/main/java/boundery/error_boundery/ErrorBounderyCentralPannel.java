package boundery.error_boundery;

import boundery.ColorRepository;

import javax.swing.*;
import java.awt.*;



public class ErrorBounderyCentralPannel extends JPanel {

    JLabel errorLabel;

    public ErrorBounderyCentralPannel(String errorMessage){

        // Look and Feel

        setSize(300, 150);
        setBackground(Color.decode(ColorRepository.getErrorColor()));

        // Usa layout verticale
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Label per il messaggio di errore
        errorLabel = new JLabel(errorMessage);
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
