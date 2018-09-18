/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.controller;
import codeapp.model.Letter;
import codeapp.view.Hangman;
import codeapp.view.MainApp;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Hangman_Controller implements ActionListener{
    private MainApp mainApp;
    private Hangman hangman;
    private Letter[][] letters= new Letter[2][13];
    private char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    /**************************************************************************\
     * This method is in charge of open a new hangman window.
     * @param mainApp before window
     * @param hangman new window
    \**************************************************************************/
    public void openHangman(MainApp mainApp, Hangman hangman) {
       this.mainApp=mainApp;
       this.hangman=hangman;
       this.hangman.setVisible(true);
       this.mainApp.setVisible(false);
       methodsManager();
    }
    /**************************************************************************\
     * Method that is in charge of call sequentially the game methods.
    \**************************************************************************/
    public void methodsManager(){
        setActionListener();
        createButtons();
    }
    /**************************************************************************\
     *  This method create the buttons that contains the alphabet letthers.
    \**************************************************************************/
    public void createButtons(){
        hangman.pnLetters.setLayout(new GridLayout(2,13));
        int index=0;
        for (int i = 0; i < 2; i++) 
            for (int j = 0; j < 13; j++) {
                letters[i][j]= new Letter(alphabet[index]);
                letters[i][j].addActionListener(this);
                index++;
                hangman.pnLetters.add(letters[i][j]);
            }
    }
    /**************************************************************************\
     * This method is in charge of close this window and show the main scream.
    \**************************************************************************/
    public void goBack(){
        mainApp.setVisible(true);
        hangman.setVisible(false);
    }
    /**************************************************************************\
     * This method is in charge of manage the events that happen when a button 
     * is pressed.
     * @param e contains the event.
    \**************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() instanceof Letter){
         Letter pushed= (Letter) e.getSource(); 
         pushed.setBackground(pushed.selected);
      }else{
        JButton pushed= (JButton) e.getSource(); 
        if(pushed.getText().equals("Back"))
          goBack();
        else if(pushed.getText().equals("New Game"))
          startGame(); 
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
     resertLetters();
    }
    /**************************************************************************\
     * Repaint all letter, set the default color to all buttons.
    \**************************************************************************/
    private void resertLetters() {
        for (int i = 0; i < 2; i++) 
            for (int j = 0; j < 13; j++) 
                letters[i][j].setBackground(letters[i][j].unselected);
    }
}
