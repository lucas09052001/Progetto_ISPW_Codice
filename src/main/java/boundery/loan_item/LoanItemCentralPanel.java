package boundery.loan_item;

import controller.loanitem_controller.LoanItemController;
import utilities.ColorUtility;
import entity.loan.LoanInterval;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import static entity.loan.LoanInterval.NULL;

public class LoanItemCentralPanel extends JPanel {

    private transient LoanItemController controller;
    private LoanInterval interval = NULL;

    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JTextField pathToImageTextField;

    private JLabel intervalLabel;
    private JRadioButton hourIntervalRadioButton;
    private JRadioButton dayIntervalRadioButton;
    private JRadioButton weekIntervalRadioButton;
    private JRadioButton monthIntervalRadioButton;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private JButton cancelButton;


    public LoanItemCentralPanel(LoanItemController controller){
        //Attributes
        this.controller = controller;

        // Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorUtility.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorUtility.getForegroundColor())));

        //Instantiating components
        nameTextField = new JTextField();
        descriptionTextField = new JTextField();
        pathToImageTextField = new JTextField();

        intervalLabel = new JLabel();
        hourIntervalRadioButton = new JRadioButton();
        dayIntervalRadioButton = new JRadioButton();
        weekIntervalRadioButton = new JRadioButton();
        monthIntervalRadioButton = new JRadioButton();
        buttonGroup = new ButtonGroup();
        submitButton = new JButton();
        cancelButton = new JButton();

        // Object Name TextField
        Dimension fieldSize = new Dimension(200, 20);
        nameTextField.setText("What are you loaning?");
        nameTextField.setMaximumSize(fieldSize);
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue()); // spaziatura
        add(nameTextField);
        add(Box.createVerticalGlue());

        // Loan Description TextField
        descriptionTextField.setText("Loan description");
        descriptionTextField.setMaximumSize(fieldSize);
        descriptionTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(descriptionTextField);
        add(Box.createVerticalGlue());

        // Path to image TextField
        pathToImageTextField.setText("Path to image");
        pathToImageTextField.setMaximumSize(fieldSize);
        pathToImageTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(pathToImageTextField);
        add(Box.createVerticalGlue());


        // Interval Label
        intervalLabel.setText("Maximum loan interval will be:");
        intervalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        intervalLabel.setBackground(Color.decode(ColorUtility.getDynamicColor()));
        intervalLabel.setForeground(Color.decode(ColorUtility.getForegroundColor()));
        intervalLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        add(intervalLabel);
        add(Box.createVerticalGlue());


        //RadioButtons
        hourIntervalRadioButton.setText("Hour");
        hourIntervalRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hourIntervalRadioButton.addActionListener(e -> interval = LoanInterval.HOUR);
        buttonGroup.add(hourIntervalRadioButton);
        add(hourIntervalRadioButton);

        dayIntervalRadioButton.setText("Day");
        dayIntervalRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dayIntervalRadioButton.addActionListener(e -> interval = LoanInterval.DAY);
        buttonGroup.add(dayIntervalRadioButton);
        add(dayIntervalRadioButton);

        weekIntervalRadioButton.setText("Week");
        weekIntervalRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        weekIntervalRadioButton.addActionListener(e -> interval = LoanInterval.WEEK);
        buttonGroup.add(weekIntervalRadioButton);
        add(weekIntervalRadioButton);

        monthIntervalRadioButton.setText("Month");
        monthIntervalRadioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        monthIntervalRadioButton.addActionListener(e -> interval = LoanInterval.MONTH);
        buttonGroup.add(monthIntervalRadioButton);
        add(monthIntervalRadioButton);
        add(Box.createVerticalGlue());

        // Submit Button
        submitButton.setText("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> handleSubmitEvent());
        add(submitButton);
        add(Box.createVerticalGlue());

        //Cancel Button
        cancelButton.setText("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> handleCancelEvent());
        add(cancelButton);
        add(Box.createVerticalGlue());

        setVisible(true);

    }

    private void handleSubmitEvent(){
        System.out.println("[BOUNDERY] SubmitLoan Event triggered");
        controller.submit(nameTextField.getText(), descriptionTextField.getText(), interval, pathToImageTextField.getText());
    }

    private void handleCancelEvent(){
        System.out.println("[BOUNDERY] Minor Function. Not yet implemented");
    }


}
