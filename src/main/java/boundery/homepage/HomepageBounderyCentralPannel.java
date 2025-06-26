package boundery.homepage;

import boundery.ColorRepository;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomepageBounderyCentralPannel extends JPanel {

    final LoansBand loansBand = new LoansBand();
    final ActivityBand activityBand = new ActivityBand();
    final ServicesBand servicesBand = new ServicesBand();

    public HomepageBounderyCentralPannel(){

        //Look and Feel
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setLayout(new GridLayout(1, 3, 10, 0));
        setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Components
        add(loansBand);
        add(activityBand);
        add(servicesBand);

        setVisible(true);
    }

}
