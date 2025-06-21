package Boundery.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class Custom_TextField extends JTextField {

    public Custom_TextField() {
        super(); // imposta la larghezza (numero di colonne)

        // Look and Feel
        setColumns(40);
        setBackground(Color.decode("#46237A")); // Sfondo bianco
        setForeground(Color.decode("#3DDC97")); // Testo celeste/emerald
        setFont(new Font("SansSerif", Font.PLAIN, 14));

    }

}

