/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.controller;
import codeapp.view.Hangman;
import codeapp.view.MainApp;
import codeapp.view.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class MainApp_Controller implements ActionListener {
    /**************************************************************************\
     * Variables declaration.
    \**************************************************************************/
    MainApp mainApp;
    LettersSoup_Controller soupController = new LettersSoup_Controller();
    Hangman_Controller hangmanController = new Hangman_Controller();
    Settings_Controller settingsController = new Settings_Controller();
    public ArrayList<String> lettesSoupWords = new ArrayList<>();
    public ArrayList<String> hangmanWords = new ArrayList<>();
    /**************************************************************************\
     *  Constructor. Initialize the variables.
     * @param mainApp 
    \**************************************************************************/
    public MainApp_Controller(MainApp mainApp){
       this.mainApp=mainApp;
       this.mainApp.btnHangman.addActionListener(this);
       this.mainApp.btnSoup.addActionListener(this);
       this.mainApp.btnSettings.addActionListener(this);
    }
    /**************************************************************************\
     * Call the method that open a new hangman window.
     * @param mainApp 
    \**************************************************************************/
    public void openHangman(MainApp mainApp){
       Hangman hangman = new Hangman();
       hangmanController.openHangman(mainApp,hangman);
    }
    /**************************************************************************\
     * Call the method that open a new hangman window.
     * @param mainApp 
    \**************************************************************************/
    private void openSoup(MainApp mainApp) {
    }
    /**************************************************************************\
     * This method is in charge of manage the events that happen when a button 
     * is pressed.
     * @param e contains the event.
    \**************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn.getToolTipText().equals("Hangman"))
            openHangman(mainApp);
        else if(btn.getToolTipText().equals("Settings"))
            openSettings(mainApp);
        else
            openSoup(mainApp);
    }
    /**************************************************************************\
     * This method open a new window edit the programs settings
     * @param mainApp mainApp window.
    \**************************************************************************/
    private void openSettings(MainApp mainApp) {
        Settings settings = new Settings();
        settingsController.openSettings(mainApp, settings);
    }

    
}
