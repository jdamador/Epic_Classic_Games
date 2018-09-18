/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.model;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Letter extends JButton {
    public char letter;
    public Color unselected=new Color(Integer.parseInt("83bb40", 16 ));
    public Color selected=new Color(Integer.parseInt("e65224", 16 ));
    public Letter(char letter){
        this.setBackground(unselected);
        this.letter=letter;
        this.setText(""+letter);
        setFont(new Font("Hack", Font.BOLD,14 ));
    }
}
