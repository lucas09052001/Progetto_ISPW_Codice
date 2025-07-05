package boundery.homepage;

import controller.homepage_controller.HomePageController;
import utilities.ColorUtility;

import javax.swing.*;
import java.awt.*;

public class HomepageBounderyCentralPannelV1 extends JPanel {

    LoansBandV1 loansBandV1;
    ActivityBandV1 activityBandV1;
    ServicesBandV1 servicesBandV1;

    public HomepageBounderyCentralPannelV1(HomePageController controller){
        //Attributes
        loansBandV1 = new LoansBandV1(controller);
        activityBandV1 = new ActivityBandV1(controller);
        servicesBandV1 = new ServicesBandV1(controller);

        //Look and Feel
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setLayout(new GridLayout(1, 3, 10, 0));
        setBackground(Color.decode(ColorUtility.getBackgroundColor()));

        //Components
        add(loansBandV1);
        add(activityBandV1);
        add(servicesBandV1);

        setVisible(true);
    }

}
