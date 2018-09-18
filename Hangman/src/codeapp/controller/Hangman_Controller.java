/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeapp.controller;

import codeapp.model.Letter;
import codeapp.view.Hangman;
import codeapp.view.MainApp;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author jushuu
 */
public class Hangman_Controller implements ActionListener{
    private MainApp mainApp;
    private Hangman hangman;
    private Letter[][] letters= new Letter[2][13];
    private char[] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    
    public void openHangman(MainApp mainApp, Hangman hangman) {
       this.mainApp=mainApp;
       this.hangman=hangman;
       this.hangman.setVisible(true);
       this.mainApp.setVisible(false);
       methodsManager();
    }
    
    public void methodsManager(){
        setActionListener();
        createButtons();
    }
    public void createButtons(){
        hangman.pnLetters.setLayout(new GridLayout(2,13));
        int index=0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                letters[i][j]= new Letter(alphabet[index]);
                letters[i][j].addActionListener(this);
                index++;
                hangman.pnLetters.add(letters[i][j]);
            }
        }
        
    }
    public void goBack(){
        mainApp.setVisible(true);
        hangman.setVisible(false);
    }
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
    
    private void setActionListener() {
        hangman.btnBack.addActionListener(this);
        hangman.btnNewGame.addActionListener(this);
    }
    
    private void startGame() {
     
    }
}
