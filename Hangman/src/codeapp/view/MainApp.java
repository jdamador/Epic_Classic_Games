/****************************************************************************\
 * Epic Classic Game
 * Author: Jose Daniel Amador Salas
 * This class is in charge of manage the hangman game
 * Create: 17/09/2018
 * Version: 1.0.
\****************************************************************************/
package codeapp.view;
import codeapp.controller.MainApp_Controller;

public class MainApp extends javax.swing.JFrame {

    /**
     * Creates new form MainApp
     */
    public MainApp() {
        initComponents();
        mainApp_Controller = new MainApp_Controller(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnSoup = new javax.swing.JButton();
        btnHangman = new javax.swing.JButton();
        lblLettersSoup = new javax.swing.JLabel();
        lblHangman = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Hack", 3, 65)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(246, 246, 225));
        lblTitle.setText("Epic Classic Games");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        btnSoup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codeapp/util/letters_soup/letters soup.jpg"))); // NOI18N
        btnSoup.setToolTipText("letterSoup");
        btnSoup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoupActionPerformed(evt);
            }
        });
        getContentPane().add(btnSoup, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 270, 340, 340));

        btnHangman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codeapp/util/hangman/icon_hangman.png"))); // NOI18N
        btnHangman.setToolTipText("Hangman");
        btnHangman.setName(""); // NOI18N
        btnHangman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangmanActionPerformed(evt);
            }
        });
        getContentPane().add(btnHangman, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 340, 340));

        lblLettersSoup.setFont(new java.awt.Font("Hack", 2, 36)); // NOI18N
        lblLettersSoup.setForeground(new java.awt.Color(248, 248, 238));
        lblLettersSoup.setText("Letters Soup");
        getContentPane().add(lblLettersSoup, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 610, -1, -1));

        lblHangman.setFont(new java.awt.Font("Hack", 2, 36)); // NOI18N
        lblHangman.setForeground(new java.awt.Color(248, 248, 238));
        lblHangman.setText("Hangman");
        getContentPane().add(lblHangman, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 600, -1, -1));

        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codeapp/util/settingsIIcon.gif"))); // NOI18N
        btnSettings.setToolTipText("Settings");
        getContentPane().add(btnSettings, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, 80, 80));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codeapp/util/background.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSoupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSoupActionPerformed

    private void btnHangmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangmanActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnHangmanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnHangman;
    public javax.swing.JButton btnSettings;
    public javax.swing.JButton btnSoup;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblHangman;
    private javax.swing.JLabel lblLettersSoup;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
    
    /************************************************************\
     * Controller instances
    \************************************************************/
    public static MainApp_Controller mainApp_Controller;
   
}
