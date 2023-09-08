import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame = new JFrame("Number Guesser");
    private JLabel label;
    private JTextArea resultTextArea;
    private JTextField numberTextField;
    private JButton start,cancel,check,playAgain,exit;
    private int targetNumber;
    private int tries;

    public NumberGuessingGame() {

        start = new JButton("Let's get Start");
        start.setBounds(80,50,150,30);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,50,150,30);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.getContentPane().removeAll();

                label = new JLabel("Guess the number Between 1 to 100...");
                label.setBounds(120,100,250,30);

                numberTextField = new JTextField("");
                numberTextField.setBounds(160,150,150,30);

                resultTextArea = new JTextArea("");
                resultTextArea.setBounds(80,220,400,50);

                check = new JButton("Check");
                check.setBounds(130,300,200,30);

                exit = new JButton("Exit");
                exit.setBounds(130,350,200,30);

                playAgain = new JButton("Play Again");
                playAgain.setBounds(130,400,200,30);

                frame.add(label);
                frame.add(numberTextField);
                frame.add(resultTextArea);
                frame.add(check);
                frame.add(exit);
                frame.add(playAgain);

                generateNewNumber();
                frame.revalidate();
                frame.repaint();

                final String[] play = {"yes"};

                check.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkGuess();
                    }
                });

                playAgain.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        generateNewNumber(); // Generate a new random number
                        resultTextArea.setText(""); // Clear the result display
                        numberTextField.setText(""); // Clear the input field
                        check.setEnabled(true); // Enable the "Check" button
                        playAgain.setEnabled(false); // Disable the "Play Again" button
                    }
                });

                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.add(start);
        frame.add(cancel);

        frame.setLayout(null);
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    private void generateNewNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1; // Generates a number between 1 and 100
        tries = 0;
    }

    private void checkGuess() {
        String guessText = numberTextField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            tries++;

            if (guess == targetNumber) {
                resultTextArea.setText("Congratulations! You guessed the number " + targetNumber +
                        " in " + tries + " tries.");
                generateNewNumber();
            } else if (guess < targetNumber) {
                resultTextArea.setText("Try a higher number.");
            } else {
                resultTextArea.setText("Try a lower number.");
            }
        } catch (NumberFormatException e) {
            resultTextArea.setText("Please enter a valid number.");
        }

        numberTextField.setText(""); // Clear the text field for the next guess
        numberTextField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
