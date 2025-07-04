package boundery.homepage;

import controller.homepage_controller.HomePageController;
import repository.ColorRepository;

import javax.swing.*;
import java.awt.*;

public class HomepageBounderyCentralPannelV1 extends JPanel {

    final LoansBandV1 loansBandV1;
    final ActivityBandV1 activityBandV1;
    final ServicesBandV1 servicesBandV1;

    public HomepageBounderyCentralPannelV1(HomePageController controller){
        //Attributes
        loansBandV1 = new LoansBandV1(controller);
        activityBandV1 = new ActivityBandV1(controller);
        servicesBandV1 = new ServicesBandV1(controller);

        //Look and Feel
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setLayout(new GridLayout(1, 3, 10, 0));
        setBackground(Color.decode(ColorRepository.getBackgroundColor()));

        //Components
        add(loansBandV1);
        add(activityBandV1);
        add(servicesBandV1);

        setVisible(true);
    }

}
