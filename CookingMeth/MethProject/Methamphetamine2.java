import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.sound.sampled.*;

public class Methamphetamine2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Methamphetamine Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Add a background image
        ImageIcon backgroundImage = new ImageIcon("Assets/Waltuh.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        frame.add(backgroundLabel);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(panel);

        JLabel compositionLabel = new JLabel("Chemical Composition:");
        JTextField compositionField = new JTextField();
        JLabel purityLabel = new JLabel("Purity (%):");
        JTextField purityField = new JTextField();
        JLabel colorLabel = new JLabel("Color:");
        JTextField colorField = new JTextField();
        JLabel costLabel = new JLabel("Production Cost ($):");
        JTextField costField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JLabel resultLabel = new JLabel();

        panel.add(compositionLabel);
        panel.add(compositionField);
        panel.add(purityLabel);
        panel.add(purityField);
        panel.add(colorLabel);
        panel.add(colorField);
        panel.add(costLabel);
        panel.add(costField);
        panel.add(submitButton);
        panel.add(resultLabel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String composition = compositionField.getText();
                    double purity = Double.parseDouble(purityField.getText());
                    String color = colorField.getText();
                    double cost = Double.parseDouble(costField.getText());

                    Methamphetamine blueMeth = new Methamphetamine(composition, purity, color, cost);

                    String result = "Chemical Composition: " + blueMeth.getChemicalComposition() + "<br>"
                            + "Purity: " + blueMeth.getPurity() + "%" + "<br>"
                            + "Color: " + blueMeth.getColor() + "<br>"
                            + "Production Cost: $" + blueMeth.calculateProductionCost();

                    resultLabel.setText("<html>" + result + "</html>");

                    // Play music
                    playMusic("Assets/BBBit.wav");
                    
                    // Save information to a text file
                    saveInformation(composition, purity, color, cost);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numerical values.");
                }
            }
        });

        frame.setVisible(true);
    }

    private static void playMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing music: " + ex.getMessage());
        }
    }
    
    private static void saveInformation(String composition, double purity, String color, double cost) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Item Info.txt", true))) {
            writer.println("Chemical Composition: " + composition);
            writer.println("Purity: " + purity + "%");
            writer.println("Color: " + color);
            writer.println("Production Cost: $" + cost);
            writer.println();
        } catch (IOException e) {
            System.out.println("Error saving information: " + e.getMessage());
        }
    }
}
