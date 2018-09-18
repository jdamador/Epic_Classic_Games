/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeapp.model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author jushuu
 */
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
