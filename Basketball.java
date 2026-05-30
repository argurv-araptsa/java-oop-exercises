import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Basketball implements ActionListener {
    private JTextField num1Field, num2Field, resultField;
    private JButton upologismosButton, resetButton, endButton;

    public Basketball() {
        JFrame frame = new JFrame("Basketball");
        frame.setSize(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel headingLabel =
                new JLabel("Ypologistis diaforetikwn omadwn mpasket", JLabel.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 22));
        headingLabel.setBorder(
                BorderFactory.createEmptyBorder(10, 0, 10, 0)
        );
        frame.add(headingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel num1Label = new JLabel("Sinolikos arithmos diathesimos paiktwn omadas mpasket:");
        num1Field = new JTextField();
        formPanel.add(num1Label);
        formPanel.add(num1Field);

        JLabel num2Label = new JLabel("Arithmos paiktwn tautoxrona sto parke:");
        num2Field = new JTextField();
        formPanel.add(num2Label);
        formPanel.add(num2Field);

        JLabel resultLabel = new JLabel("Plithos diaforetikwn omadwn:");
        resultField = new JTextField();
        resultField.setEditable(false); 
        formPanel.add(resultLabel);
        formPanel.add(resultField);

        upologismosButton = new JButton("Ypologismos");
        resetButton = new JButton("Reset");
        endButton = new JButton("Termatismos");


        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        buttonPanel.add(upologismosButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(endButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        upologismosButton.addActionListener(this);
        endButton.addActionListener(this);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        frame.setVisible(true);
    }

  public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Ypologismos")) {
            try {
                int num1 = Integer.parseInt(num1Field.getText().trim());
                int num2 = Integer.parseInt(num2Field.getText().trim());

                if (num1 < 0 || num2 < 0 || num2 > num1) {
                    showError("Please make sure 0 <= num2 <= num1.");
                    return;
                }

                long combinations = paragontiko(num1) / (paragontiko(num2) * paragontiko(num1 - num2));
                resultField.setText(String.valueOf(combinations));
            } catch (NumberFormatException ex) {
                showError("Please enter valid numbers.");
            }
        } else if (command.equals("Termatismos")) {
            System.exit(0);
        }
    }

    private long paragontiko(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        num1Field.setText("");
        num2Field.setText("");
        resultField.setText("");
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Basketball());
    }
}