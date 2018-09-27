/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.controller;
import codeapp.view.MainApp;
import codeapp.view.Settings;
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
   private DefaultListModel<String> modelSoup = new DefaultListModel<>();
   /***************************************************************************\
    * Open settings window
    * @param mainApp
    * @param settings
    * @param words 
    \**************************************************************************/
    public void openSettings(MainApp mainApp, Settings settings) {
       this.mainApp=mainApp;
       this.settings=settings;
       this.settings.setVisible(true);
       this.mainApp.setVisible(false);
       modelHangman.clear();
       modelSoup.clear();
       managerMethods();
    }
    /**************************************************************************\
     * Call other methods in their respective order.
    \**************************************************************************/
    public void managerMethods(){
        setActionListener();
        chargeDefault();
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
            newWordHangman();
        else if(pushed.getToolTipText().equals("cleanHang"))
            clear(settings.ltHangman,modelHangman,mainApp.mainApp_Controller.hangmanWords);
        else if(pushed.getToolTipText().equals("addSoup"))
            newWordSoup();
        else if(pushed.getToolTipText().equals("cleanSoup"))
            clear(settings.ltSoup,modelSoup,mainApp.mainApp_Controller.lettesSoupWords);
      }
    /**************************************************************************\
     * Set new action listener to handle events
    \**************************************************************************/
    private void setActionListener() {
        settings.btnBack.addActionListener(this);
        settings.btnCleanHangman.addActionListener(this);
        settings.btnAddHangman.addActionListener(this);
        settings.btnCleanSoup.addActionListener(this);
        settings.btnAddSoup.addActionListener(this);
    }
    /**************************************************************************\
     * Add a new word into the hangman list
     * Before add a new word is evaluate if this new word contains numbers or
     * characters.
    \**************************************************************************/
    private void newWordHangman() {
        String newWord = settings.txtHangman.getText();
        if(newWord.matches("([a-z]|[A-Z]|\\s)+")){
           if(!mainApp.mainApp_Controller.hangmanWords.contains(newWord)){
                mainApp.mainApp_Controller.hangmanWords.add(newWord.toUpperCase());
                modelHangman.addElement(newWord.toUpperCase());
                settings.ltHangman.setModel(modelHangman);
           }else
               JOptionPane.showMessageDialog(settings, "¡Esta palabra ya existe!");
        }else
            JOptionPane.showMessageDialog(settings, "¡Esta palabra contiene números o caracteres especiales!");
    }
    /**************************************************************************\
     * Clean the lists.
    \**************************************************************************/
    private void clear(JList list, DefaultListModel model, ArrayList words) {
        model.clear();
        list.setModel(model);
        words.clear();
    }
    /**************************************************************************\
     * Add a new word into the letter soup list
     * Before add a new word is evaluate if this new word contains numbers or
     * characters.
    \**************************************************************************/
    private void newWordSoup() {
        if(mainApp.mainApp_Controller.lettesSoupWords.size()>30){
            String newWord = settings.txtSoup.getText();
            if(newWord.matches("([a-z]|[A-Z]|\\s)+")){
               if(!mainApp.mainApp_Controller.lettesSoupWords.contains(newWord.toUpperCase())){
                    mainApp.mainApp_Controller.lettesSoupWords.add(newWord.toUpperCase());
                    modelSoup.addElement(newWord);
                    settings.ltSoup.setModel(modelSoup);
               }else
                   JOptionPane.showMessageDialog(settings, "¡Esta palabra ya existe!");
            }else
                JOptionPane.showMessageDialog(settings, "¡Esta palabra contiene números o caracteres especiales!");
        }
    }

    private void chargeDefault() {
        for (int i = 0; i <  mainApp.mainApp_Controller.hangmanWords.size(); i++) {
            modelHangman.addElement(mainApp.mainApp_Controller.hangmanWords.get(i));
        }
        settings.ltHangman.setModel(modelHangman);
        for (int i = 0; i <  mainApp.mainApp_Controller.lettesSoupWords.size(); i++) {
            modelSoup.addElement(mainApp.mainApp_Controller.lettesSoupWords.get(i));
        }
        settings.ltSoup.setModel(modelSoup);
    }
}

