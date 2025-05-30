package degree;

import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends Frame implements ActionListener {
    Label inputLabel, resultLabel;
    TextField inputField;
    Button cToFButton, fToCButton;

    TemperatureConverter() {
        setLayout(new FlowLayout());

        inputLabel = new Label("Enter Temperature:");
        inputField = new TextField(10);

        cToFButton = new Button("C to F");
        fToCButton = new Button("F to C");

        resultLabel = new Label("Result");

        add(inputLabel);
        add(inputField);
        add(cToFButton);
        add(fToCButton);
        add(resultLabel);

        cToFButton.addActionListener(this);
        fToCButton.addActionListener(this);

        setTitle("Temp Converter");
        setSize(250, 150);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            double temp = Double.parseDouble(inputField.getText());
            double result;

            if (ae.getSource() == cToFButton) {
                result = (temp * 9 / 5) + 32;
                resultLabel.setText("Result: " + result + " °F");
            } else {
                result = (temp - 32) * 5 / 9;
                resultLabel.setText("Result: " + result + " °C");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}
