package Boundery.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Custom_Button extends JButton {

    public Custom_Button(ActionListener actionListener){
        super("CustomButton"); // Testo del bottone

        // Stile base
        this.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.setFocusPainted(false);
        this.setBackground(new Color(66, 133, 244)); // Blu Google
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(100, 40));
        this.setToolTipText("Clicca per effettuare l'accesso");

        // Listener passato da chi crea il bottone
        addActionListener(actionListener);
    }

}
