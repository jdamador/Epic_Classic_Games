/** **************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
 * \*************************************************************************** */
package codeapp.controller;
import codeapp.view.Hangman;
import codeapp.view.MainApp;
import java.awt.Color;
import java.awt.Font;
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
    private int index = -1;
    private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    private ArrayList<String> allWordsToHangman;
    private boolean gameActive = false;
    private ArrayList<JButton> btnLetters= new ArrayList<>();
    /* ************************************************************************\
     * This method is in charge of open a new hangman window.
     * @param mainApp before window
     * @param hangman new window
    \**************************************************************************/
    public void openHangman(MainApp mainApp, Hangman hangman, ArrayList<String> list) {
        this.mainApp = mainApp;
        this.hangman = hangman;
        this.hangman.setVisible(true);
        this.mainApp.setVisible(false);
        this.allWordsToHangman = list;
        this.index =-1;
        methodsManager();
    }
    /* ************************************************************************\
     * Method that is in charge of call sequentially the game methods.
    \**************************************************************************/
    public void methodsManager() {
        setActionListener();
        createButtons();
        createChronometer();
    }
    /**************************************************************************\
     * This method create the buttons that contains the alphabet letthers.
    \**************************************************************************/
    public void createButtons() {
        int ind = 0;
        for (int i = 0; i < 27; i++) {
                btnLetters.add(new JButton(alphabet[ind]+""));
                btnLetters.get(i).setVisible(true);
                btnLetters.get(i).addActionListener(this);
                btnLetters.get(i).setBackground(new Color(Integer.parseInt("83bb40", 16 )));
                btnLetters.get(i).setFont(new Font("Hack", Font.BOLD,14 ));
                ind++;         
                hangman.pnLetters.add(btnLetters.get(i));
        }
    }
    /* ************************************************************************\
     * This method is in charge of close this window and show the main scream.
    \**************************************************************************/
    public void goBack() {
        mainApp.setVisible(true);
        hangman.setVisible(false);
    }
    /* ************************************************************************\
     * This method is in charge of manage the events that happen when a button
     * is pressed.
     * @param e contains the event.
    \**************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
            JButton pushed = (JButton) e.getSource();
            if (pushed.getText().equals("Back")) 
                goBack();
            else if (pushed.getText().equals("New Game")) {
                startGame();
            }else
                if(gameActive){
                    checkContains(pushed.getText());
                    pushed.setBackground(new Color(Integer.parseInt("e65224", 16 )));
                }
    }
    /**************************************************************************\
     * Method that set a new action listener to all buttons in all buttons in
     * the interface.
    \**************************************************************************/
    private void setActionListener() {
        hangman.btnBack.addActionListener(this);
        hangman.btnNewGame.addActionListener(this);
    }
    /**************************************************************************\
     * Method that start the game, this method call other methods that manage
     * the game execution.
    \**************************************************************************/
    private void startGame() {
        answer = "";
        seg=min=hour=0;
        hangman.timer.start();
        hangman.lblWord.setText(answer);
        resertLetters();
        if (allWordsToHangman.size() == 0) 
            JOptionPane.showMessageDialog(hangman, "No hay palabras ingresadas", "Alert", JOptionPane.WARNING_MESSAGE);
        else{
            index++;
            gameActive = true;
            setWord();
        }
    }
    /**************************************************************************\
     * Repaint all letter, set the default color to all buttons.
    \**************************************************************************/
    
    private void resertLetters() {
        btnLetters.stream().filter(S-> S.getBackground().equals(new Color(Integer.parseInt("e65224", 16 ))))
                .forEach(E-> E.setBackground(new Color(Integer.parseInt("83bb40", 16 ))));
    }
    /**************************************************************************\
     * This method add the word to guess into the screen. (Fuctional)
    \**************************************************************************/
    private void setWord() {
        //Functional programming map method.
        word = new ArrayList<>(
                allWordsToHangman.get(index).chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()));
       
        for (int i = 0; i < word.size(); i++)
            answer += "_";
        hangman.lblWord.setText(answer);
    }
    /**************************************************************************\
     * Check each time when is pressed a button if the letters is in the word 
     * to guess.
     * @param letter 
    \**************************************************************************/
    private void checkContains(String letter) {
        String newAnswer = "";
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i).equals(letter.charAt(0))) 
                newAnswer += letter;
            else
                newAnswer += answer.charAt(i);
        }
        answer = newAnswer;
        hangman.lblWord.setText(answer);
        //Validate if the word is complete and show a congratulation messages
        if (answer.equals(allWordsToHangman.get(index))) {
            hangman.timer.stop();
            JOptionPane.showMessageDialog(hangman, "¡Felicitaciones has encontrado la palabra! Duración:" + hangman.lblCrono.getText());
            allWordsToHangman.remove(index);
            startGame();
        }
    }
    /**************************************************************************\
     * Its initialize the instance of timer that is in charge of manage
     * the time that the person lasted  to guess the word.
    \**************************************************************************/
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
