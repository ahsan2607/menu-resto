package ujikomahsan;

import Form.*;
import com.formdev.flatlaf.*;
import javax.swing.JOptionPane;

//@author Muhammad Ahsan Raziq Sulthany

public class UjikomAhsan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        splashscreen sc=new splashscreen();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sc.setVisible(true);
            }
        });
        try {
            for(int i=0;i<=110;i++){
                Thread.sleep(25);
                if(i<100){
                    sc.scloadingbar.setValue(i);
                    sc.sctxprogress.setText(i+"%");
                }else if(i>=100){
                    sc.scloadingbar.setValue(100);
                    sc.sctxprogress.setText("100%");
                }
                if(i==10){
                    sc.sctxload.setText("Memulai program...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar.");
                }else if(i==20){
                    sc.sctxload.setText("Mengkoneksikan database...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar..");
                }else if(i==30){
                    sc.sctxload.setText("Menyiapkan menu...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar...");
                }else if(i==40){
                    sc.sctxload.setText("Membereskan meja...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar.");
                }else if(i==50){
                    sc.sctxload.setText("Merekrut pegawai...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar..");
                }else if(i==60){
                    sc.sctxload.setText("Mendaftarkan pengguna...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar...");
                }else if(i==70){
                    sc.sctxload.setText("Melayani pelanggan...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar.");
                }else if(i==80){
                    sc.sctxload.setText("Finalisasi...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar..");
                }else if(i==90){
                    sc.sctxload.setText("Aplikasi hampir siap...");
                    sc.sctxtunggu.setText("Harap tunggu sebentar...");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        formlogin log=new formlogin();
        log.setVisible(true);
        sc.dispose();
    }
    
}
