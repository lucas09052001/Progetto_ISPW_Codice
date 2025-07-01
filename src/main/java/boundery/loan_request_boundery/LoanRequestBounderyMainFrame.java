package boundery.loan_request_boundery;

import boundery.ColorRepository;
import boundery.borrow_item_boundery.BorrowItemBounderyCentralPannel;
import boundery.macro_components.DashBoard;

import javax.swing.*;
import java.awt.*;

public class LoanRequestBounderyMainFrame extends JFrame {
    //MainFrame components
    LoanRequestBounderyCentralPannel centralPanel = new LoanRequestBounderyCentralPannel();
    DashBoard dashBoard = new DashBoard();
    JPanel southPannel = new JPanel();
    JPanel eastPannel = new JPanel();
    JPanel westPannel = new JPanel();


    public LoanRequestBounderyMainFrame() {
        super("Unishare");

        // Look and Feel
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        southPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        eastPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));
        westPannel.setBackground(Color.decode(ColorRepository.getBackgroundColor()));


        //Behavior
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Components
        add(centralPanel, BorderLayout.CENTER);
        add(dashBoard, BorderLayout.NORTH);
        add(eastPannel, BorderLayout.EAST);
        add(westPannel, BorderLayout.WEST);
        add(southPannel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
