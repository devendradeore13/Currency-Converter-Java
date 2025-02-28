import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter {
    private static HashMap<String, Double> exchangeRates = new HashMap<>();

    public static void main(String[] args) {
        // Initialize exchange rates
        exchangeRates.put("USD-INR", 82.0);
        exchangeRates.put("INR-USD", 0.012);
        exchangeRates.put("USD-EUR", 0.91);
        exchangeRates.put("EUR-USD", 1.10);
        
        // Create JFrame
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Components
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50, 30, 100, 30);
        frame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 30, 150, 30);
        frame.add(amountField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(50, 70, 100, 30);
        frame.add(fromLabel);

        String[] currencies = {"USD", "INR", "EUR"};
        JComboBox<String> fromCurrency = new JComboBox<>(currencies);
        fromCurrency.setBounds(150, 70, 150, 30);
        frame.add(fromCurrency);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(50, 110, 100, 30);
        frame.add(toLabel);

        JComboBox<String> toCurrency = new JComboBox<>(currencies);
        toCurrency.setBounds(150, 110, 150, 30);
        frame.add(toCurrency);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(150, 150, 100, 30);
        frame.add(convertButton);

        JLabel resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setBounds(50, 190, 300, 30);
        frame.add(resultLabel);

        // ActionListener for conversion
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String from = (String) fromCurrency.getSelectedItem();
                    String to = (String) toCurrency.getSelectedItem();
                    
                    if (from.equals(to)) {
                        resultLabel.setText("Select different currencies!");
                        return;
                    }

                    String key = from + "-" + to;
                    double rate = exchangeRates.getOrDefault(key, 1.0);
                    double convertedAmount = amount * rate;

                    resultLabel.setText("Converted Amount: " + convertedAmount + " " + to);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid amount!");
                }
            }
        });

        frame.setVisible(true);
    }
}
