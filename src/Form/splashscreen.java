/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import javax.swing.JOptionPane;

/**
 *
 * @author M. AHSAN
 */
public class splashscreen extends javax.swing.JFrame {

    /**
     * Creates new form splashscreen
     */
    public splashscreen() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scloadingbar = new javax.swing.JProgressBar();
        sctxload = new javax.swing.JLabel();
        sctxprogress = new javax.swing.JLabel();
        sctxtunggu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 200, 200));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        scloadingbar.setBackground(new java.awt.Color(255, 255, 255));
        scloadingbar.setForeground(new java.awt.Color(175, 5, 30));
        getContentPane().add(scloadingbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 340, 30));

        sctxload.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        sctxload.setForeground(new java.awt.Color(255, 255, 255));
        sctxload.setText("Loading...");
        getContentPane().add(sctxload, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 354, 280, 30));

        sctxprogress.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        sctxprogress.setForeground(new java.awt.Color(255, 255, 255));
        sctxprogress.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        sctxprogress.setText("0%");
        getContentPane().add(sctxprogress, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 354, 50, 30));

        sctxtunggu.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 18)); // NOI18N
        sctxtunggu.setForeground(new java.awt.Color(255, 255, 255));
        sctxtunggu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sctxtunggu.setText("Harap tunggu sebentar...");
        getContentPane().add(sctxtunggu, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 484, 350, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(splashscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splashscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splashscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splashscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        splashscreen sc=new splashscreen();
                
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JProgressBar scloadingbar;
    public javax.swing.JLabel sctxload;
    public javax.swing.JLabel sctxprogress;
    public javax.swing.JLabel sctxtunggu;
    // End of variables declaration//GEN-END:variables
}
