//<editor-fold defaultstate="collapsed" desc="Source untuk Form Login pada Aplikasi Restoran untuk Ujikom tahun 2023; Author: Muhammad Ahsan Raziq Sulthany">
package Form;

import Class.*;
import java.sql.*;
import static javafx.application.Platform.exit;
import javax.swing.*;

//@author Muhammad Ahsan Raziq Sulthany

public class formlogin extends javax.swing.JFrame {

    public formlogin() {
        initComponents();
        setLocationRelativeTo(this);
    }
    private void login(){
        Connection c = new conn().connDb();
        String sql = "select * from tbpgn where usnmpgn=? and pwpgn=?";
        try{
            PreparedStatement p = conn.connDb().prepareStatement(sql);
            p.setString(1, txusnmpgn.getText());
            String strpwpgn = new String(txpwpgn.getPassword());
            p.setString(2, strpwpgn);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                session.setidpgn(rs.getString("idpgn"));
                session.setnmpgn(rs.getString("nmpgn"));
                session.setusnmpgn(rs.getString("usnmpgn"));
                session.setpwpgn(rs.getString("pwpgn"));
                session.setlvlpgn(rs.getString("lvlpgn"));
                this.dispose();
                JOptionPane.showMessageDialog(null, "Berhasil Login");
                new formmaster().setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password salah");
                txusnmpgn.setText("");
                txpwpgn.setText("");
                txusnmpgn.requestFocus();
            }
        }catch(Exception e){
            
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txusnmpgn = new javax.swing.JTextField();
        txpwpgn = new javax.swing.JPasswordField();
        btlogin = new javax.swing.JButton();
        btout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 200, 200));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOGIN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 400, 30));

        txusnmpgn.setBorder(null);
        txusnmpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txusnmpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 260, 30));

        txpwpgn.setBorder(null);
        txpwpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txpwpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 260, 30));

        btlogin.setBackground(new java.awt.Color(175, 5, 30));
        btlogin.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlogin.setForeground(new java.awt.Color(255, 255, 255));
        btlogin.setText("Log in");
        btlogin.setBorder(null);
        btlogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btloginActionPerformed(evt);
            }
        });
        jPanel1.add(btlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 140, 40));

        btout.setBackground(new java.awt.Color(175, 5, 30));
        btout.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btout.setForeground(new java.awt.Color(255, 255, 255));
        btout.setText("Keluar");
        btout.setBorder(null);
        btout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btoutActionPerformed(evt);
            }
        });
        jPanel1.add(btout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 140, 40));

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 80, 30));

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 80, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg2.png"))); // NOI18N
        jLabel4.setText(" ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btloginActionPerformed
        login();
    }//GEN-LAST:event_btloginActionPerformed

    private void btoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar dari aplikasi?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
        System.exit(0);
        }else{
            
        }
    }//GEN-LAST:event_btoutActionPerformed

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
            java.util.logging.Logger.getLogger(formlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlogin;
    private javax.swing.JButton btout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txpwpgn;
    private javax.swing.JTextField txusnmpgn;
    // End of variables declaration//GEN-END:variables
}
//</editor-fold>