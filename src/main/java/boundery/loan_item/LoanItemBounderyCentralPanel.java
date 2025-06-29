package boundery.loan_item;

import boundery.ColorRepository;
import controller.LoanItemController;
import entity.loan.LoanInterval;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import static entity.loan.LoanInterval.NULL;

public class LoanItemBounderyCentralPanel extends JPanel {

    LoanItemController controller = new LoanItemController();
    String loanObjectName = "";
    String description = "";
    LoanInterval interval = NULL;

    JTextField nameTextField = new JTextField();
    JTextField descriptionTextField = new JTextField();
    JLabel intervalLabel = new JLabel();
    JRadioButton hourIntervalRadioButton = new JRadioButton();
    JRadioButton dayIntervalRadioButton = new JRadioButton();
    JRadioButton weekIntervalRadioButton = new JRadioButton();
    JRadioButton monthIntervalRadioButton = new JRadioButton();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton submitButton = new JButton();
    JButton cancelButton = new JButton();


    public LoanItemBounderyCentralPanel(){

        // Look and Feel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode(ColorRepository.getDynamicColor()));
        setBorder(new LineBorder(Color.decode(ColorRepository.getForegroundColor())));

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

        // Interval Label
        intervalLabel.setText("Maximum loan interval will be:");
        intervalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        intervalLabel.setBackground(Color.decode(ColorRepository.getDynamicColor()));
        intervalLabel.setForeground(Color.decode(ColorRepository.getForegroundColor()));
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
        SwingUtilities.getWindowAncestor(this).dispose();
        controller.submit(nameTextField.getText(), descriptionTextField.getText(), interval);
    }

    private void handleCancelEvent(){

    }


}
