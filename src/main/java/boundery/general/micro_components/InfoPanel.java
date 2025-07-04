package boundery.general.micro_components;

import repository.ColorRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoPanel extends JPanel {
    ArrayList<JLabel> labelList;
    ArrayList<String> strings;

    public InfoPanel(){

        //Static Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Instantiating attributes
        labelList = new ArrayList<>();

    }

    public void setUp(ArrayList<String> strings){
        //Attributes
        this.strings = strings;

        //Instantiating as many labels as strings to show in labelList
        for(int i = 0; i < strings.size(); i++){
            JLabel curr = new JLabel();

            //Static look and feel of labels
            curr.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
            curr.setForeground(Color.decode(ColorRepository.getForegroundColor()));
            curr.setFont(new Font("Arial", Font.PLAIN, 16));
            labelList.add(curr);
        }

        //Adding Components
        for(JLabel l : labelList){
            add(l);
        }
        populate();

        setVisible(true);
    }

    private void populate(){

        //Set text
        for(int i = 0; i < strings.size(); i++){
            JLabel currLabel = labelList.get(i);
            currLabel.setText(strings.get(i));
        }

        //Refresh
        revalidate();
        repaint();
    }

}
