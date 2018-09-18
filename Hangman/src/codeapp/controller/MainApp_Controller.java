/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeapp.controller;

import codeapp.view.Hangman;
import codeapp.view.MainApp;

/**
 *
 * @author jushuu
 */
public class MainApp_Controller {
    LettersSoup_Controller soupController = new LettersSoup_Controller();
    Hangman_Controller hangmanController = new Hangman_Controller();
    
    public void openHangman(MainApp mainApp){
       Hangman hangman = new Hangman();
       hangmanController.openHangman(mainApp,hangman);
    }
}
