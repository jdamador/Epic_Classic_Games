/** **************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
 * \*************************************************************************** */
package codeapp.controller;

import codeapp.model.Orientacion;
import codeapp.model.Palabra;
import codeapp.view.WordsSoup;
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
    private WordsSoup lettersSoup;
    private ArrayList<String> allWords;
    public JLabel[][] soup = new JLabel[15][15];
    private JLabel[] lettersToSearch = new JLabel[30];
    private List<Palabra>palabras = new ArrayList<Palabra>();;
    /**
     * *********************************
     */
    int index = 0;

    public void openSoup(MainApp mainApp, WordsSoup lettersSoup, ArrayList<String> list) {
        this.mainApp = mainApp;
        this.lettersSoup = lettersSoup;
        this.lettersSoup.setVisible(true);
        this.mainApp.setVisible(false);
        allWords = list;
        addActionListener();
        cargar();
    }

    public void cargar() {
        palabras.clear();
        index = 0;
        createLetters();
        createWords();
        colocarPalabras();
        espaciosVacios();

    }

    public void createLetters() {
        lettersSoup.pnLetters.removeAll();
        for (int i = 0; i < 15; i++) {
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
    }

    public void createWords() {
        lettersSoup.pnWords.removeAll();
        for (int j = 0; j < 30; j++) {
            if (index < allWords.size()) {
                lettersToSearch[j] = new JLabel(allWords.get(index), javax.swing.SwingConstants.CENTER);
                index++;
                lettersToSearch[j].setBackground(Color.white);
                lettersToSearch[j].setFont(new Font("Hack", 1, 14));
                lettersToSearch[j].setForeground(new Color(0, 5, 2));
                lettersToSearch[j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                lettersToSearch[j].setOpaque(true);
                lettersToSearch[j].setBorder(new LineBorder(Color.black, 1));
            } else {
                lettersToSearch[j] = new JLabel("", javax.swing.SwingConstants.CENTER);
                lettersToSearch[j].setEnabled(false);
            }

            lettersSoup.pnWords.add(lettersToSearch[j]);
        }

    }

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
            cargar();
        } else if (button.getText().equals("Auto Solve")) {
            autosolve();
        }
    }

    private void addActionListener() {
        lettersSoup.btnBack.addActionListener(this);
        lettersSoup.btnNewGame.addActionListener(this);
        lettersSoup.btnAutoSave.addActionListener(this);
    }

    private void pressedClick(MouseEvent evt) {
        
            if (evt.getComponent().getBackground().equals(Color.white)) {
                evt.getComponent().setBackground(Color.BLUE);
                 tachar();
            } else if (evt.getComponent().getName().equals("")) {
                evt.getComponent().setBackground(Color.white);
            }
        
    }

    private void colocarPalabras() {
        int palabrasGeneradas = 0;
        while (palabrasGeneradas < index) {
            int rndLinea = (int) (Math.random() * 15);
            int rndCol = (int) (Math.random() * 15);
            int rndOrientacion = (int) (Math.random() * Orientacion.ORIENTACION.length);
            String palabra = allWords.get((int) (Math.random() * index));

            if (palabraValida(rndLinea, rndCol, rndOrientacion, palabra)) {
                Palabra pl = new Palabra(rndLinea, rndCol, rndOrientacion, palabra, this);
                palabras.add(pl);
                pl.meterEnTablero();

                palabrasGeneradas++;
            }

        }
    }

    public void autosolve() {
        for (int i = 0; i < soup.length; i++) {
            for (int j = 0; j < soup[i].length; j++) {
                if (!soup[i][j].getName().equals("") && soup[i][j].getBackground().equals(Color.white)) {
                    soup[i][j].setBackground(Color.yellow);
                }
            }
        }
    }

    private void espaciosVacios() {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (soup[i][j].getText().equals("")) {
                    soup[i][j].setText("" + alphabet[(int) (random.nextDouble() * alphabet.length - 1)]);
                }
            }
        }
    }

    private boolean palabraValida(int linea, int col, int orientacion, String palabra) {
        if (palabraUsada(palabra)) {
            return false;
        }
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            switch (orientacion) {
                case Orientacion.HORIZONTAL:
                    if (!letraValida(linea, col + i, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.VERTICAL:
                    if (!letraValida(linea + i, col, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.DIAGONAL_DRC:
                    if (!letraValida(linea + i, col + i, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.DIAGONAL_IZQ:
                    if (!letraValida(linea + i, col - i, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.INV_HORIZONTAL:
                    if (!letraValida(linea, col - i, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.INV_VERTICAL:
                    if (!letraValida(linea - i, col, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.INV_DIAGONAL_DRC:
                    if (!letraValida(linea - i, col + i, letra)) {
                        return false;
                    }
                    break;
                case Orientacion.INV_DIAGONAL_IZQ:
                    if (!letraValida(linea - i, col - i, letra)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }

        return true;
    }

    private boolean letraValida(int linea, int col, char letra) {
        if (linea >= 15 || col >= 15 || linea < 0 || col < 0) {
            return false;
        }
        if (soup[linea][col].getText() != "" && !soup[linea][col].getText().equals(letra)) {
            return false;
        }
        return true;
    }

    private boolean palabraUsada(String palabra) {
        for (Palabra pl : palabras) {
            if (pl.getPalabra().equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    private void tachar() {
        
    }
}
