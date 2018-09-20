/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.controller;
import codeapp.model.Letter;
import codeapp.view.MainApp;
import codeapp.view.Settings;
import codeapp.view.WordsSoup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Settings_Controller implements ActionListener{
   private MainApp mainApp;
   private Settings settings;
   public void openSettings(MainApp mainApp, Settings settings) {
       this.mainApp=mainApp;
       this.settings=settings;
       this.settings.setVisible(true);
       this.mainApp.setVisible(false);
    }
   public void goBack(){
        mainApp.setVisible(true);
        settings.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushed= (JButton) e.getSource(); 
        if(pushed.getText().equals("Back"))
          goBack();
        else if(pushed.getText().equals("AddHang"))
            addNewWord();
        else if(pushed.getText().equals("CleanHang"))
            clear();
      }
    
    private void setActionListener() {
        settings.btnBack.addActionListener(this);
        settings.btnCleanHangman.addActionListener(this);
        settings.btnAddHangman.addActionListener(this);
    }

    private void addNewWord() {
       
    }

    private void clear() {
        
    }
}
