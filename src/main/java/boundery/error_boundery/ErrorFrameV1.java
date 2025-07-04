package boundery.error_boundery;

import repository.ColorRepository;

import javax.swing.*;
import java.awt.*;

public class ErrorFrameV1 extends JFrame implements ErrorFrame{
    JLabel errorLabel;
    JButton okButton;

    public ErrorFrameV1(String errorMessage){

        // Look and Feel
        setSize(300, 150);
        setBackground(Color.decode(ColorRepository.getErrorColor()));
        setLocationRelativeTo(null);

        // Usa layout verticale
        setLayout(new BorderLayout());


        // Label per il messaggio di errore
        errorLabel = new JLabel(errorMessage);
        errorLabel.setBackground(Color.decode(ColorRepository.getErrorColor()));

        // Bottone "OK"
        okButton = new JButton("Ok");
        okButton.addActionListener(e -> dispose());

        // Look and Feel
        add(errorLabel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        setAlwaysOnTop(true);
        setVisible(true);
    }
}
