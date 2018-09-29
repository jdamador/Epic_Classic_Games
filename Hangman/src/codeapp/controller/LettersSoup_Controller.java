/** **************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
 * \*************************************************************************** */
package codeapp.controller;
import codeapp.model.Orientacion;
import codeapp.model.Word;
import codeapp.view.LettersSoup;
import codeapp.view.MainApp;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class LettersSoup_Controller implements ActionListener {
    private MainApp mainApp;
    private LettersSoup lettersSoup;
    private ArrayList<String> allWords;
    public JLabel[][] soup = new JLabel[15][15];
    private ArrayList<JLabel> lettersToSearch = new ArrayList<>();
    private List<Word> words = new ArrayList<Word>();
    int index = 0;
    /**************************************************************************\
     * This method is in charge of open a new letter's soup window.
     * @param mainApp before window
     * @param lettersSoup new window
    \**************************************************************************/
    public void openSoup(MainApp mainApp, LettersSoup lettersSoup, ArrayList<String> list) {
        this.mainApp = mainApp;
        this.lettersSoup = lettersSoup;
        this.lettersSoup.setVisible(true);
        this.mainApp.setVisible(false);
        allWords = list;
        addActionListener();
        charge();
    }
    /**************************************************************************\
     * Start all methods to play
    \**************************************************************************/
    public void charge() {
        words.clear();
        index = 0;
        lettersToSearch.stream().forEach(s -> s.setBackground(Color.white));
        createLetters();
        createWords();
        putWordsBoard();
        putBlankSpaces();
    }
    /**************************************************************************\
     * Create each letter in the board.
    \**************************************************************************/
    public void createLetters() {
        lettersSoup.pnLetters.removeAll();
        for (int i = 0; i < 15; i++) 
            for (int j = 0; j < 15; j++) {
                soup[i][j] = new JLabel("", javax.swing.SwingConstants.CENTER);
                soup[i][j].setName("");
                soup[i][j].setBackground(Color.white);
                soup[i][j].setFont(new Font("Hack", 1, 14));
                soup[i][j].setForeground(new Color(0, 5, 2));
                soup[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                soup[i][j].setOpaque(true);
                soup[i][j].setBorder(new LineBorder(Color.black, 1));
                soup[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        pressedClick(evt);
                    }
                });
                lettersSoup.pnLetters.add(soup[i][j]);
            }
    }
    /**************************************************************************\
     * Create words to search into the letter's soup.
    \**************************************************************************/
    public void createWords() {
        lettersSoup.pnWords.removeAll();
        for (int j = 0; j < 30; j++) {
            if (index < allWords.size()) {
                lettersToSearch.add(new JLabel(allWords.get(index), javax.swing.SwingConstants.CENTER));
                index++;
                lettersToSearch.get(j).setBackground(Color.white);
                lettersToSearch.get(j).setFont(new Font("Hack", 1, 14));
                lettersToSearch.get(j).setForeground(new Color(0, 5, 2));
                lettersToSearch.get(j).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lettersToSearch.get(j).setOpaque(true);
                lettersToSearch.get(j).setBorder(new LineBorder(Color.black, 1));
                lettersToSearch.get(j).setName("w");
                lettersToSearch.get(j).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                       if(evt.getComponent().getBackground().equals(new Color(Integer.parseInt("3DBE98", 16 ))))
                            evt.getComponent().setBackground(Color.white);
                       else
                            evt.getComponent().setBackground(new Color(Integer.parseInt("3DBE98", 16 )));
                    }
                });
            } else {
                lettersToSearch.add(new JLabel("", javax.swing.SwingConstants.CENTER));
                lettersToSearch.get(j).setEnabled(false);
            }
            
            lettersSoup.pnWords.add(lettersToSearch.get(j));
        }
    }
    /**************************************************************************\
     * This method is in charge of close this window and show the main scream.
    \**************************************************************************/
    public void goBack() {
        mainApp.setVisible(true);
        lettersSoup.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("Back")) {
            goBack();
        } else if (button.getText().equals("New Game")) {
            charge();
        } else if (button.getText().equals("Auto Solve")) {
            autosolve(); 
        }
    }
    /**************************************************************************\
     * This method is in charge of manage the events that happen when a button
     * is pressed.
     * @param e contains the event.
    \**************************************************************************/
    private void addActionListener() {
        lettersSoup.btnBack.addActionListener(this);
        lettersSoup.btnNewGame.addActionListener(this);
        lettersSoup.btnAutoSave.addActionListener(this);
    }
    /**************************************************************************\
     * Event handler in charge of to paint the labels.
    \**************************************************************************/
    private void pressedClick(MouseEvent evt) {
        if (!evt.getComponent().getName().equals("")) {
            evt.getComponent().setBackground(Color.green);
            evt.getComponent().setEnabled(false);
        }else if(evt.getComponent().getBackground().equals(Color.white))
            evt.getComponent().setBackground(Color.blue); 
        else if(evt.getComponent().getName().equals(""))
            evt.getComponent().setBackground(Color.white);
    }
    
    private void crossOut(MouseEvent evt) {
        
    }
    /**************************************************************************\
     * Put each word into into the board.
    \**************************************************************************/
    private void putWordsBoard() {
        int generateWords = 0;
        while (generateWords < index) {
            int rndLine = (int) (Math.random() * 15);
            int rndColum = (int) (Math.random() * 15);
            int rndOrienation = (int) (Math.random() * Orientacion.ORIENTACION.length);
            String word = allWords.get((int) (Math.random() * index));
            if (allowWord(rndLine, rndColum, rndOrienation, word)) {
                Word pl = new Word(rndLine, rndColum, rndOrienation, word, this);
                words.add(pl);
                pl.putIntoBoard();
                generateWords++;
            }
        }
    }
    /**************************************************************************\
     * Paint the elements that was asigned like a letter of some word.
    \**************************************************************************/
    public void autosolve() {
        for (int i = 0; i < soup.length; i++) 
            for (int j = 0; j < soup[i].length; j++) 
                if (!soup[i][j].getName().equals("") && soup[i][j].getBackground().equals(Color.white))
                    soup[i][j].setBackground(Color.yellow);
        lettersToSearch.stream().filter(S-> S.getBackground().equals(Color.white))
                .forEach(s-> s.setBackground(Color.magenta));
    }
    /**************************************************************************\
     * Fill all blank spaces.
    \**************************************************************************/
    private void putBlankSpaces() {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
        Random random = new Random();
        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 15; j++) 
                if (soup[i][j].getText().equals("")) 
                    soup[i][j].setText("" + alphabet[(int) (random.nextDouble() * alphabet.length - 1)]);      
    }
    /**************************************************************************\
     * Check if is allow to put a word in this position.
    \**************************************************************************/
    private boolean allowWord(int linea, int col, int orientacion, String word) {
        if (usedWord(word)) 
            return false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            switch (orientacion) {
                case Orientacion.HORIZONTAL:
                    if (!allowLetter(linea, col + i, letter)) 
                        return false;
                    break;
                case Orientacion.VERTICAL:
                    if (!allowLetter(linea + i, col, letter))
                        return false;
                    break;
                case Orientacion.DIAGONAL_DRC:
                    if (!allowLetter(linea + i, col + i, letter)) 
                        return false;
                    break;
                case Orientacion.DIAGONAL_IZQ:
                    if (!allowLetter(linea + i, col - i, letter))
                        return false;
                    break;
                case Orientacion.INV_HORIZONTAL:
                    if (!allowLetter(linea, col - i, letter))
                        return false;
                    break;
                case Orientacion.INV_VERTICAL:
                    if (!allowLetter(linea - i, col, letter)) 
                        return false;
                    break;
                case Orientacion.INV_DIAGONAL_DRC:
                    if (!allowLetter(linea - i, col + i, letter))
                        return false;
                    break;
                case Orientacion.INV_DIAGONAL_IZQ:
                    if (!allowLetter(linea - i, col - i, letter))
                        return false;
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
    /**************************************************************************\
     * Check is allow to put this letter in these position.
    \**************************************************************************/
    private boolean allowLetter(int line, int col, char letter) {
        if (line >= 15 || col >= 15 || line < 0 || col < 0) 
            return false;
        if (soup[line][col].getText() != "" && !soup[line][col].getText().equals(letter))
            return false;
        return true;
    }
    /*************************************************************************\
     * Check if this word wasn't be used.
    \**************************************************************************/
    private boolean usedWord(String palabra) {
        for (Word pl : words)
            if (pl.getWord().equals(palabra)) 
                return true;
        return false;
    }


}
