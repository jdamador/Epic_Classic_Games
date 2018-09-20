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
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Settings_Controller implements ActionListener{
   /***************************************************************************\
    * Global variables declaration.
   \***************************************************************************/
   private MainApp mainApp;
   private Settings settings;
   private DefaultListModel<String> modelHangman = new DefaultListModel<>();
   /***************************************************************************\
    * Open settings window
    * @param mainApp
    * @param settings
    * @param words 
    \**************************************************************************/
    public void openSettings(MainApp mainApp, Settings settings, ArrayList<String> words) {
       this.mainApp=mainApp;
       this.settings=settings;
       this.settings.setVisible(true);
       this.mainApp.setVisible(false);
       managerMethods();
    }
    /**************************************************************************\
     * Call other methods in their respective order.
    \**************************************************************************/
    public void managerMethods(){
        setActionListener();
    }
    /**************************************************************************\
     * Close this window and open the befero window.
    \**************************************************************************/
    public void goBack(){
        mainApp.setVisible(true);
        settings.setVisible(false);
    }
    /**************************************************************************\
     * Method that handle of the events that are produced when some button is
     * pressed.
    \**************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushed= (JButton) e.getSource(); 
        if(pushed.getToolTipText().equals("Back"))
          goBack();
        else if(pushed.getToolTipText().equals("addHang"))
            addNewWord();
        else if(pushed.getToolTipText().equals("CleanHang"))
            clear(settings.ltSoup,modelHangman,mainApp.mainApp_Controller.hangmanWords);
      }
    /**************************************************************************\
     * Set new action listener to handle events
    \**************************************************************************/
    private void setActionListener() {
        settings.btnBack.addActionListener(this);
        settings.btnCleanHangman.addActionListener(this);
        settings.btnAddHangman.addActionListener(this);
    }
    /**************************************************************************\
     * Add a new word into the hangman list
     * Before add a new word is evaluate if this new word contains numbers or
     * characters.
    \**************************************************************************/
    private void addNewWord() {
        String newWord = settings.txtHangman.getText();
        if(newWord.matches("([a-z]|[A-Z]|\\s)+")){
           if(!mainApp.mainApp_Controller.hangmanWords.contains(newWord)){
                mainApp.mainApp_Controller.hangmanWords.add(newWord);
                modelHangman.addElement(newWord);
                settings.ltHangman.setModel(modelHangman);
           }
        }else
            JOptionPane.showMessageDialog(settings, "Esta palabra contiene números o caracteres especiales");
    }
    /**************************************************************************\
     * Clean the lists.
    \**************************************************************************/
    private void clear(JList list, DefaultListModel model, ArrayList words) {
        model.clear();
        list.setModel(model);
        words.clear();
    }
}
