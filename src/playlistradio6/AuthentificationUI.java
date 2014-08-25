/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlistradio6;

import CrudPanels.ConfigurationServeur;
import CrudPanels.Utilisateur;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author moez
 */
public class AuthentificationUI extends javax.swing.JFrame {

    /**
     * Creates new form AuthentificationUI
     */
    public AuthentificationUI() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PersistanceUnit").createEntityManager();
        query1 = java.beans.Beans.isDesignTime() ? null : entityManager1.createNamedQuery("Utilisateur.findByLogin");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        passe = new javax.swing.JPasswordField();
        boutonOK = new javax.swing.JButton();
        boutonQuitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Radio6 -- Connexion");
        setIconImage(new ImageIcon(getClass().getResource("/logofr.png")).getImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Connexion");

        jLabel2.setText("Login :");

        jLabel3.setText("Mot de passe :");

        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginKeyPressed(evt);
            }
        });

        passe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passeActionPerformed(evt);
            }
        });
        passe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passeKeyPressed(evt);
            }
        });

        boutonOK.setText("OK");
        boutonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonOKActionPerformed(evt);
            }
        });

        boutonQuitter.setText("Quitter");
        boutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonQuitterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(passe, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(boutonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(boutonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boutonQuitter)
                    .addComponent(boutonOK))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boutonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonOKActionPerformed
        // selection suivant le login
        query1.setParameter("login", login.getText());
        try {
            Utilisateur u = (Utilisateur) query1.getSingleResult();
            // si le mot de passe est correct
            if (u.getPass().equals(String.valueOf(passe.getPassword()))) {
                // on passe à la fenêtre suivante
                this.setVisible(false);

                Properties prop = new Properties();
                InputStream in = null;

                try {
                    File f = new File("playlist.conf");
                    if (f.exists()) {
                        in = new FileInputStream("playlist.conf");
                        // chargement de la configuration
                        prop.load(in);
                        if (prop.containsKey("music_folder")) {
                            // si le chemin de serveur est deja configure passer
                            GestionPlaylistUI fen = new GestionPlaylistUI();
                            fen.setLocationRelativeTo(null);
                            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            fen.setSize(fen.getToolkit().getScreenSize());
                            fen.validate();
                            fen.setVisible(true);
                        } else {
                            // configurer le serveur
                            ConfigurationServeur fen = new ConfigurationServeur();
                            fen.setLocationRelativeTo(null);
                            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            fen.pack();
                            fen.setVisible(true);
                        }
                    } else {
                        // configurer le serveur
                        ConfigurationServeur fen = new ConfigurationServeur();
                        fen.setLocationRelativeTo(null);
                        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        fen.pack();
                        fen.setVisible(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Veuiller vérifier vos informations personnelles !", "Erreur", JOptionPane.ERROR_MESSAGE);
                login.setText("");
                passe.setText("");
                login.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Veuiller vérifier vos informations personnelles !", "Erreur", JOptionPane.ERROR_MESSAGE);
            login.setText("");
            passe.setText("");
            login.requestFocus();
        }

    }//GEN-LAST:event_boutonOKActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed

    }//GEN-LAST:event_loginActionPerformed

    private void boutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonQuitterActionPerformed
        // quitter le programme
        System.exit(0);
    }//GEN-LAST:event_boutonQuitterActionPerformed

    private void passeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passeActionPerformed

    }//GEN-LAST:event_passeActionPerformed

    private void loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginKeyPressed
        if (evt.getKeyChar() == '\n') {
            java.awt.event.ActionEvent event = new ActionEvent(login, 143, "click bouton");
            boutonOKActionPerformed(event);
        }
    }//GEN-LAST:event_loginKeyPressed

    private void passeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passeKeyPressed
        if (evt.getKeyChar() == '\n') {
            java.awt.event.ActionEvent event = new ActionEvent(passe, 123, "click bouton 2");
            boutonOKActionPerformed(event);
        }
    }//GEN-LAST:event_passeKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AuthentificationUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonOK;
    private javax.swing.JButton boutonQuitter;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField login;
    private javax.swing.JPasswordField passe;
    private javax.persistence.Query query1;
    // End of variables declaration//GEN-END:variables
}
