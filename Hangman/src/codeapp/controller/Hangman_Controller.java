/** **************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
 * \*************************************************************************** */
package codeapp.controller;

import codeapp.model.Letter;
import codeapp.view.Hangman;
import codeapp.view.MainApp;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Hangman_Controller implements ActionListener {

    private MainApp mainApp;
    private Hangman hangman;
    int seg,min,hour;
    private ArrayList<Character> word;
    private String answer = "";
    private int index = 0;
    private Letter[][] letters = new Letter[2][14];
    private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    private ArrayList<String> wordsToHangman;
    private boolean gameActive = false;

    /**
     * ************************************************************************\
     * This method is in charge of open a new hangman window.
     *
     * @param mainApp before window
     * @param hangman new window
     * \*************************************************************************
     */
    public void openHangman(MainApp mainApp, Hangman hangman, ArrayList<String> list) {
        this.mainApp = mainApp;
        this.hangman = hangman;
        this.hangman.setVisible(true);
        this.mainApp.setVisible(false);
        this.wordsToHangman = list;
        methodsManager();
    }

    /**
     * ************************************************************************\
     * Method that is in charge of call sequentially the game methods.
     * \*************************************************************************
     */
    public void methodsManager() {
        setActionListener();
        createButtons();
        createChronometer();

    }

    /**
     * ************************************************************************\
     * This method create the buttons that contains the alphabet letthers.
     * \*************************************************************************
     */
    public void createButtons() {
        hangman.pnLetters.setLayout(new GridLayout(2, 14));
        int ind = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 14; j++) {
                letters[i][j] = new Letter(alphabet[ind]);
                if (ind < alphabet.length - 1) {
                    letters[i][j].addActionListener(this);
                    ind++;
                    hangman.pnLetters.add(letters[i][j]);
                }

            }
        }
    }

    /**
     * ************************************************************************\
     * This method is in charge of close this window and show the main scream.
     * \*************************************************************************
     */
    public void goBack() {
        mainApp.setVisible(true);
        hangman.setVisible(false);
    }

    /**
     * ************************************************************************\
     * This method is in charge of manage the events that happen when a button
     * is pressed.
     *
     * @param e contains the event.
     * \*************************************************************************
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Letter) {
            if (gameActive) {
                Letter pushed = (Letter) e.getSource();
                pushed.setBackground(pushed.selected);
                checkContains(pushed.letter);
            }
        } else {
            JButton pushed = (JButton) e.getSource();
            if (pushed.getText().equals("Back")) {
                goBack();
            } else if (pushed.getText().equals("New Game")) {
                index = 0;
                startGame();
            } else if (pushed.getText().equals("+ Nivel")) {
                setLevel(true);
            } else if (pushed.getText().equals("- Nivel")) {
                setLevel(false);
            }
        }
    }

    /**
     * ************************************************************************\
     * Method that set a new action listener to all buttons in all buttons in
     * the interface.
     * \*************************************************************************
     */
    private void setActionListener() {
        hangman.btnBack.addActionListener(this);
        hangman.btnNewGame.addActionListener(this);
        hangman.btnAfter.addActionListener(this);
        hangman.btnBefore.addActionListener(this);
    }

    /**
     * ************************************************************************\
     * Method that start the game, this method call other methods that manage
     * the game execution.
     * \*************************************************************************
     */
    private void startGame() {
        answer = "";
        seg=min=hour=0;
        hangman.timer.start();
        hangman.lblWord.setText(answer);
        resertLetters();
        if (wordsToHangman.size() == 0) {
            JOptionPane.showMessageDialog(hangman, "No hay palabras ingresadas", "Alert", JOptionPane.WARNING_MESSAGE);
        }
        gameActive = true;
        setWord();
    }

    /**
     * ************************************************************************\
     * Repaint all letter, set the default color to all buttons.
     * \*************************************************************************
     */
    private void resertLetters() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 14; j++) {
                letters[i][j].setBackground(letters[i][j].unselected);
            }
        }
    }

    private void setWord() {
        word = new ArrayList<>(
                wordsToHangman.get(index).chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );
        for (int i = 0; i < word.size(); i++) {
            answer += "_";
        }
        hangman.lblWord.setText(answer);

    }

    private void checkContains(char letter) {
        String newAnswer = "";
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i) == letter) {
                newAnswer += letter;
            } else {
                newAnswer += answer.charAt(i);
            }
        }
        answer = newAnswer;
        hangman.lblWord.setText(answer);
        if (answer.equals(wordsToHangman.get(index))) {
            hangman.timer.stop();
            JOptionPane.showMessageDialog(hangman, "¡Felicitaciones has encontrado la palabra! Duración:" + hangman.lblCrono.getText());
            wordsToHangman.remove(index);
            setLevel(true);
        }
    }

    private void setLevel(boolean b) {
        if (b) {
            if (index < wordsToHangman.size() - 1) {
                index++;
            } else {
                index = 0;
            }
        } else if (index > 0) {
            index--;
        } else {
            index = wordsToHangman.size() - 1;
        }
        startGame();
    }

    private void createChronometer() {
        hangman.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seg++;
                if (seg == 60) {
                    min++;
                    seg = 0;
                    if (min == 60) {
                        hour++;
                        min = 0;
                    }
                }
                hangman.lblCrono.setText(hour + ":" + min + ":" + seg);
            }
        });
    }

}
