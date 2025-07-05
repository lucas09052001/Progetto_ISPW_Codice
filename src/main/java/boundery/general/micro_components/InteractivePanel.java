package boundery.general.micro_components;

import utilities.ColorUtility;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public abstract class InteractivePanel extends JPanel {

    JLabel icon;
    InfoPanel infoPanel;
    JButton genericButton;

    protected InteractivePanel(){
        icon = new JLabel();
        infoPanel = new InfoPanel();
        genericButton = new JButton();

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));
        setBackground(Color.decode(ColorUtility.getBackgroundColor()));

        //Static set up
        genericButton.addActionListener(e -> handleButtonEvent());

        //Adding components
        add(Box.createHorizontalGlue());
        add(icon);
        add(Box.createHorizontalGlue());
        add(infoPanel);
        add(Box.createHorizontalGlue());
        add(genericButton);
        add(Box.createHorizontalGlue());

    }

    public void setUp(){
        String pathToIcon =  getPathToIcon();
        icon.setIcon(new ImageIcon(pathToIcon));

        ArrayList<String> stringsToShow;
        stringsToShow = gatherStringsToShow();
        infoPanel.setUp(stringsToShow);

        String buttonText = getButtonText();
        genericButton.setText(buttonText);

        //Setting visibility
        setVisible(true);
    }

    protected abstract String getButtonText();

    protected abstract String getPathToIcon();

    protected abstract ArrayList<String> gatherStringsToShow();

    protected abstract void handleButtonEvent();

}
