import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeConversion extends JFrame implements ActionListener {

    private JLabel indianTimeLabel;
    private JTextField indianTimeTextField;
    private JLabel convertedTimeLabel;
    private JTextField convertedTimeTextField;
    private JButton convertButton;

    public TimeConversion() {
        setTitle("Time Conversion by Country");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        
        indianTimeLabel = new JLabel("Indian Time:");
        add(indianTimeLabel);
        
        indianTimeTextField = new JTextField();
        add(indianTimeTextField);
        
        convertedTimeLabel = new JLabel("Converted Time:");
        add(convertedTimeLabel);
        
        convertedTimeTextField = new JTextField();
        convertedTimeTextField.setEditable(false);
        add(convertedTimeTextField);
        
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String indianTime = indianTimeTextField.getText();
            if (!indianTime.matches("\\d{2}:\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Please enter Indian time in HH:MM:SS format.");
                return;
            }
            
            int hours = Integer.parseInt(indianTime.substring(0, 2));
            int minutes = Integer.parseInt(indianTime.substring(3, 5));
            int seconds = Integer.parseInt(indianTime.substring(6, 8));
            
            String[] countries = {"USA", "UK", "Australia", "Japan"};
            String selectedCountry = (String) JOptionPane.showInputDialog(this, "Select a country:", "Country Selection", JOptionPane.PLAIN_MESSAGE, null, countries, countries[0]);
            
            if (selectedCountry == null) {
                return;
            }
            
            int offset;
            switch (selectedCountry) {
                case "USA":
                    offset = -9;
                    break;
                case "UK":
                    offset = -5;
                    break;
                case "Australia":
                    offset = 5;
                    break;
                case "Japan":
                    offset = 3;
                    break;
                default:
                    offset = 0;
                    break;
            }
            
            hours += offset;
            if (hours < 0) {
                hours += 24;
            } else if (hours >= 24) {
                hours -= 24;
            }
            
            String convertedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            convertedTimeTextField.setText(convertedTime);
        }
    }
    
    public static void main(String[] args) {
        new TimeConversion();
    }
}