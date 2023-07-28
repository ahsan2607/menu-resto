//<editor-fold desc="Source untuk Form Master pada Aplikasi Restoran untuk Ujikom tahun 2023; Author: Muhammad Ahsan Raziq Sulthany">
package Form;

import Class.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import java.io.File;
import java.lang.reflect.Parameter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

//@author Muhammad Ahsan Raziq Sulthany

public class formmaster extends javax.swing.JFrame {
    //<editor-fold defaultstate="collapsed" desc="Kode untuk tampilan JFrame">
    //Menentukan Tanggal Sekarang
    java.util.Date tglnow = new java.util.Date();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private String tgl = format.format(tglnow);
    
    private DefaultTableModel mdmn, mdmj, mdpgw, mdplg, mdpgn, mdpgwi, mdplgtrs, mdmjtrs, mdmntrs, mddtr, mdtrs;
    
    String idmn, nmmn, ktgmn, hrgmn, stkmn,//variabel menu
           idmj, nmmj, ktgmj, sttsmj,//variabel meja
           idpgw, nmpgw, sxpgw, tmptlhrpgw, tgllhrpgw, almtpgw, nohppgw, jobpgw,//variabel pegawai
           idplg, nmplg, sxplg, tmptlhrplg, tgllhrplg, almtplg, nohpplg,//variabel pelanggan
           idpgn, nmpgn, usnmpgn, pwpgn, lvlpgn, idpgwpgn,//variabel pengguna
           idtrs, iddtr, idtrsdtr, tgltrs, idpgntrs, idplgtrs, idmjtrs, subttltrs, ttlbyrtrs, byrtrs, kmbltrs, idmndtr, hrgdtr, jmlhdtr, hrgttldtr, hrgttltrs, tgtrs, wktrs,
           tglawal, tglakhir;
    
           Calendar tglskrg = Calendar.getInstance();
           Calendar tgllalu = Calendar.getInstance();
    public formmaster() {
        this.setTitle("Aplikasi Restoran - Ujikom Muhammad Ahsan Raziq Sulthany 2023");
        initComponents();
        tgllalu.add(Calendar.DATE, -1);
        jdc1.setCalendar(tgllalu);
        jdc2.setCalendar(tglskrg);
        
        //Set Teks Label Beranda
        setjam(lbwkthm);
        lbwelcome.setText("Selamat Datang "+nmpgnlog+"!");
        lbnmpgnhm.setText(nmpgnlog);
        lbusnmpgnhm.setText(usnmpgnlog);
        lblvlpgnhm.setText(lvlpgnlog);
        lbtglhm.setText(tgl);
        
        //Nonaktifasi Form
        nonactmn(); nonactmj(); nonactpgw(); nonactplg(); nonactpgn(); nonacttrs();
        
        //Modifikasi Tabel Menu
        mdmn = new DefaultTableModel(); tbmn.setModel(mdmn);
        mdmn.addColumn("Id"); mdmn.addColumn("Nama"); mdmn.addColumn("Kategori"); mdmn.addColumn("Harga");
        mdmn.addColumn("Stok");
        
        //Modifikasi Tabel Meja
        mdmj = new DefaultTableModel(); tbmj.setModel(mdmj);
        mdmj.addColumn("Id"); mdmj.addColumn("Nomor"); mdmj.addColumn("Kategori"); mdmj.addColumn("Status");
        
        //Modifikasi Tabel Pegawai
        mdpgw = new DefaultTableModel(); tbpgw.setModel(mdpgw);
        mdpgw.addColumn("Id"); mdpgw.addColumn("Nama"); mdpgw.addColumn("Kelamin"); mdpgw.addColumn("Tempat Lahir");
        mdpgw.addColumn("Tanggal Lahir"); mdpgw.addColumn("Alamat"); mdpgw.addColumn("No. Telp"); mdpgw.addColumn("Pekerjaan");
        
        //Modifikasi Tabel Pelanggan
        mdplg = new DefaultTableModel(); tbplg.setModel(mdplg);
        mdplg.addColumn("Id"); mdplg.addColumn("Nama"); mdplg.addColumn("Kelamin"); mdplg.addColumn("Tempat Lahir");
        mdplg.addColumn("Tanggal Lahir"); mdplg.addColumn("Alamat"); mdplg.addColumn("No. Telp");
        
        //Modifikasi Tabel Pengguna
        mdpgn = new DefaultTableModel(); tbpgn.setModel(mdpgn);
        mdpgn.addColumn("Id"); mdpgn.addColumn("Id Pegawai"); mdpgn.addColumn("Nama"); mdpgn.addColumn("Username");
        mdpgn.addColumn("Password"); mdpgn.addColumn("Level");
        
        //Modifikasi Tabel Pegawai Form Pengguna
        mdpgwi = new DefaultTableModel(); tbpgwi.setModel(mdpgwi);
        mdpgwi.addColumn("Id"); mdpgwi.addColumn("Nama"); mdpgwi.addColumn("Kelamin"); mdpgwi.addColumn("Tempat Lahir");
        mdpgwi.addColumn("Tanggal Lahir"); mdpgwi.addColumn("Alamat"); mdpgwi.addColumn("No. Telp"); mdpgwi.addColumn("Pekerjaan");

        //Modifikasi Tabel Pelanggan Form Transaksi
        mdplgtrs = new DefaultTableModel(); tbplgtrs.setModel(mdplgtrs);
        mdplgtrs.addColumn("Id"); mdplgtrs.addColumn("Nama"); mdplgtrs.addColumn("Kelamin"); mdplgtrs.addColumn("Tempat Lahir");
        mdplgtrs.addColumn("Tanggal Lahir"); mdplgtrs.addColumn("Alamat"); mdplgtrs.addColumn("No. Telp");
        
        //Modifikasi Tabel Meja Form Transaksi
        mdmjtrs = new DefaultTableModel(); tbmjtrs.setModel(mdmjtrs);
        mdmjtrs.addColumn("Id"); mdmjtrs.addColumn("Nomor"); mdmjtrs.addColumn("Kategori"); mdmjtrs.addColumn("Status");
        
        //Modifikasi Tabel Menu Form Transaksi
        mdmntrs = new DefaultTableModel(); tbmntrs.setModel(mdmntrs);
        mdmntrs.addColumn("Id"); mdmntrs.addColumn("Nama"); mdmntrs.addColumn("Kategori"); mdmntrs.addColumn("Harga");
        mdmntrs.addColumn("Stok");
        
        //Modifikasi Tabel Pesanan Form Transaksi
        mddtr = new DefaultTableModel(); tbdtr.setModel(mddtr);
        mddtr.addColumn("Id Pesanan"); mddtr.addColumn("Id Transaksi"); mddtr.addColumn("Id Menu"); mddtr.addColumn("Nama Menu"); mddtr.addColumn("Jumlah");
        mddtr.addColumn("Harga"); mddtr.addColumn("Total");
        
        //Modifikasi Tabel Transaksi
        mdtrs = new DefaultTableModel(); tbtrs.setModel(mdtrs);
        mdtrs.addColumn("Id Transaksi"); mdtrs.addColumn("Tanggal"); mdtrs.addColumn("Id Pengguna"); mdtrs.addColumn("Id Pelanggan"); mdtrs.addColumn("Id Meja");
        mdtrs.addColumn("Harga Total"); mdtrs.addColumn("Total Bayar"); mdtrs.addColumn("Bayar"); mdtrs.addColumn("Kembalian");
        
        //Id Otomatis, Auto Increment, dan Otomatisasi Data
        idautomn(); idautomj(); idautopgw(); idautoplg(); idautopgn(); autoincmj();
        txtgltrs.setText(tgl);
        txnmpgntrs.setText(nmpgnlog);
        txidpgntrs.setText(idpgnlog);
        settxjam(txwkttrs);
        
        //Mengambil Data dari Database untuk Ditampilkan di Tabel
        getmn(mdmn, 0, ">="); getmn(mdmntrs, 1, ">="); getmj(mdmj); getpgw(mdpgw); getpgw(mdpgwi); getplg(mdplg); getpgn(); getmjtrs(mdmjtrs); getplg(mdplgtrs); gettrs();
        
        //Hak Akses
        if (lvlpgnlog.equalsIgnoreCase("Administrator")) {
            admin();
        } else if (lvlpgnlog.equalsIgnoreCase("Kasir")) {
            kasir();
        } else if (lvlpgnlog.equalsIgnoreCase("Pelayan")) {
            pelayan();
        }
    }
    //</editor-fold>
    
    //AWAL FUNGSI MINOR
    //<editor-fold defaultstate="collapsed" desc="Method untuk transisi tampilan minor">
    
    //Nonaktif
    //<editor-fold defaultstate="collapsed">
    public void btnoff(JButton... args){
        for (JButton arg : args){
            arg.setEnabled(false);
        }
    }
    public void tboff(JTable... args){
        for (JTable arg : args){
            arg.setEnabled(false);
        }
    }
    public void txoff(JTextField... args){
        for (JTextField arg : args){
            arg.setEnabled(false);
        }
    }
    public void cboff(JComboBox... args){
        for (JComboBox arg : args){
            arg.setEnabled(false);
        }
    }
    public void tbunsel(JTable... args){
        for (JTable arg : args){
            arg.setFocusCycleRoot(false);
        }
    }
    //</editor-fold>
    //Aktif
    //<editor-fold defaultstate="collapsed">
    public void txon(JTextField... args){
        for (JTextField arg : args){
            arg.setEnabled(true);
        }
    }
    public void cbon(JComboBox... args){
        for (JComboBox arg : args){
            arg.setEnabled(true);
        }
    }
    public void btnon(JButton... args){
        for (JButton arg : args){
            arg.setEnabled(true);
        }
    }
    //</editor-fold>
    //Kosongkan
    //<editor-fold defaultstate="collapsed">
    public void txempty(JTextField... args){
        for (JTextField arg :  args){
            arg.setText("");
        }
    }
    public void cbempty(JComboBox... args){
        for (JComboBox arg :  args){
            arg.setSelectedIndex(0);
        }
    }
    //</editor-fold>
    //Placeholder
    //<editor-fold defaultstate="collapsed">
    public void clickplaceholder(JTextField tx, String text){
        if(tx.getText().equals(text)){
            tx.setText("");
            tx.setForeground(new Color(0,0,0));
        }
    }
    public void setplaceholder(JTextField tx, String text){
        if(tx.getText().equals("")){
            tx.setText(text);
            tx.setForeground(new Color(127,127,127));
        }
    }
    //</editor-fold>
    //Seleksi Sidepanel
    //<editor-fold defaultstate="collapsed">
    public void setbg(JPanel panel, JPanel panelsel){
        panel.setBackground(new Color(220,65,100));
        panelsel.setBackground(new Color(255, 255, 255));
    }
    public void resetbg(JPanel panel, JPanel panelsel){
        panel.setBackground(new Color(175,5,30));
        panelsel.setBackground(new Color(175,5,30));
    }
    public void resetmenu(JPanel panel, JPanel panel1, JPanel panel2, JPanel panel3, JPanel panel4, JPanel panel5, JPanel panel6, JPanel panel7, JPanel panel8, JPanel panel9, JPanel panel10, JPanel panel11, JPanel panel12, JPanel panel13){
        resetbg(panel, panel7);
        resetbg(panel1, panel8);
        resetbg(panel2, panel9);
        resetbg(panel3, panel10);
        resetbg(panel4, panel11);
        resetbg(panel5, panel12);
        resetbg(panel6, panel13);
    }
    //</editor-fold>
    //Limitasi Teks
    //<editor-fold defaultstate="collapsed">
    public void txnumb(java.awt.event.KeyEvent evt){
        char input = evt.getKeyChar();
        if(!(((Character.isDigit(input)) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)))){
        getToolkit().beep();
        evt.consume();}  
    }
    public void txlimit(java.awt.event.KeyEvent evt, JTextField limited, Integer limit){
        char input = evt.getKeyChar();
        if((limited.getText().length() >= limit) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
        getToolkit().beep();
        evt.consume();}  
    }
    public void talimit(java.awt.event.KeyEvent evt, JTextArea limited, Integer limit){
        char input = evt.getKeyChar();
        if((limited.getText().length() >= limit) || (input == KeyEvent.VK_BACK_SPACE) || (input == KeyEvent.VK_DELETE)){
        getToolkit().beep();
        evt.consume();}  
    }
    //</editor-fold>
    
    //</editor-fold>
    //AKHIR FUNGSI MINOR
    
    //AWAL FUNGSI BERANDA
    //<editor-fold defaultstate="collapsed" desc="Method untuk tampilan beranda">
    
    //Menjalankan Fungsi di Session
    //<editor-fold defaultstate="collapsed">
    private String idpgnlog = session.getidpgn();
    private String nmpgnlog = session.getnmpgn();
    private String usnmpgnlog = session.getusnmpgn();
    private String pwpgnlog = session.getpwpgn();
    private String lvlpgnlog = session.getlvlpgn();
    //</editor-fold>
    //Hak Akses
    //<editor-fold defaultstate="collapsed">
    public void admin(){
    }
    public void kasir(){
        btnoff(btadmn, btadmj, btadpgw, btadpgn, btlmn, btlmj, btlpgw, btlpgn);
        tboff(tbmn, tbmj, tbpgw, tbpgn, tbpgwi);
        txoff(txsrcmn, txsrcmj, txsrcpgw, txsrcpgn, txsrcpgwi);
        txsrcmn.setText("MNU");
        txsrcmj.setText("MJA");
        txsrcpgw.setText("PGW");
        txsrcpgn.setText("PGN");
        txsrcpgwi.setText("PGW");
    }
    public void pelayan(){
        btnoff(btadmn, btadplg, btadpgw, btadpgn, btlmn, btlplg, btlpgw, btlpgn, btadtrs, btendtrs, btprinttrs1);
        tboff(tbmn, tbplg, tbpgw, tbpgn, tbpgwi, tbplgtrs, tbmjtrs, tbmntrs, tbdtr);
        txoff(txsrcmn, txsrcplg, txsrcpgw, txsrcpgn, txsrcpgwi, txsrcmntrs, txsrcmjtrs, txsrcplgtrs);
        txsrcmn.setText("MNU");
        txsrcplg.setText("PLG");
        txsrcpgw.setText("PGW");
        txsrcpgn.setText("PGN");
        txsrcpgwi.setText("PGW");
        txsrcmjtrs.setText("MJA");
        txsrcplgtrs.setText("PLG");
        txsrcmntrs.setText("MNU");
    }
    //</editor-fold>
    //Menentukan Waktu Sekarang
    //<editor-fold defaultstate="collapsed">
    public final void setjam(JLabel label) {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String ojm = "", omnt = "", odtk = "";

                java.util.Date datetime = new java.util.Date();
                int njm = datetime.getHours();
                int nmnt = datetime.getMinutes();
                int ndtk = datetime.getSeconds();
                
                if(njm <= 9) ojm = "0";
                if(nmnt <= 9) omnt = "0";
                if(ndtk <= 9) odtk = "0";
                
                String jm = ojm + Integer.toString(njm);
                String mnt = omnt + Integer.toString(nmnt);
                String dtk = odtk + Integer.toString(ndtk);
                
                label.setText(jm+":"+mnt+":"+dtk+"");
            }
        };
        new javax.swing.Timer(1000, taskPerformer).start();
    }
    //</editor-fold>
    
    //</editor-fold>
    //AKHIR FUNGSI BERANDA
    
    //AWAL FUNGSI OTOMATISASI DATA
    //<editor-fold defaultstate="collapsed" desc="Method untuk input data yang dibuat otomatis">
    
    //Auto Increment
    //<editor-fold defaultstate="collapsed">
    public void autoincmj(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj order by nmmj desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                Integer id = rs.getInt("nmmj");
                String AN = ""+(id+1);
                txnmmj.setText(AN);
            }else{
                txnmmj.setText("1");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    //</editor-fold>
    //Id Otomatis
    //<editor-fold defaultstate="collapsed">
    public void idautomn(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmn order by idmn desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idmn").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00";}
                else if(AN.length()==2){o = "0";}
                else if(AN.length()==3){o = "";}
                txidmn.setText("MNU"+o+AN);
            }else{
                txidmn.setText("MNU001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void idautotrs(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbtrs order by idtrs desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idtrs").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00000";}
                else if(AN.length()==2){o = "0000";}
                else if(AN.length()==3){o = "000";}
                else if(AN.length()==4){o = "00";}
                else if(AN.length()==5){o = "0";}
                else if(AN.length()==6){o = "";}
                txidtrs.setText("TRS"+o+AN);
            }else{
                txidtrs.setText("TRS000001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void idautomj(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj order by idmj desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idmj").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00";}
                else if(AN.length()==2){o = "0";}
                else if(AN.length()==3){o = "";}
                txidmj.setText("MJA"+o+AN);
            }else{
                txidmj.setText("MJA001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void idautopgw(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgw order by idpgw desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idpgw").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00";}
                else if(AN.length()==2){o = "0";}
                else if(AN.length()==3){o = "";}
                txidpgw.setText("PGW"+o+AN);
            }else{
                txidpgw.setText("PGW001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void idautoplg(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbplg order by idplg desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idplg").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00";}
                else if(AN.length()==2){o = "0";}
                else if(AN.length()==3){o = "";}
                txidplg.setText("PLG"+o+AN);
            }else{
                txidplg.setText("PLG001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void idautopgn(){
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgn order by idpgn desc";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString("idpgn").substring(3);
                String AN = ""+(Integer.parseInt(id)+1);
                String o = "";
                if(AN.length()==1){o = "00";}
                else if(AN.length()==2){o = "0";}
                else if(AN.length()==3){o = "";}
                txidpgn.setText("PGN"+o+AN);
            }else{
                txidpgn.setText("PGN001");
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    //</editor-fold>
    //Otomatisasi Waktu
    //<editor-fold defaultstate="collapsed">
    public final void settxjam(JTextField tx) {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String ojm = "", omnt = "", odtk = "";

                java.util.Date datetime = new java.util.Date();
                int njm = datetime.getHours();
                int nmnt = datetime.getMinutes();
                int ndtk = datetime.getSeconds();
                
                if(njm <= 9) ojm = "0";
                if(nmnt <= 9) omnt = "0";
                if(ndtk <= 9) odtk = "0";
                
                String jm = ojm + Integer.toString(njm);
                String mnt = omnt + Integer.toString(nmnt);
                String dtk = odtk + Integer.toString(ndtk);
                
                tx.setText(jm+":"+mnt+":"+dtk+"");
            }
        };
        new javax.swing.Timer(1000, taskPerformer).start();
    }
    
    //</editor-fold>
    
    //</editor-fold>
    //AKHIR FUNGSI OTOMATISASI DATA
    
    //AWAL FUNGSI AKTIFASI, NONAKTIFASI FORM, DAN KOSONGKAN FORM
    //<editor-fold defaultstate="collapsed" desc="Method untuk aktifasi dan nonaktifasi komponen di form">
    
    //Aktifasi Form
    //<editor-fold defaultstate="collapsed">
    public void actmn(){
        txon(txnmmn, txhrgmn, txstkmn);
        cbon(cbktgmn);
        btnon(btadmn, btsvmn, btedmn, btdemn);
        btadmn.setEnabled(false);
    }
    public void actmj(){
        cbon(cbktgmj, cbsttsmj);
        btnon(btadmj, btsvmj, btedmj, btdemj);
        btadmj.setEnabled(false);
    }
    public void actpgw(){
        txon(txnmpgw, txtmptlhrpgw, txnohppgw);
        cbon(cbsxpgw, cbjobpgw);
        btnon(btadpgw, btsvpgw, btedpgw, btdepgw);
        txalmtpgw.setEnabled(true);
        jdctgllhrpgw.setEnabled(true);
        btadpgw.setEnabled(false);
    }
    public void actplg(){
        txon(txnmplg, txtmptlhrplg, txnohpplg);
        cbon(cbsxplg);
        btnon(btadplg, btsvplg, btedplg, btdeplg);
        txalmtplg.setEnabled(true);
        jdctgllhrplg.setEnabled(true);
        btadplg.setEnabled(false);
    }
    public void actpgn(){
        txon(txpwpgn, txusnmpgn);
        cbon(cblvlpgn);
        btnon(btadpgn, btsvpgn, btedpgn, btdepgn);
        btadpgn.setEnabled(false);
    }
    //</editor-fold>
    //Nonaktifasi Form
    //<editor-fold defaultstate="collapsed">
    public void nonactmn(){
        txoff(txidmn, txnmmn, txhrgmn, txstkmn);
        cboff(cbktgmn);
        btnoff(btadmn, btsvmn, btedmn, btdemn);
        btadmn.setEnabled(true);
    }
    public void nonactmj(){
        txoff(txidmj, txnmmj);
        cboff(cbktgmj, cbsttsmj);
        btnoff(btadmj, btsvmj, btedmj, btdemj);
        btadmj.setEnabled(true);
    }
    public void nonactpgw(){
        txoff(txidpgw, txnmpgw, txtmptlhrpgw, txnohppgw);
        cboff(cbsxpgw, cbjobpgw);
        btnoff(btadpgw, btsvpgw, btedpgw, btdepgw);
        txalmtpgw.setEnabled(false);
        jdctgllhrpgw.setEnabled(false);
        btadpgw.setEnabled(true);
    }
    public void nonactplg(){
        txoff(txidplg, txnmplg, txtmptlhrplg, txnohpplg);
        cboff(cbsxplg);
        btnoff(btadplg, btsvplg, btedplg, btdeplg);
        txalmtplg.setEnabled(false);
        jdctgllhrplg.setEnabled(false);
        btadplg.setEnabled(true);
    }
    public void nonactpgn(){
        txoff(txidpgn, txnmpgn, txusnmpgn, txpwpgn, txidpgwpgn);
        cboff(cblvlpgn);
        btnoff(btadpgn, btsvpgn, btedpgn, btdepgn);
        btadpgn.setEnabled(true);
        tbpgn.setEnabled(true);
        tbpgwi.setEnabled(false);
    }
    public void nonacttrs(){
        txoff(txstkdtr, txidtrs, txtgltrs, txwkttrs, txidplgtrs, txnmplgtrs, txidmjtrs, txnmmjtrs, txidmndtr, txnmmndtr, txjmlhdtr, txhrgttldtr, txhrgdtr, txkmbltrs, txnmpgntrs, txidpgntrs, txkmbltrs, txttlbyrtrs, txbyrtrs);
        btnoff(btminmndtr, btprosesmndtr, btprinttrs, btadtrs, btendtrs);
    }
    //</editor-fold>
    //Kosongkan Form
    //<editor-fold defaultstate="collapsed">
    public void emptymn(){
        txempty(txidmn, txnmmn, txhrgmn, txstkmn);
        cbempty(cbktgmn);
        setplaceholder(txnmmn, "Masukan Nama Menu...");
        setplaceholder(txhrgmn, "Masukan Harga Menu...");
        setplaceholder(txstkmn, "Masukan Stok Menu...");
        idautomn();
    }
    public void emptymj(){
        txempty(txidmj, txnmmj);
        cbempty(cbktgmj, cbsttsmj);
        idautomj();
        autoincmj();
    }
    public void emptypgw(){
        txempty(txidpgw, txnmpgw, txtmptlhrpgw, txnohppgw);
        cbempty(cbsxpgw, cbjobpgw);
        txalmtpgw.setText("");
        jdctgllhrpgw.setDate(null);
        setplaceholder(txnmpgw, "Masukan Nama Pegawai...");
        setplaceholder(txtmptlhrpgw, "Masukan Tempat Lahir Pegawai...");
        setplaceholder(txnohppgw, "Masukan No. Telepon Pegawai...");
        idautopgw();
    }
    public void emptyplg(){
        txempty(txidplg, txnmplg, txtmptlhrplg, txnohpplg);
        cbempty(cbsxplg);
        txalmtplg.setText("");
        jdctgllhrplg.setDate(null);
        setplaceholder(txnmplg, "Masukan Nama Pelanggan...");
        setplaceholder(txtmptlhrplg, "Masukan Tempat Lahir Pelanggan...");
        setplaceholder(txnohpplg, "Masukan No. Telepon Pelanggan...");
        idautoplg();
    }
    public void emptypgn(){
        txempty(txidpgn, txnmpgn, txusnmpgn, txpwpgn, txidpgwpgn);
        cbempty(cblvlpgn);
        setplaceholder(txpwpgn, "Masukan Password Pengguna...");
        setplaceholder(txidpgwpgn, "Klik Data Pegawai untuk Mengisi...");
        setplaceholder(txnmpgn, "Klik Data Pegawai untuk Mengisi...");
        setplaceholder(txusnmpgn, "Masukan Username Pengguna...");
        idautopgn();
    }
    public void emptytrs(){
        txempty(txidmndtr, txnmmndtr, txjmlhdtr, txhrgdtr, txhrgttldtr);
    }
    //</editor-fold>
    
    //</editor-fold>
    //AKHIR FUNGSI AKTIFASI, NONAKTIFASI FORM, DAN KOSONGKAN FORM
    
    //AWAL FUNGSI AMBIL DATA DARI DATABASE
    //<editor-fold defaultstate="collapsed" desc="Method untuk mengambil data dari Database yang kemudian di tampilkan di tabel">
    
    //Ambil Semua Data
    //<editor-fold defaultstate="collapsed">
    public void getdtr(){
        mddtr.getDataVector().removeAllElements();
        mddtr.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select tbdtr.iddtr,tbdtr.idtrsdtr,tbdtr.idmndtr,tbmn.nmmn,tbdtr.jmlhdtr,tbdtr.hrgdtr,tbdtr.ttlhrgdtr from tbdtr INNER JOIN tbmn ON tbdtr.idmndtr = tbmn.idmn where tbdtr.idtrsdtr='"+idtrs+"'"; //inner join tbtrs on tbdtr.idtrsdtr = tbtrs.idtrs
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[7];
                obj[0] = rs.getString("iddtr");
                obj[1] = rs.getString("idtrsdtr");
                obj[2] = rs.getString("idmndtr");
                obj[3] = rs.getString("nmmn");
                obj[4] = rs.getString("jmlhdtr");
                obj[5] = rs.getString("hrgdtr");
                obj[6] = rs.getString("ttlhrgdtr");
                mddtr.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void gettrs(){
        mdtrs.getDataVector().removeAllElements();
        mdtrs.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbtrs";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[9];
                obj[0] = rs.getString("idtrs");
                obj[1] = rs.getString("tgltrs");
                obj[2] = rs.getString("idpgntrs");
                obj[3] = rs.getString("idplgtrs");
                obj[4] = rs.getString("idmjtrs");
                obj[5] = rs.getString("hrgttl");
                obj[6] = rs.getString("ttlbyrtrs");
                obj[7] = rs.getString("byrtrs");
                obj[8] = rs.getString("kmbltrs");
                mdtrs.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getmn(DefaultTableModel model, int limit, String sign){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmn where stkmn "+sign+""+limit+"";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[5];
                obj[0] = rs.getString("idmn");
                obj[1] = rs.getString("nmmn");
                obj[2] = rs.getString("ktgmn");
                obj[3] = rs.getString("hrgmn");
                obj[4] = rs.getString("stkmn");
                model.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getmj(DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0] = rs.getString("idmj");
                obj[1] = rs.getString("nmmj");
                obj[2] = rs.getString("ktgmj");
                obj[3] = rs.getString("sttsmj");
                model.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getmjtrs(DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj where sttsmj='Tersedia'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0] = rs.getString("idmj");
                obj[1] = rs.getString("nmmj");
                obj[2] = rs.getString("ktgmj");
                obj[3] = rs.getString("sttsmj");
                model.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getpgw(DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgw";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0] = rs.getString("idpgw");
                obj[1] = rs.getString("nmpgw");
                obj[2] = rs.getString("sxpgw");
                obj[3] = rs.getString("tmptlhrpgw");
                obj[4] = rs.getString("tgllhrpgw");
                obj[5] = rs.getString("almtpgw");
                obj[6] = rs.getString("nohppgw");
                obj[7] = rs.getString("jobpgw");
                model.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getplg(DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbplg";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[7];
                obj[0] = rs.getString("idplg");
                obj[1] = rs.getString("nmplg");
                obj[2] = rs.getString("sxplg");
                obj[3] = rs.getString("tmptlhrplg");
                obj[4] = rs.getString("tgllhrplg");
                obj[5] = rs.getString("almtplg");
                obj[6] = rs.getString("nohpplg");
                model.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    public void getpgn(){
        mdpgn.getDataVector().removeAllElements();
        mdpgn.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgn";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[6];
                obj[0] = rs.getString("idpgn");
                obj[1] = rs.getString("idpgwpgn");
                obj[2] = rs.getString("nmpgn");
                obj[3] = rs.getString("usnmpgn");
                obj[4] = rs.getString("pwpgn");
                obj[5] = rs.getString("lvlpgn");
                mdpgn.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    //</editor-fold>
    //Ambil Data Spesifik
    //<editor-fold defaultstate="collapsed">
    public void searchmn(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmn where idmn like '%" + txsrc.getText() + "%' or nmmn like '%" + txsrc.getText() + "%' or ktgmn like '%" + txsrc.getText() + "%' or hrgmn like '%" + txsrc.getText() + "%' or stkmn like '%" + txsrc.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[5];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchmntrs(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmn where (idmn like '%" + txsrc.getText() + "%' or nmmn like '%" + txsrc.getText() + "%' or ktgmn like '%" + txsrc.getText() + "%' or hrgmn like '%" + txsrc.getText() + "%') and (stkmn >= 0)";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[5];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchmj(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj where idmj like '%" + txsrc.getText() + "%' or nmmj like '%" + txsrc.getText() + "%' or ktgmj like '%" + txsrc.getText() + "%' or sttsmj like '%" + txsrc.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[4];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchmjtrs(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbmj where (idmj like '%" + txsrc.getText() + "%' or nmmj like '%" + txsrc.getText() + "%' or ktgmj like '%" + txsrc.getText() + "%') and (sttsmj = 'Tersedia')";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[4];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchpgw(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgw where idpgw like '%" + txsrc.getText() + "%' or nmpgw like '%" + txsrc.getText() + "%' or sxpgw like '%" + txsrc.getText() + "%' or tmptlhrpgw like '%" + txsrc.getText() + "%' or tgllhrpgw like '%" + txsrc.getText() + "%' or almtpgw like '%" + txsrc.getText() + "%' or nohppgw like '%" + txsrc.getText() + "%' or jobpgw like '%" + txsrc.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[8];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ob[6] = rs.getString(7);
                ob[7] = rs.getString(8);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchplg(JTextField txsrc, DefaultTableModel model){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbplg where idplg like '%" + txsrc.getText() + "%' or nmplg like '%" + txsrc.getText() + "%' or sxplg like '%" + txsrc.getText() + "%' or tmptlhrplg like '%" + txsrc.getText() + "%' or tgllhrplg like '%" + txsrc.getText() + "%' or almtplg like '%" + txsrc.getText() + "%' or nohpplg like '%" + txsrc.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[7];
                ob[0] = rs.getString(1);
                ob[1] = rs.getString(2);
                ob[2] = rs.getString(3);
                ob[3] = rs.getString(4);
                ob[4] = rs.getString(5);
                ob[5] = rs.getString(6);
                ob[6] = rs.getString(7);
                
                model.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void searchpgn(){
        mdpgn.getDataVector().removeAllElements();
        mdpgn.fireTableDataChanged();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbpgn where idpgn like '%" + txsrcpgn.getText() + "%' or idpgwpgn like '%" + txsrcpgn.getText() + "%' or nmpgn like '%" + txsrcpgn.getText() + "%' or lvlpgn like '%" + txsrcpgn.getText() + "%' or usnmpgn like '%" + txsrcpgn.getText() + "%' or pwpgn like '%" + txsrcpgn.getText() + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Object[] ob = new Object[6];
                ob[0] = rs.getString("idpgn");
                ob[1] = rs.getString("idpgwpgn");
                ob[2] = rs.getString("nmpgn");
                ob[3] = rs.getString("usnmpgn");
                ob[4] = rs.getString("pwpgn");
                ob[5] = rs.getString("lvlpgn");
                mdpgn.addRow(ob);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //</editor-fold>
    
    //</editor-fold>
    //AKHIR FUNGSI AMBIL DATA DARI DATABASE
    
    //AWAL FUNGSI CRUD FORM
    //<editor-fold defaultstate="collapsed" desc="Method CRUD (Create, Read, Update, Delete) untuk setiap form">
    
    //Form Menu
    //<editor-fold defaultstate="collapsed">
    public void savemn(){
        loadmn();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbmn values('"+idmn+"','"+nmmn+"','"+ktgmn+"','"+hrgmn+"','"+stkmn+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmn(mdmn, 0, ">=");
            getmn(mdmntrs, 1, ">=");
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void editmn(){
        loadmn();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "update tbmn set nmmn='"+nmmn+"', ktgmn='"+ktgmn+"', hrgmn='"+hrgmn+"', stkmn='"+stkmn+"' where idmn='"+idmn+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmn(mdmn, 0, ">=");
            getmn(mdmntrs, 1, ">=");
            JOptionPane.showMessageDialog(null, "Data telah berhasil diubah");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void selectmn(){
        int i = tbmn.getSelectedRow();
        if(i == -1){
            return;
        }
        txidmn.setText("" + mdmn.getValueAt(i, 0));
        txnmmn.setText("" + mdmn.getValueAt(i, 1));
        cbktgmn.setSelectedItem("" + mdmn.getValueAt(i, 2));
        txhrgmn.setText("" + mdmn.getValueAt(i, 3));
        txstkmn.setText("" + mdmn.getValueAt(i, 4));
        txnmmn.setForeground(new Color(0,0,0));
        txhrgmn.setForeground(new Color(0,0,0));
        txstkmn.setForeground(new Color(0,0,0));
        actmn();
        btsvmn.setEnabled(false);
    }
    public void deletemn(){
        loadmn();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data tersebut?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "delete from tbmn where idmn='"+idmn+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmn(mdmn, 0, ">=");
            getmn(mdmntrs, 1, ">=");
            JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
    //</editor-fold>
    //Form Meja
    //<editor-fold defaultstate="collapsed">
    public void savemj(){
        loadmj();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbmj values('"+idmj+"','"+nmmj+"','"+ktgmj+"','"+sttsmj+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmj(mdmj);
            getmjtrs(mdmjtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void editmj(){
        loadmj();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "update tbmj set nmmj='"+nmmj+"', ktgmj='"+ktgmj+"', sttsmj='"+sttsmj+"' where idmj='"+idmj+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmj(mdmj);
            getmjtrs(mdmjtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil diubah");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void selectmj(){
        int i = tbmj.getSelectedRow();
        if(i == -1){
            return;
        }
        txidmj.setText("" + mdmj.getValueAt(i, 0));
        txnmmj.setText("" + mdmj.getValueAt(i, 1));
        cbktgmj.setSelectedItem("" + mdmj.getValueAt(i, 2));
        cbsttsmj.setSelectedItem("" + mdmj.getValueAt(i, 3));
        actmj();
        btsvmj.setEnabled(false);
    }
    public void deletemj(){
        loadmj();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data tersebut?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "delete from tbmj where idmj='"+idmj+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmj(mdmj);
            getmjtrs(mdmjtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
    //</editor-fold>
    //Form Pegawai
    //<editor-fold defaultstate="collapsed">
    public void savepgw(){
        loadpgw();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbpgw values('"+idpgw+"','"+nmpgw+"','"+sxpgw+"','"+tmptlhrpgw+"','"+tgllhrpgw+"','"+almtpgw+"','"+nohppgw+"','"+jobpgw+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgw(mdpgw);
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void editpgw(){
        loadpgw();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "update tbpgw set nmpgw='"+nmpgw+"', sxpgw='"+sxpgw+"', tmptlhrpgw='"+tmptlhrpgw+"', tgllhrpgw='"+tgllhrpgw+"', almtpgw='"+almtpgw+"', nohppgw='"+nohppgw+"', jobpgw='"+jobpgw+"' where idpgw='"+idpgw+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgw(mdpgw);
            JOptionPane.showMessageDialog(null, "Data telah berhasil diubah");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void selectpgw(){
        int i = tbpgw.getSelectedRow();
        if (i == -1)
        {
            return;
        }
        txidpgw.setText(""+mdpgw.getValueAt(i, 0));
        txnmpgw.setText(""+mdpgw.getValueAt (i, 1));
        cbsxpgw.setSelectedItem(""+mdpgw.getValueAt(i, 2));
        txtmptlhrpgw.setText(""+mdpgw.getValueAt(i, 3));
        String s=(String)tbpgw.getModel().getValueAt(i, 4);
        try{
            SimpleDateFormat f= new SimpleDateFormat("yy-MM-dd");
            java.util.Date d=f.parse(s);
            jdctgllhrpgw.setDate(d);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "gagal");
        }
        txalmtpgw.setText(""+mdpgw.getValueAt(i, 5));
        txnohppgw.setText(""+mdpgw.getValueAt(i, 6));
        cbjobpgw.setSelectedItem(""+mdpgw.getValueAt(i, 7));
        txnmpgw.setForeground(new Color(0,0,0));
        txtmptlhrpgw.setForeground(new Color(0,0,0));
        txnohppgw.setForeground(new Color(0,0,0));
        actpgw();
        btsvpgw.setEnabled(false);
    }
    public void deletepgw(){
        loadpgw();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data tersebut?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "delete from tbpgw where idpgw='"+idpgw+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgw(mdpgw);
            JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
    //</editor-fold>
    //Form Pelanggan
    //<editor-fold defaultstate="collapsed">
    public void saveplg(){
        loadplg();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbplg values('"+idplg+"','"+nmplg+"','"+sxplg+"','"+tmptlhrplg+"','"+tgllhrplg+"','"+almtplg+"','"+nohpplg+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getplg(mdplg);
            getplg(mdplgtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void editplg(){
        loadplg();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "update tbplg set nmplg='"+nmplg+"', sxplg='"+sxplg+"', tmptlhrplg='"+tmptlhrplg+"', tgllhrplg='"+tgllhrplg+"', almtplg='"+almtplg+"', nohpplg='"+nohpplg+"' where idplg='"+idplg+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getplg(mdplg);
            getplg(mdplgtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil diubah");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void selectplg(){
        int i = tbplg.getSelectedRow();
        if (i == -1)
        {
            return;
        }
        txidplg.setText(""+mdplg.getValueAt(i, 0));
        txnmplg.setText(""+mdplg.getValueAt (i, 1));
        cbsxplg.setSelectedItem(""+mdplg.getValueAt(i, 2));
        txtmptlhrplg.setText(""+mdplg.getValueAt(i, 3));
        String s=(String)tbplg.getModel().getValueAt(i, 4);
        try{
            SimpleDateFormat f= new SimpleDateFormat("yy-MM-dd");
            java.util.Date d=f.parse(s);
            jdctgllhrplg.setDate(d);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "gagal");
        }
        txalmtplg.setText(""+mdplg.getValueAt(i, 5));
        txnohpplg.setText(""+mdplg.getValueAt(i, 6));
        txnmplg.setForeground(new Color(0,0,0));
        txtmptlhrplg.setForeground(new Color(0,0,0));
        txnohpplg.setForeground(new Color(0,0,0));
        actplg();
        btsvplg.setEnabled(false);
    }
    public void deleteplg(){
        loadplg();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data tersebut?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "delete from tbplg where idplg='"+idplg+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getplg(mdplg);
            getplg(mdplgtrs);
            JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
    //</editor-fold>
    //Form Pengguna
    //<editor-fold defaultstate="collapsed">
    public void savepgn(){
        loadpgn();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbpgn values('"+idpgn+"','"+idpgwpgn+"','"+nmpgn+"','"+usnmpgn+"','"+pwpgn+"','"+lvlpgn+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgn();
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        tbpgn.setEnabled(true);
        tbpgwi.setEnabled(false);
    }
    public void editpgn(){
        loadpgn();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "update tbpgn set nmpgn='"+nmpgn+"', idpgwpgn='"+idpgwpgn+"', lvlpgn='"+lvlpgn+"', usnmpgn='"+usnmpgn+"', pwpgn='"+pwpgn+"' where idpgn='"+idpgn+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgn();
            JOptionPane.showMessageDialog(null, "Data telah berhasil diubah");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void selectpgn(){
        int i = tbpgn.getSelectedRow();
        if(i == -1){
            return;
        }
        txidpgn.setText("" + mdpgn.getValueAt(i, 0));
        txidpgwpgn.setText("" + mdpgn.getValueAt(i, 1));
        txnmpgn.setText("" + mdpgn.getValueAt(i, 2));
        txusnmpgn.setText("" + mdpgn.getValueAt(i, 3));
        txpwpgn.setText("" + mdpgn.getValueAt(i, 4));
        cblvlpgn.setSelectedItem("" + mdpgn.getValueAt(i, 5));
        actpgn();
        txusnmpgn.setForeground(new Color(0,0,0));
        txpwpgn.setForeground(new Color(0,0,0));
        btsvpgn.setEnabled(false);
        tbpgwi.setEnabled(false);
    }
    public void deletepgn(){
        loadpgn();
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus data tersebut?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "delete from tbpgn where idpgn='"+idpgn+"'";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getpgn();
            JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
        }
    }
    public void selectpgwi(){
        int i = tbpgwi.getSelectedRow();
        if(i == -1){
            return;
        }
        clickplaceholder(txidpgwpgn, "Klik Data Pegawai untuk Mengisi...");
        clickplaceholder(txnmpgn, "Klik Data Pegawai untuk Mengisi...");
        txnmpgn.setText("" + mdpgwi.getValueAt(i, 1));
        txidpgwpgn.setText("" + mdpgwi.getValueAt(i, 0));
    }
    //</editor-fold>
    //Form Transaksi
    //<editor-fold defaultstate="collapsed">
    public void selectmndtr(){
        int i = tbmntrs.getSelectedRow();
        if(i == -1){
            return;
        }
        txidmndtr.setText("" + mdmntrs.getValueAt(i, 0));
        txnmmndtr.setText("" + mdmntrs.getValueAt(i, 1));
        txhrgdtr.setText("" + mdmntrs.getValueAt(i, 3));
        txstkdtr.setText(""+ mdmntrs.getValueAt(i, 4));
    }
    public void selectmjtrs(){
        int i = tbmjtrs.getSelectedRow();
        if(i == -1){
            return;
        }
        txidmjtrs.setText("" + mdmjtrs.getValueAt(i, 0));
        txnmmjtrs.setText("" + mdmjtrs.getValueAt(i, 1));
    }
    public void selectplgtrs(){
        int i = tbplgtrs.getSelectedRow();
        if(i == -1){
            return;
        }
        txidplgtrs.setText("" + mdplgtrs.getValueAt(i, 0));
        txnmplgtrs.setText("" + mdplgtrs.getValueAt(i, 1));
    }
    public void selectdtr(){
        int i = tbdtr.getSelectedRow();
        if(i == -1){
            return;
        }
        txiddtr.setText("" + mddtr.getValueAt(i, 0));
        txidtrs.setText("" + mddtr.getValueAt(i, 1));
        txidmndtr.setText("" + mddtr.getValueAt(i, 2));
        txnmmndtr.setText("" + mddtr.getValueAt(i, 3));
        txjmlhdtr.setText("" + mddtr.getValueAt(i, 4));
        txhrgdtr.setText("" + mddtr.getValueAt(i, 5));
        txhrgttldtr.setText("" + mddtr.getValueAt(i, 6));
    }
    public void loaddtr(){
        idtrs = txidtrs.getText();
        idmndtr = txidmndtr.getText();
        jmlhdtr = txjmlhdtr.getText();
        hrgdtr = txhrgdtr.getText();
        hrgttldtr = txhrgttldtr.getText();
    }
    public void loadtrs(){
        idtrs = txidtrs.getText();
        idplgtrs = txidplgtrs.getText();
        idmjtrs = txidmjtrs.getText();
        idpgntrs = txidpgntrs.getText();
        hrgttltrs = txhrgttldtr.getText();
        tgtrs = txtgltrs.getText();
        wktrs = txwkttrs.getText();
        tgltrs = tgtrs+" "+wktrs;
        ttlbyrtrs = txttlbyrtrs.getText();
        byrtrs = txbyrtrs.getText();
        kmbltrs = txkmbltrs.getText();
    }
    public void savetrs(){
        loaddtr();
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "insert into tbtrs values('"+idtrs+"','"+tgltrs+"','"+idpgntrs+"','"+idplgtrs+"','"+idmjtrs+"','"+hrgttltrs+"','"+ttlbyrtrs+"','"+byrtrs+"','"+kmbltrs+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            nonacttrs();
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void savedtr(){
        loaddtr();
        loadtrs();
        try{
            com.mysql.jdbc.Statement stat=(com.mysql.jdbc.Statement)conn.connDb().createStatement();
             String sql="insert into tbtrs values ('"+idtrs+"','"+tgltrs+"','"+idpgntrs+"','"+idplgtrs+"','"+idmjtrs+"',"+hrgttltrs+","+ttlbyrtrs+","+byrtrs+","+kmbltrs+")";
             String sql1="insert into tbdtr values (null,'"+idtrs+"','"+idmndtr+"','"+jmlhdtr+"','"+hrgdtr+"','"+hrgttldtr+"')";
             if(tbdtr.getRowCount()==0){
             PreparedStatement p=(PreparedStatement) conn.connDb().prepareStatement(sql);
             p.executeUpdate();
             };
             PreparedStatement q=(PreparedStatement) conn.connDb().prepareStatement(sql1);
             q.executeUpdate();
            getdtr();
            nonacttrs();
            JOptionPane.showMessageDialog(null, "Data telah berhasil ditambahkan");
        }catch(SQLException ex){
            Logger.getLogger(formmaster.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public void updatemj()
    {
        loaddtr();
        try
        {
            com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) conn.connDb().createStatement();
            String sql = "update tbmj set sttsmj='Terisi' WHERE idmj='"+idmjtrs+"'";
                                                        
            PreparedStatement p = (PreparedStatement) conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmjtrs(mdmjtrs);
            getmj(mdmj);
            emptytrs();
        }catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
    }
    public void showdata(){
        try {
        Connection c = conn.connDb();
        String sql = "select * from tbdtr,tbmn where tbdtr.idmndtr = tbmn.idmn and tbdtr.idmndtr'"+this.txidmndtr.getText()+"'";
        com.mysql.jdbc.Statement st = (com.mysql.jdbc.Statement) conn.connDb().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            this.txjmlhdtr.setText(rs.getString("jmlhdtr"));
            this.txnmmndtr.setText(rs.getString("nmmn"));
            this.txhrgdtr.setText(rs.getString("hrgdtr"));
            this.txhrgttldtr.setText(rs.getString("subttl"));
        }
        rs.close(); st.close();
        } catch (Exception e) {
        }
    }
    public void updatestock(){
        int x, y, z;
        x =  Integer.parseInt(txstkdtr.getText());
        y = Integer.parseInt(txjmlhdtr.getText());
        z = x-y;
        
        String barang_id = this.txidmndtr.getText();
        try {
            Connection c = conn.connDb();
            String sql = "update tbmn set stkmn=? where idmn=?";
            PreparedStatement p = (PreparedStatement)c.prepareStatement(sql);
            p.setInt(1, z);
            p.setString(2, barang_id);
            p.executeUpdate();
            p.close();
            getmn(mdmntrs, 1, ">=");
            getmn(mdmn, 0, ">=");
        } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Terjadi Kesalahan");
        }finally{
            
        }
    }
    public void mintrs(){
        int x, y, z;
        x = Integer.parseInt(txstkdtr.getText());
        y = Integer.parseInt(txjmlhdtr.getText());
        z = x+y;
        String barang_id = this.txidmndtr.getText();
        try {
            Connection c = conn.connDb();
            String sql = "update tbmn set stkmn=? where idmn=?";
            PreparedStatement p = (PreparedStatement)c.prepareStatement(sql);
            p.setInt(1, z);
            p.setString(2, barang_id);
            p.executeUpdate();
            p.close();
            getmn(mdmntrs, 1, ">=");
            getmn(mdmn, 0, ">=");
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e); 
        }finally{
            
        }
        try {
            Connection c = conn.connDb();
            String sql = "delete from tbdtr where idmndtr='"+this.txidmndtr.getText()+"' and iddtr='"+this.txiddtr.getText()+"'";
            PreparedStatement p = (PreparedStatement)c.prepareStatement(sql);
            p.executeUpdate();
            p.close();
        getdtr();
        } catch (Exception e) {
        System.out.println("Terjadi Kesalahan");
        }finally{
            JOptionPane.showMessageDialog(this, "Sukses Hapus Data");
        }
    }
    public void end(){
        String jual_nofa = this.txidtrs.getText();
        String jual_total = this.txttlbyrtrs.getText();
        String jual_cash = this.txbyrtrs.getText();
        String jual_kembali = this.txkmbltrs.getText();
        String jual_idpgn = this.txidpgntrs.getText();
        String jual_idplg = this.txidplgtrs.getText();
        String jual_idmj = this.txidmjtrs.getText();
        String jual_hrgttl = this.txhrgttldtr.getText();
        String jual_tgl = this.txtgltrs.getText();
        
        try {
            Connection c = conn.connDb();
            String sql = "update tbtrs set tgltrs='"+jual_tgl+"',"+"idpgntrs='"+idpgntrs+"',"+"idplgtrs='"+idplgtrs+"',"+"idmjtrs='"+idmjtrs+"',"+"hrgttl="+hrgttltrs+","+"ttlbyrtrs="+ttlbyrtrs+","+"byrtrs="+byrtrs+","+"kmbltrs="+kmbltrs+" where idtrs='"+idtrs+"'";
            PreparedStatement p = (PreparedStatement)c.prepareStatement(sql);
//            p.setString(1,jual_nofa);
//            p.setString(2, jual_tgl);
//            p.setString(3, jual_idpgn);
//            p.setString(4, jual_idplg);
//            p.setString(5, jual_idmj);
//            p.setString(6, jual_hrgttl);
//            p.setString(7, jual_total);
//            p.setString(8, jual_cash);
//            p.setString(9, jual_kembali);
            p.executeUpdate();
            p.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            JOptionPane.showMessageDialog(this, "Data Telah Tersimpan");
        }
        getdtr();
    }
    public void ubahdata()
    {
        loaddtr();
        loadtrs();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        java.util.Date tanggal = new java.util.Date(); 
        String jual_tgl = dateFormat.format(tanggal)+" "+wktrs;
        try
        {
            com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement) conn.connDb().createStatement();
            String sql = "update tbtrs set tgltrs='"+jual_tgl+"',"+"idpgntrs='"+idpgntrs+"',"+"idplgtrs='"+idplgtrs+"',"+"idmjtrs='"+idmjtrs+"',ttlbyrtrs="+ttlbyrtrs+",byrtrs="+byrtrs+",kmbltrs="+kmbltrs+" where idtrs='"+idtrs+"'";                                    
            PreparedStatement p = (PreparedStatement) conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            getmn(mdmntrs, 1, ">=");
            gettrs();
            emptytrs();
            JOptionPane.showMessageDialog(null,"Data berhasil dirubah");
        }catch(SQLException err)
        {
            JOptionPane.showMessageDialog(null,err.getMessage());
        }
    }
    public void loadprint(){
        idtrs = txidtrs.getText();
        idplgtrs = txidplgtrs.getText();
        idmjtrs = txidmjtrs.getText();
        idpgntrs = txidpgntrs.getText();
        hrgttltrs = txhrgttldtr.getText();
        ttlbyrtrs = txttlbyrtrs.getText();
        byrtrs = txbyrtrs.getText();
        kmbltrs = txkmbltrs.getText();
    }
    public void saveprint(){
        loadprint();
        String ps = "yy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(ps);
        tgl = String.valueOf(format.format(txtgltrs.getText()));
        try {
            com.mysql.jdbc.Statement stat = (com.mysql.jdbc.Statement)conn.connDb().createStatement();
            String sql = "insert into tbtrs (idtrs, tgltrs, idpgntrs, idplgtrs, idmjtrs, hrgttl, ttlbyrtrs, byrtrs, kmbltrs) values ('"+idtrs+"', '"+tgltrs+"', '"+idpgntrs+"', '"+idplgtrs+"', '"+idmjtrs+"', '"+hrgttltrs+"', '"+ttlbyrtrs+"', '"+byrtrs+"', '"+kmbltrs+"')";
            PreparedStatement p = (PreparedStatement)conn.connDb().prepareStatement(sql);
            p.executeUpdate();
            nonacttrs();
            emptytrs();
        } catch (Exception e) {
        }
    }
    public void loadhistori(){
        Calendar Calawal = jdc1.getCalendar();
        Calawal.set(Calendar.HOUR_OF_DAY, 0);
        Calawal.set(Calendar.MINUTE, 0);
        Calawal.set(Calendar.SECOND, 0);
        Timestamp tsawal = new Timestamp(Calawal.getTimeInMillis());
        tglawal = tsawal.toString();
        Calendar Calakhir = jdc2.getCalendar();
        Calakhir.set(Calendar.HOUR_OF_DAY, 23);
        Calakhir.set(Calendar.MINUTE, 59);
        Calakhir.set(Calendar.SECOND, 0);
        Timestamp tsakhir = new Timestamp(Calakhir.getTimeInMillis());
        tglakhir = tsakhir.toString();
    }
    public void printhistori(String nmlap){
        loadhistori();
        try {
            HashMap parameter = new HashMap();
            
            parameter.put("dc1", tglawal);
            parameter.put("dc2", tglakhir);
            
            String nmfile = "src/Report/"+nmlap;
            File Report = new File(nmfile);
            JasperReport jrpt;
            jrpt = (JasperReport) JRLoader.loadObject(Report);
            JasperPrint jprt = JasperFillManager.fillReport(jrpt, parameter, conn.connDb());
            
            JasperViewer.viewReport(jprt,false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cetak laporan gagal"+e);
        }
    }
    public void srchistori(){
        mdtrs.getDataVector().removeAllElements();
        mdtrs.fireTableDataChanged();
        String frmt = "yyyy-MM-dd";
        SimpleDateFormat f = new SimpleDateFormat(frmt);
        String Calawal = String.valueOf(f.format(jdc1.getDate()));
        String Calakhr = String.valueOf(f.format(jdc2.getDate()));
        try{
            Connection c = conn.connDb();
            Statement st = c.createStatement();
            String sql = "select * from tbtrs where (idtrs like '%" + txsrctrs.getText() + "%' or tgltrs like '%" + txsrctrs.getText() + "%' or idpgntrs like '%" + txsrctrs.getText() + "%' or idplgtrs like '%" + txsrctrs.getText() + "%' or idmjtrs like '%" + txsrctrs.getText() + "%' or hrgttl like '%" + txsrctrs.getText() + "%' or ttlbyrtrs like '%" + txsrctrs.getText() + "%' or byrtrs like '%" + txsrctrs.getText() + "%' or kmbltrs like '%" + txsrctrs.getText() + "%') and (tgltrs between '"+Calawal+"' and '"+Calakhr+"')";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[9];
                obj[0] = rs.getString("idtrs");
                obj[1] = rs.getString("tgltrs");
                obj[2] = rs.getString("idpgntrs");
                obj[3] = rs.getString("idplgtrs");
                obj[4] = rs.getString("idmjtrs");
                obj[5] = rs.getString("hrgttl");
                obj[6] = rs.getString("ttlbyrtrs");
                obj[7] = rs.getString("byrtrs");
                obj[8] = rs.getString("kmbltrs");
                mdtrs.addRow(obj);
            }
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
    }
    //</editor-fold>
    //Muat Data pada Variabel
    //<editor-fold defaultstate="collapsed">
    public void loadmn(){
        idmn = txidmn.getText();
        nmmn = txnmmn.getText();
        ktgmn = (String)cbktgmn.getSelectedItem();
        hrgmn = txhrgmn.getText();
        stkmn = txstkmn.getText();
   }
    public void loadmj(){
        idmj = txidmj.getText();
        nmmj = txnmmj.getText();
        ktgmj = (String)cbktgmj.getSelectedItem();
        sttsmj = (String)cbsttsmj.getSelectedItem();
   }
    public void loadpgw(){
        idpgw = txidpgw.getText();
        nmpgw = txnmpgw.getText();
        nohppgw = txnohppgw.getText();
        tmptlhrpgw = txtmptlhrpgw.getText();
        almtpgw = txalmtpgw.getText();
        sxpgw = (String)cbsxpgw.getSelectedItem();
        jobpgw = (String)cbjobpgw.getSelectedItem();
        String frmt = "yyyy-MM-dd";
        SimpleDateFormat f = new SimpleDateFormat(frmt);
        tgllhrpgw = String.valueOf(f.format(jdctgllhrpgw.getDate()));
   }
    public void loadplg(){
        idplg = txidplg.getText();
        nmplg = txnmplg.getText();
        nohpplg = txnohpplg.getText();
        tmptlhrplg = txtmptlhrplg.getText();
        almtplg = txalmtplg.getText();
        sxplg = (String)cbsxplg.getSelectedItem();
        String frmt = "yyyy-MM-dd";
        SimpleDateFormat f = new SimpleDateFormat(frmt);
        tgllhrplg = String.valueOf(f.format(jdctgllhrplg.getDate()));
   }
    public void loadpgn(){
        idpgn = txidpgn.getText();
        idpgwpgn =  txidpgwpgn.getText();
        nmpgn = txnmpgn.getText();
        lvlpgn = (String)cblvlpgn.getSelectedItem();
        usnmpgn = txusnmpgn.getText();
        pwpgn = txpwpgn.getText();
   }
    //</editor-fold>

    //</editor-fold>
    //AKHIR FUNGSI CRUD FORM
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        bg = new javax.swing.JPanel();
        sidepanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        menuhome = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        selhm = new javax.swing.JPanel();
        menumn = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        selmn = new javax.swing.JPanel();
        menumj = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        selmj = new javax.swing.JPanel();
        menupgw = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        selpgw = new javax.swing.JPanel();
        menuplg = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        selplg = new javax.swing.JPanel();
        menupgn = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        selpgn = new javax.swing.JPanel();
        menutrs = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        seltrs = new javax.swing.JPanel();
        menurpt = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        selrpt = new javax.swing.JPanel();
        menuout = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        selrpt1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btabout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        master = new javax.swing.JTabbedPane();
        beranda = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        lbwelcome = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbusnmpgnhm = new javax.swing.JLabel();
        lbtglhm = new javax.swing.JLabel();
        lbwkthm = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbnmpgnhm = new javax.swing.JLabel();
        lblvlpgnhm = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        formmenu = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txidmn = new javax.swing.JTextField();
        txnmmn = new javax.swing.JTextField();
        txstkmn = new javax.swing.JTextField();
        txhrgmn = new javax.swing.JTextField();
        cbktgmn = new javax.swing.JComboBox<>();
        btlmn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txsrcmn = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        btsrcmn = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbmn = new javax.swing.JTable();
        btadmn = new javax.swing.JButton();
        btsvmn = new javax.swing.JButton();
        btedmn = new javax.swing.JButton();
        btdemn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        formmeja = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel18 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txidmj = new javax.swing.JTextField();
        txnmmj = new javax.swing.JTextField();
        cbktgmj = new javax.swing.JComboBox<>();
        cbsttsmj = new javax.swing.JComboBox<>();
        btlmj = new javax.swing.JButton();
        btsvmj = new javax.swing.JButton();
        btedmj = new javax.swing.JButton();
        btdemj = new javax.swing.JButton();
        btadmj = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txsrcmj = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        btsrcmj = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbmj = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        formpegawai = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txidpgw = new javax.swing.JTextField();
        txnmpgw = new javax.swing.JTextField();
        txnohppgw = new javax.swing.JTextField();
        txtmptlhrpgw = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txalmtpgw = new javax.swing.JTextArea();
        cbsxpgw = new javax.swing.JComboBox<>();
        cbjobpgw = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jdctgllhrpgw = new com.toedter.calendar.JDateChooser();
        btlpgw = new javax.swing.JButton();
        btsvpgw = new javax.swing.JButton();
        btedpgw = new javax.swing.JButton();
        btdepgw = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txsrcpgw = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btsrcpgw = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbpgw = new javax.swing.JTable();
        btadpgw = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        formpelanggan = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txidplg = new javax.swing.JTextField();
        txnmplg = new javax.swing.JTextField();
        txnohpplg = new javax.swing.JTextField();
        txtmptlhrplg = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txalmtplg = new javax.swing.JTextArea();
        cbsxplg = new javax.swing.JComboBox<>();
        jdctgllhrplg = new com.toedter.calendar.JDateChooser();
        btlplg = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        btsvplg = new javax.swing.JButton();
        btedplg = new javax.swing.JButton();
        btdeplg = new javax.swing.JButton();
        btadplg = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txsrcplg = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        btsrcplg = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbplg = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        formpengguna = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel24 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txidpgn = new javax.swing.JTextField();
        txnmpgn = new javax.swing.JTextField();
        txpwpgn = new javax.swing.JTextField();
        txusnmpgn = new javax.swing.JTextField();
        cblvlpgn = new javax.swing.JComboBox<>();
        btlpgn = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        txidpgwpgn = new javax.swing.JTextField();
        btsvpgn = new javax.swing.JButton();
        btedpgn = new javax.swing.JButton();
        btdepgn = new javax.swing.JButton();
        btadpgn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        txsrcpgwi = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        btsrcpgwi = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbpgwi = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txsrcpgn = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        btsrcpgn = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tbpgn = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        formtransaksi = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jPanel27 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txidtrs = new javax.swing.JTextField();
        txtgltrs = new javax.swing.JTextField();
        txnmmjtrs = new javax.swing.JTextField();
        txnmplgtrs = new javax.swing.JTextField();
        txnmpgntrs = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        txwkttrs = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        txttlbyrtrs = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        txbyrtrs = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txkmbltrs = new javax.swing.JTextField();
        btadtrs = new javax.swing.JButton();
        btprinttrs = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        txsrcplgtrs = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        btsrcplgtrs = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbplgtrs = new javax.swing.JTable();
        btsrcmjtrs = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txsrcmjtrs = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbmjtrs = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        txsrcmntrs = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        btsrcmntrs = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tbmntrs = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        jPanel35 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        txnmmndtr = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txhrgdtr = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        txjmlhdtr = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        txhrgttldtr = new javax.swing.JTextField();
        btminmndtr = new javax.swing.JButton();
        btprosesmndtr = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        txidmndtr = new javax.swing.JTextField();
        txidpgntrs = new javax.swing.JTextField();
        txidmjtrs = new javax.swing.JTextField();
        txidplgtrs = new javax.swing.JTextField();
        txstkdtr = new javax.swing.JTextField();
        btendtrs = new javax.swing.JButton();
        txiddtr = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbdtr = new javax.swing.JTable();
        jLabel101 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        report = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        txsrctrs = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        btsrctrs = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbtrs = new javax.swing.JTable();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jdc1 = new com.toedter.calendar.JDateChooser();
        jdc2 = new com.toedter.calendar.JDateChooser();
        btprinttrs1 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mnitmn = new javax.swing.JMenuItem();
        mnitmj = new javax.swing.JMenuItem();
        mnitpgw = new javax.swing.JMenuItem();
        mnitplg = new javax.swing.JMenuItem();
        mnitpgn = new javax.swing.JMenuItem();
        mnithome = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnittrs = new javax.swing.JMenuItem();
        mnitrpt = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mnitout = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1320, 730));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setPreferredSize(new java.awt.Dimension(1330, 680));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidepanel.setBackground(new java.awt.Color(175, 5, 30));
        sidepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(175, 5, 30));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuhome.setBackground(new java.awt.Color(220, 65, 100));
        menuhome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuhome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuhomeMouseClicked(evt);
            }
        });
        menuhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setBackground(new java.awt.Color(255, 204, 204));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/beranda.png"))); // NOI18N
        menuhome.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel38.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Beranda");
        menuhome.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selhm.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout selhmLayout = new javax.swing.GroupLayout(selhm);
        selhm.setLayout(selhmLayout);
        selhmLayout.setHorizontalGroup(
            selhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selhmLayout.setVerticalGroup(
            selhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menuhome.add(selhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        jPanel1.add(menuhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 240, 40));

        menumn.setBackground(new java.awt.Color(175, 5, 30));
        menumn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menumn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menumnMouseClicked(evt);
            }
        });
        menumn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/menu.png"))); // NOI18N
        menumn.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel13.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Data Menu");
        menumn.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selmn.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selmnLayout = new javax.swing.GroupLayout(selmn);
        selmn.setLayout(selmnLayout);
        selmnLayout.setHorizontalGroup(
            selmnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selmnLayout.setVerticalGroup(
            selmnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menumn.add(selmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 40));

        jPanel1.add(menumn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, 40));

        menumj.setBackground(new java.awt.Color(175, 5, 30));
        menumj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menumj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menumjMouseClicked(evt);
            }
        });
        menumj.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/meja.png"))); // NOI18N
        menumj.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel15.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Data Meja");
        menumj.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selmj.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selmjLayout = new javax.swing.GroupLayout(selmj);
        selmj.setLayout(selmjLayout);
        selmjLayout.setHorizontalGroup(
            selmjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selmjLayout.setVerticalGroup(
            selmjLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menumj.add(selmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menumj, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, 40));

        menupgw.setBackground(new java.awt.Color(175, 5, 30));
        menupgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menupgw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menupgwMouseClicked(evt);
            }
        });
        menupgw.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pegawai.png"))); // NOI18N
        menupgw.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel17.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Data Pegawai");
        menupgw.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selpgw.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selpgwLayout = new javax.swing.GroupLayout(selpgw);
        selpgw.setLayout(selpgwLayout);
        selpgwLayout.setHorizontalGroup(
            selpgwLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selpgwLayout.setVerticalGroup(
            selpgwLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menupgw.add(selpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menupgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, 40));

        menuplg.setBackground(new java.awt.Color(175, 5, 30));
        menuplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuplg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuplgMouseClicked(evt);
            }
        });
        menuplg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pelanggan.png"))); // NOI18N
        menuplg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel28.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Data Pelanggan");
        menuplg.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selplg.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selplgLayout = new javax.swing.GroupLayout(selplg);
        selplg.setLayout(selplgLayout);
        selplgLayout.setHorizontalGroup(
            selplgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selplgLayout.setVerticalGroup(
            selplgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menuplg.add(selplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menuplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 240, 40));

        menupgn.setBackground(new java.awt.Color(175, 5, 30));
        menupgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menupgn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menupgnMouseClicked(evt);
            }
        });
        menupgn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/pengguna.png"))); // NOI18N
        menupgn.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel30.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Data Pengguna");
        menupgn.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selpgn.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selpgnLayout = new javax.swing.GroupLayout(selpgn);
        selpgn.setLayout(selpgnLayout);
        selpgnLayout.setHorizontalGroup(
            selpgnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selpgnLayout.setVerticalGroup(
            selpgnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menupgn.add(selpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menupgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 240, 40));

        menutrs.setBackground(new java.awt.Color(175, 5, 30));
        menutrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menutrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menutrsMouseClicked(evt);
            }
        });
        menutrs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/transaksi.png"))); // NOI18N
        menutrs.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel34.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Transaksi");
        menutrs.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        seltrs.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout seltrsLayout = new javax.swing.GroupLayout(seltrs);
        seltrs.setLayout(seltrsLayout);
        seltrsLayout.setHorizontalGroup(
            seltrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        seltrsLayout.setVerticalGroup(
            seltrsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menutrs.add(seltrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menutrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 240, 40));

        menurpt.setBackground(new java.awt.Color(175, 5, 30));
        menurpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menurpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menurptMouseClicked(evt);
            }
        });
        menurpt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/report.png"))); // NOI18N
        menurpt.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel36.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Histori Transaksi");
        menurpt.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selrpt.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selrptLayout = new javax.swing.GroupLayout(selrpt);
        selrpt.setLayout(selrptLayout);
        selrptLayout.setHorizontalGroup(
            selrptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selrptLayout.setVerticalGroup(
            selrptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menurpt.add(selrpt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menurpt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 240, 40));

        menuout.setBackground(new java.awt.Color(175, 5, 30));
        menuout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuoutMouseClicked(evt);
            }
        });
        menuout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/out.png"))); // NOI18N
        menuout.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 20, 20));

        jLabel52.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Log Out");
        menuout.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 20));

        selrpt1.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout selrpt1Layout = new javax.swing.GroupLayout(selrpt1);
        selrpt1.setLayout(selrpt1Layout);
        selrpt1Layout.setHorizontalGroup(
            selrpt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        selrpt1Layout.setVerticalGroup(
            selrpt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        menuout.add(selrpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanel1.add(menuout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 240, 40));

        jPanel22.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 240, 10));

        jPanel3.setBackground(new java.awt.Color(175, 5, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 10));

        jScrollPane2.setViewportView(jPanel1);

        sidepanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 240, 400));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo2.png"))); // NOI18N
        jLabel1.setToolTipText("");
        sidepanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 140));

        btabout.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        btabout.setForeground(new java.awt.Color(255, 255, 255));
        btabout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btabout.setText("Tentang Pengembang");
        btabout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btaboutMouseClicked(evt);
            }
        });
        sidepanel.add(btabout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 120, -1));

        bg.add(sidepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 710));

        jPanel2.setBackground(new java.awt.Color(230, 0, 48));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1310, 50));

        beranda.setBackground(new java.awt.Color(255, 255, 255));
        beranda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/logo3.png"))); // NOI18N
        beranda.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 1070, 300));

        jPanel33.setBackground(new java.awt.Color(230, 0, 48));

        jLabel97.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("BERANDA");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        beranda.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        lbwelcome.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        lbwelcome.setForeground(new java.awt.Color(255, 255, 255));
        lbwelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbwelcome.setText("Selamat Datang -nama-");
        beranda.add(lbwelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 1070, 40));

        jPanel25.setBackground(new java.awt.Color(230, 0, 48));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Login Sebagai");
        jPanel25.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 110, 20));

        jLabel4.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Level User");
        jPanel25.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 110, 30));

        lbusnmpgnhm.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        lbusnmpgnhm.setForeground(new java.awt.Color(255, 255, 255));
        lbusnmpgnhm.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbusnmpgnhm.setText("Username");
        jPanel25.add(lbusnmpgnhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 310, 40));

        lbtglhm.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        lbtglhm.setForeground(new java.awt.Color(255, 255, 255));
        lbtglhm.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbtglhm.setText("Tanggal");
        jPanel25.add(lbtglhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 15, 310, 40));

        lbwkthm.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        lbwkthm.setForeground(new java.awt.Color(255, 255, 255));
        lbwkthm.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbwkthm.setText("Jam");
        jPanel25.add(lbwkthm, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 310, 20));

        jLabel8.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(":");
        jPanel25.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 10, 20));

        jLabel9.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(":");
        jPanel25.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 10, 30));

        lbnmpgnhm.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        lbnmpgnhm.setForeground(new java.awt.Color(255, 255, 255));
        lbnmpgnhm.setText("Nama");
        jPanel25.add(lbnmpgnhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 480, 20));

        lblvlpgnhm.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        lblvlpgnhm.setForeground(new java.awt.Color(255, 255, 255));
        lblvlpgnhm.setText("Level");
        jPanel25.add(lblvlpgnhm, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 480, 30));

        beranda.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 1080, 90));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        beranda.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        master.addTab("Beranda", beranda);

        formmenu.setBackground(new java.awt.Color(255, 255, 255));
        formmenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(230, 0, 48));

        jLabel31.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("FORM MENU");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formmenu.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel32.setText("ID Menu");
        jPanel15.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 20));

        jLabel39.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel39.setText("Nama Menu");
        jPanel15.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 20));

        jLabel40.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel40.setText("Kategori Menu");
        jPanel15.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        jLabel41.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel41.setText("Harga Menu");
        jPanel15.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 230, 20));

        jLabel42.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel42.setText("Stok Menu");
        jPanel15.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 230, 20));

        txidmn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidmn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel15.add(txidmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        txnmmn.setForeground(new java.awt.Color(127, 127, 127));
        txnmmn.setText("Masukan Nama Menu...");
        txnmmn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmmn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txnmmn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txnmmnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txnmmnFocusLost(evt);
            }
        });
        txnmmn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txnmmnKeyTyped(evt);
            }
        });
        jPanel15.add(txnmmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        txstkmn.setForeground(new java.awt.Color(127, 127, 127));
        txstkmn.setText("Masukan Stok Menu...");
        txstkmn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txstkmn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txstkmn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txstkmnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txstkmnFocusLost(evt);
            }
        });
        txstkmn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txstkmnKeyTyped(evt);
            }
        });
        jPanel15.add(txstkmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 30));

        txhrgmn.setForeground(new java.awt.Color(127, 127, 127));
        txhrgmn.setText("Masukan Harga Menu...");
        txhrgmn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txhrgmn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txhrgmn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txhrgmnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txhrgmnFocusLost(evt);
            }
        });
        txhrgmn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txhrgmnKeyTyped(evt);
            }
        });
        jPanel15.add(txhrgmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 30));

        cbktgmn.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbktgmn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Makanan", "Minuman" }));
        cbktgmn.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel15.add(cbktgmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 30));

        btlmn.setBackground(new java.awt.Color(175, 5, 30));
        btlmn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlmn.setForeground(new java.awt.Color(255, 255, 255));
        btlmn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btl.png"))); // NOI18N
        btlmn.setText("Batalkan Input");
        btlmn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlmnActionPerformed(evt);
            }
        });
        jPanel15.add(btlmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 170, 40));

        jScrollPane8.setViewportView(jPanel15);

        formmenu.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 290, 560));

        jPanel5.setBackground(new java.awt.Color(230, 0, 48));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcmn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcmn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel5.add(txsrcmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel46.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Tabel Menu");
        jPanel5.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcmn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcmn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcmn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcmnMouseClicked(evt);
            }
        });
        jPanel5.add(btsrcmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbmn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbmn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbmnFocusLost(evt);
            }
        });
        tbmn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmnMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbmn);

        jPanel5.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 410));

        formmenu.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 730, 500));

        btadmn.setBackground(new java.awt.Color(175, 5, 30));
        btadmn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadmn.setForeground(new java.awt.Color(255, 255, 255));
        btadmn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadmn.setText("Tambah Data");
        btadmn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadmn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadmnActionPerformed(evt);
            }
        });
        formmenu.add(btadmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 160, 50));

        btsvmn.setBackground(new java.awt.Color(175, 5, 30));
        btsvmn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btsvmn.setForeground(new java.awt.Color(255, 255, 255));
        btsvmn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btsvmn.setText("Simpan Data");
        btsvmn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btsvmn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsvmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsvmnActionPerformed(evt);
            }
        });
        formmenu.add(btsvmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 160, 50));

        btedmn.setBackground(new java.awt.Color(175, 5, 30));
        btedmn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btedmn.setForeground(new java.awt.Color(255, 255, 255));
        btedmn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edt.png"))); // NOI18N
        btedmn.setText("Ubah Data");
        btedmn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btedmn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btedmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedmnActionPerformed(evt);
            }
        });
        formmenu.add(btedmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 160, 50));

        btdemn.setBackground(new java.awt.Color(175, 5, 30));
        btdemn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btdemn.setForeground(new java.awt.Color(255, 255, 255));
        btdemn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/del.png"))); // NOI18N
        btdemn.setText("Hapus Data");
        btdemn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btdemn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btdemn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdemnActionPerformed(evt);
            }
        });
        formmenu.add(btdemn, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 160, 50));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formmenu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, -1, 660));

        master.addTab("Menu", formmenu);

        formmeja.setBackground(new java.awt.Color(255, 255, 255));
        formmeja.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(230, 0, 48));

        jLabel47.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("FORM MEJA");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formmeja.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel48.setText("ID Meja");
        jPanel18.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 20));

        jLabel49.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel49.setText("Nomor Meja");
        jPanel18.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 20));

        jLabel50.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel50.setText("Kategori Meja");
        jPanel18.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        jLabel54.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel54.setText("Status Meja");
        jPanel18.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 230, 20));

        txidmj.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidmj.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel18.add(txidmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        txnmmj.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmmj.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel18.add(txnmmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        cbktgmj.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbktgmj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Duo", "Group", "Quadro", "Family", "Floor" }));
        cbktgmj.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel18.add(cbktgmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 30));

        cbsttsmj.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbsttsmj.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Tersedia", "Terisi" }));
        cbsttsmj.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel18.add(cbsttsmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 30));

        btlmj.setBackground(new java.awt.Color(175, 5, 30));
        btlmj.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlmj.setForeground(new java.awt.Color(255, 255, 255));
        btlmj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btl.png"))); // NOI18N
        btlmj.setText("Batalkan Input");
        btlmj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlmj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlmjActionPerformed(evt);
            }
        });
        jPanel18.add(btlmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 170, 40));

        jScrollPane10.setViewportView(jPanel18);

        formmeja.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 290, 560));

        btsvmj.setBackground(new java.awt.Color(175, 5, 30));
        btsvmj.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btsvmj.setForeground(new java.awt.Color(255, 255, 255));
        btsvmj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btsvmj.setText("Simpan Data");
        btsvmj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btsvmj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsvmj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsvmjActionPerformed(evt);
            }
        });
        formmeja.add(btsvmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 160, 50));

        btedmj.setBackground(new java.awt.Color(175, 5, 30));
        btedmj.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btedmj.setForeground(new java.awt.Color(255, 255, 255));
        btedmj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edt.png"))); // NOI18N
        btedmj.setText("Ubah Data");
        btedmj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btedmj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btedmj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedmjActionPerformed(evt);
            }
        });
        formmeja.add(btedmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 160, 50));

        btdemj.setBackground(new java.awt.Color(175, 5, 30));
        btdemj.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btdemj.setForeground(new java.awt.Color(255, 255, 255));
        btdemj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/del.png"))); // NOI18N
        btdemj.setText("Hapus Data");
        btdemj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btdemj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btdemj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdemjActionPerformed(evt);
            }
        });
        formmeja.add(btdemj, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 160, 50));

        btadmj.setBackground(new java.awt.Color(175, 5, 30));
        btadmj.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadmj.setForeground(new java.awt.Color(255, 255, 255));
        btadmj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadmj.setText("Tambah Data");
        btadmj.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadmj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadmj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadmjActionPerformed(evt);
            }
        });
        formmeja.add(btadmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 160, 50));

        jPanel7.setBackground(new java.awt.Color(230, 0, 48));
        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcmj.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcmj.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel7.add(txsrcmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel56.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Tabel Meja");
        jPanel7.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcmj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcmj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcmj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcmjMouseClicked(evt);
            }
        });
        jPanel7.add(btsrcmj, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbmj.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbmj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbmjFocusLost(evt);
            }
        });
        tbmj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmjMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tbmj);

        jPanel7.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 410));

        formmeja.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 730, 500));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formmeja.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

        master.addTab("Meja", formmeja);

        formpegawai.setBackground(new java.awt.Color(255, 255, 255));
        formpegawai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(230, 0, 48));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("FORM PEGAWAI");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formpegawai.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel20.setText("ID Pegawai");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 20));

        jLabel21.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel21.setText("Nama Pegawai");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 20));

        jLabel22.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel22.setText("Gender Pegawai");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        jLabel23.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel23.setText("Tanggal Lahir Pegawai");
        jPanel12.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 230, 20));

        jLabel24.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel24.setText("Tempat Lahir Pegawai");
        jPanel12.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 230, 20));

        jLabel25.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel25.setText("No. Telepon Pegawai");
        jPanel12.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 230, 20));

        jLabel26.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel26.setText("Pekerjaan Pegawai");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 230, 20));

        jLabel27.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel27.setText("Alamat Pegawai");
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 230, 20));

        txidpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel12.add(txidpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        txnmpgw.setForeground(new java.awt.Color(127, 127, 127));
        txnmpgw.setText("Masukan Nama Pegawai...");
        txnmpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txnmpgw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txnmpgwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txnmpgwFocusLost(evt);
            }
        });
        txnmpgw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txnmpgwKeyTyped(evt);
            }
        });
        jPanel12.add(txnmpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        txnohppgw.setForeground(new java.awt.Color(127, 127, 127));
        txnohppgw.setText("Masukan No. Telepon Pegawai...");
        txnohppgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnohppgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txnohppgw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txnohppgwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txnohppgwFocusLost(evt);
            }
        });
        txnohppgw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txnohppgwKeyTyped(evt);
            }
        });
        jPanel12.add(txnohppgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 230, 30));

        txtmptlhrpgw.setForeground(new java.awt.Color(127, 127, 127));
        txtmptlhrpgw.setText("Masukan Tempat Lahir Pegawai...");
        txtmptlhrpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtmptlhrpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtmptlhrpgw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmptlhrpgwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmptlhrpgwFocusLost(evt);
            }
        });
        txtmptlhrpgw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmptlhrpgwKeyTyped(evt);
            }
        });
        jPanel12.add(txtmptlhrpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 30));

        txalmtpgw.setColumns(20);
        txalmtpgw.setRows(5);
        txalmtpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txalmtpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txalmtpgw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txalmtpgwKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txalmtpgw);

        jPanel12.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 230, 110));

        cbsxpgw.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbsxpgw.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Pria", "Wanita", "Lain-lain" }));
        cbsxpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel12.add(cbsxpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 30));

        cbjobpgw.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbjobpgw.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Manager", "Koki", "Pelayan", "Offce Boy", "Kasir", "Tukang Parkir", "Staff Keamanan" }));
        cbjobpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel12.add(cbjobpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 230, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 270, 30));
        jPanel12.add(jdctgllhrpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 30));

        btlpgw.setBackground(new java.awt.Color(175, 5, 30));
        btlpgw.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlpgw.setForeground(new java.awt.Color(255, 255, 255));
        btlpgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btl.png"))); // NOI18N
        btlpgw.setText("Batalkan Input");
        btlpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlpgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlpgwActionPerformed(evt);
            }
        });
        jPanel12.add(btlpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 170, 40));

        jScrollPane7.setViewportView(jPanel12);

        formpegawai.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 290, 560));

        btsvpgw.setBackground(new java.awt.Color(175, 5, 30));
        btsvpgw.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btsvpgw.setForeground(new java.awt.Color(255, 255, 255));
        btsvpgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btsvpgw.setText("Simpan Data");
        btsvpgw.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btsvpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsvpgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsvpgwActionPerformed(evt);
            }
        });
        formpegawai.add(btsvpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 160, 50));

        btedpgw.setBackground(new java.awt.Color(175, 5, 30));
        btedpgw.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btedpgw.setForeground(new java.awt.Color(255, 255, 255));
        btedpgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edt.png"))); // NOI18N
        btedpgw.setText("Ubah Data");
        btedpgw.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btedpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btedpgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedpgwActionPerformed(evt);
            }
        });
        formpegawai.add(btedpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 160, 50));

        btdepgw.setBackground(new java.awt.Color(175, 5, 30));
        btdepgw.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btdepgw.setForeground(new java.awt.Color(255, 255, 255));
        btdepgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/del.png"))); // NOI18N
        btdepgw.setText("Hapus Data");
        btdepgw.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btdepgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btdepgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdepgwActionPerformed(evt);
            }
        });
        formpegawai.add(btdepgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 160, 50));

        jPanel4.setBackground(new java.awt.Color(230, 0, 48));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcpgw.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel4.add(txsrcpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tabel Pegawai");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcpgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcpgw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcpgwMouseClicked(evt);
            }
        });
        jPanel4.add(btsrcpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbpgw.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbpgw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbpgwFocusLost(evt);
            }
        });
        tbpgw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpgwMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbpgw);

        jPanel4.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 410));

        formpegawai.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 730, 500));

        btadpgw.setBackground(new java.awt.Color(175, 5, 30));
        btadpgw.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadpgw.setForeground(new java.awt.Color(255, 255, 255));
        btadpgw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadpgw.setText("Tambah Data");
        btadpgw.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadpgw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadpgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadpgwActionPerformed(evt);
            }
        });
        formpegawai.add(btadpgw, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 160, 50));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formpegawai.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 660));

        master.addTab("Pegawai", formpegawai);

        formpelanggan.setBackground(new java.awt.Color(255, 255, 255));
        formpelanggan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel20.setBackground(new java.awt.Color(230, 0, 48));

        jLabel57.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("FORM PELANGGAN");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formpelanggan.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel58.setText("ID Pelanggan");
        jPanel21.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 20));

        jLabel59.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel59.setText("Nama Pelanggan");
        jPanel21.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 20));

        jLabel60.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel60.setText("Gender Pelanggan");
        jPanel21.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        jLabel61.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel61.setText("Tanggal Lahir Pelanggan");
        jPanel21.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 230, 20));

        jLabel62.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel62.setText("Tempat Lahir Pelanggan");
        jPanel21.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 230, 20));

        jLabel63.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel63.setText("No. Telepon Pelanggan");
        jPanel21.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 230, 20));

        jLabel65.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel65.setText("Alamat Pelanggan");
        jPanel21.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 230, 20));

        txidplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel21.add(txidplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        txnmplg.setForeground(new java.awt.Color(127, 127, 127));
        txnmplg.setText("Masukan Nama Pelanggan...");
        txnmplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txnmplg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txnmplgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txnmplgFocusLost(evt);
            }
        });
        txnmplg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txnmplgKeyTyped(evt);
            }
        });
        jPanel21.add(txnmplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        txnohpplg.setForeground(new java.awt.Color(127, 127, 127));
        txnohpplg.setText("Masukan No. Telepon Pelanggan...");
        txnohpplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnohpplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txnohpplg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txnohpplgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txnohpplgFocusLost(evt);
            }
        });
        txnohpplg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txnohpplgKeyTyped(evt);
            }
        });
        jPanel21.add(txnohpplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 230, 30));

        txtmptlhrplg.setForeground(new java.awt.Color(127, 127, 127));
        txtmptlhrplg.setText("Masukan Tempat Lahir Pelanggan...");
        txtmptlhrplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtmptlhrplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtmptlhrplg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmptlhrplgFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmptlhrplgFocusLost(evt);
            }
        });
        txtmptlhrplg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmptlhrplgKeyTyped(evt);
            }
        });
        jPanel21.add(txtmptlhrplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 30));

        txalmtplg.setColumns(20);
        txalmtplg.setRows(5);
        txalmtplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txalmtplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txalmtplg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txalmtplgKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(txalmtplg);

        jPanel21.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 230, 110));

        cbsxplg.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cbsxplg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Pria", "Wanita", "Lain-lain" }));
        cbsxplg.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel21.add(cbsxplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 30));
        jPanel21.add(jdctgllhrplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 30));

        btlplg.setBackground(new java.awt.Color(175, 5, 30));
        btlplg.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlplg.setForeground(new java.awt.Color(255, 255, 255));
        btlplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btl.png"))); // NOI18N
        btlplg.setText("Batalkan Input");
        btlplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlplgActionPerformed(evt);
            }
        });
        jPanel21.add(btlplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 170, 40));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel21.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 270, 30));

        jScrollPane12.setViewportView(jPanel21);

        formpelanggan.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 290, 560));

        btsvplg.setBackground(new java.awt.Color(175, 5, 30));
        btsvplg.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btsvplg.setForeground(new java.awt.Color(255, 255, 255));
        btsvplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btsvplg.setText("Simpan Data");
        btsvplg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btsvplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsvplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsvplgActionPerformed(evt);
            }
        });
        formpelanggan.add(btsvplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 160, 50));

        btedplg.setBackground(new java.awt.Color(175, 5, 30));
        btedplg.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btedplg.setForeground(new java.awt.Color(255, 255, 255));
        btedplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edt.png"))); // NOI18N
        btedplg.setText("Ubah Data");
        btedplg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btedplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btedplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedplgActionPerformed(evt);
            }
        });
        formpelanggan.add(btedplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 160, 50));

        btdeplg.setBackground(new java.awt.Color(175, 5, 30));
        btdeplg.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btdeplg.setForeground(new java.awt.Color(255, 255, 255));
        btdeplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/del.png"))); // NOI18N
        btdeplg.setText("Hapus Data");
        btdeplg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btdeplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btdeplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeplgActionPerformed(evt);
            }
        });
        formpelanggan.add(btdeplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 160, 50));

        btadplg.setBackground(new java.awt.Color(175, 5, 30));
        btadplg.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadplg.setForeground(new java.awt.Color(255, 255, 255));
        btadplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadplg.setText("Tambah Data");
        btadplg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadplgActionPerformed(evt);
            }
        });
        formpelanggan.add(btadplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 160, 50));

        jPanel8.setBackground(new java.awt.Color(230, 0, 48));
        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcplg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcplg.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel8.add(txsrcplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel66.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Tabel Pelanggan");
        jPanel8.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcplg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcplg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcplg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcplgMouseClicked(evt);
            }
        });
        jPanel8.add(btsrcplg, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbplg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbplg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbplgFocusLost(evt);
            }
        });
        tbplg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbplgMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbplg);

        jPanel8.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 410));

        formpelanggan.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 730, 500));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formpelanggan.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

        master.addTab("Pelanggan", formpelanggan);

        formpengguna.setBackground(new java.awt.Color(255, 255, 255));
        formpengguna.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(230, 0, 48));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("FORM PENGGUNA");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formpengguna.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane14.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel68.setText("ID Pengguna");
        jPanel24.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 230, 20));

        jLabel69.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel69.setText("Nama Pengguna");
        jPanel24.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        jLabel72.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel72.setText("Username Pengguna");
        jPanel24.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 230, 20));

        jLabel73.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel73.setText("Password Pengguna");
        jPanel24.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 230, 20));

        jLabel74.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel74.setText("Level Pengguna");
        jPanel24.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 230, 20));

        txidpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel24.add(txidpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        txnmpgn.setForeground(new java.awt.Color(127, 127, 127));
        txnmpgn.setText("Klik Data Pegawai untuk Mengisi...");
        txnmpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel24.add(txnmpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 230, 30));

        txpwpgn.setForeground(new java.awt.Color(127, 127, 127));
        txpwpgn.setText("Masukan Password Pengguna...");
        txpwpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txpwpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txpwpgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txpwpgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txpwpgnFocusLost(evt);
            }
        });
        txpwpgn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txpwpgnKeyTyped(evt);
            }
        });
        jPanel24.add(txpwpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 230, 30));

        txusnmpgn.setForeground(new java.awt.Color(127, 127, 127));
        txusnmpgn.setText("Masukan Username Pengguna...");
        txusnmpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txusnmpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txusnmpgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txusnmpgnFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txusnmpgnFocusLost(evt);
            }
        });
        txusnmpgn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txusnmpgnKeyTyped(evt);
            }
        });
        jPanel24.add(txusnmpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 230, 30));

        cblvlpgn.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cblvlpgn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tidak dipilih", "Administrator", "Kasir", "Pelayan" }));
        cblvlpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel24.add(cblvlpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 230, 30));

        btlpgn.setBackground(new java.awt.Color(175, 5, 30));
        btlpgn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btlpgn.setForeground(new java.awt.Color(255, 255, 255));
        btlpgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btl.png"))); // NOI18N
        btlpgn.setText("Batalkan Input");
        btlpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btlpgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlpgnActionPerformed(evt);
            }
        });
        jPanel24.add(btlpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 170, 40));

        jLabel70.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel70.setText("ID Pegawai");
        jPanel24.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 230, 20));

        txidpgwpgn.setForeground(new java.awt.Color(127, 127, 127));
        txidpgwpgn.setText("Klik Data Pegawai untuk Mengisi...");
        txidpgwpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidpgwpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel24.add(txidpgwpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        jScrollPane14.setViewportView(jPanel24);

        formpengguna.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 290, 560));

        btsvpgn.setBackground(new java.awt.Color(175, 5, 30));
        btsvpgn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btsvpgn.setForeground(new java.awt.Color(255, 255, 255));
        btsvpgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btsvpgn.setText("Simpan Data");
        btsvpgn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btsvpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsvpgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsvpgnActionPerformed(evt);
            }
        });
        formpengguna.add(btsvpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 160, 50));

        btedpgn.setBackground(new java.awt.Color(175, 5, 30));
        btedpgn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btedpgn.setForeground(new java.awt.Color(255, 255, 255));
        btedpgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edt.png"))); // NOI18N
        btedpgn.setText("Ubah Data");
        btedpgn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btedpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btedpgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btedpgnActionPerformed(evt);
            }
        });
        formpengguna.add(btedpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 160, 50));

        btdepgn.setBackground(new java.awt.Color(175, 5, 30));
        btdepgn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btdepgn.setForeground(new java.awt.Color(255, 255, 255));
        btdepgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/del.png"))); // NOI18N
        btdepgn.setText("Hapus Data");
        btdepgn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btdepgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btdepgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdepgnActionPerformed(evt);
            }
        });
        formpengguna.add(btdepgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 590, 160, 50));

        btadpgn.setBackground(new java.awt.Color(175, 5, 30));
        btadpgn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadpgn.setForeground(new java.awt.Color(255, 255, 255));
        btadpgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadpgn.setText("Tambah Data");
        btadpgn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadpgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadpgnActionPerformed(evt);
            }
        });
        formpengguna.add(btadpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 160, 50));

        jPanel16.setBackground(new java.awt.Color(230, 0, 48));
        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcpgwi.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcpgwi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel16.add(txsrcpgwi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel88.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Tabel Pegawai");
        jPanel16.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcpgwi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcpgwi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcpgwi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcpgwiMouseClicked(evt);
            }
        });
        jPanel16.add(btsrcpgwi, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbpgwi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbpgwi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbpgwiFocusLost(evt);
            }
        });
        tbpgwi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpgwiMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tbpgwi);

        jPanel16.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 160));

        formpengguna.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 730, 250));

        jPanel9.setBackground(new java.awt.Color(230, 0, 48));
        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcpgn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel9.add(txsrcpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 30));

        jLabel76.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Tabel Pengguna");
        jPanel9.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcpgn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcpgn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcpgn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcpgnMouseClicked(evt);
            }
        });
        jPanel9.add(btsrcpgn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 30, 30));

        tbpgn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbpgn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbpgnFocusLost(evt);
            }
        });
        tbpgn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpgnMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tbpgn);

        jPanel9.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 730, 160));

        formpengguna.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 730, 250));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formpengguna.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

        master.addTab("Pengguna", formpengguna);

        formtransaksi.setBackground(new java.awt.Color(255, 255, 255));
        formtransaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel26.setBackground(new java.awt.Color(230, 0, 48));

        jLabel77.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("FORM TRANSAKSI");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        formtransaksi.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jScrollPane16.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane16.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel78.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel78.setText("ID Transaksi");
        jPanel27.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 190, 20));

        jLabel79.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel79.setText("Tanggal dan Jam");
        jPanel27.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 190, 20));

        jLabel81.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel81.setText(" Nama Petugas");
        jPanel27.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 190, 20));

        jLabel82.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel82.setText("Nama Pelanggan");
        jPanel27.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 190, 20));

        jLabel83.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel83.setText("No. Meja");
        jPanel27.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 190, 20));

        txidtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txidtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, 30));

        txtgltrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtgltrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txtgltrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 190, 30));

        txnmmjtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmmjtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txnmmjtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 190, 30));

        txnmplgtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmplgtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txnmplgtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 190, 30));

        txnmpgntrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmpgntrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txnmpgntrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 190, 30));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel27.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 230, 20));

        txwkttrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txwkttrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txwkttrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, 30));

        jLabel93.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel93.setText("Total Bayar");
        jPanel27.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 190, 20));

        txttlbyrtrs.setText("0");
        txttlbyrtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttlbyrtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txttlbyrtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 190, 30));

        jLabel94.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel94.setText("Bayar");
        jPanel27.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 190, 20));

        txbyrtrs.setText("0");
        txbyrtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txbyrtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txbyrtrs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txbyrtrsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txbyrtrsKeyTyped(evt);
            }
        });
        jPanel27.add(txbyrtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 190, 30));

        jLabel95.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel95.setText("Kembali");
        jPanel27.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 190, 20));

        txkmbltrs.setText("0");
        txkmbltrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txkmbltrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel27.add(txkmbltrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 190, 30));

        jScrollPane16.setViewportView(jPanel27);

        formtransaksi.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 250, 560));

        btadtrs.setBackground(new java.awt.Color(175, 5, 30));
        btadtrs.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btadtrs.setForeground(new java.awt.Color(255, 255, 255));
        btadtrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/ad.png"))); // NOI18N
        btadtrs.setText("Tambah Data");
        btadtrs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btadtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btadtrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btadtrsActionPerformed(evt);
            }
        });
        formtransaksi.add(btadtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, 160, 50));

        btprinttrs.setBackground(new java.awt.Color(175, 5, 30));
        btprinttrs.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btprinttrs.setForeground(new java.awt.Color(255, 255, 255));
        btprinttrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/print.png"))); // NOI18N
        btprinttrs.setText("Cetak Data");
        btprinttrs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btprinttrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btprinttrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprinttrsActionPerformed(evt);
            }
        });
        formtransaksi.add(btprinttrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, 110, 40));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(230, 0, 48));
        jPanel30.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcplgtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcplgtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel30.add(txsrcplgtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 690, 30));

        jLabel91.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Tabel Pelanggan");
        jPanel30.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrcplgtrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcplgtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcplgtrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcplgtrsMouseClicked(evt);
            }
        });
        jPanel30.add(btsrcplgtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 30, 30));

        tbplgtrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbplgtrs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbplgtrsFocusLost(evt);
            }
        });
        tbplgtrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbplgtrsMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(tbplgtrs);

        jPanel30.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 770, 140));

        btsrcmjtrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcmjtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcmjtrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcmjtrsMouseClicked(evt);
            }
        });
        jPanel30.add(btsrcmjtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, 30, 30));

        jLabel98.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText("Tabel Meja");
        jPanel30.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 670, 30));

        txsrcmjtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcmjtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel30.add(txsrcmjtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 670, 30));

        tbmjtrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbmjtrs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbmjtrsFocusLost(evt);
            }
        });
        tbmjtrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmjtrsMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tbmjtrs);

        jPanel30.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 770, 140));

        jTabbedPane1.addTab("Pelanggan dan Meja", jPanel30);

        jPanel34.setBackground(new java.awt.Color(230, 0, 48));
        jPanel34.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrcmntrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txsrcmntrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel34.add(txsrcmntrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 470, 30));

        jLabel99.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Tabel Menu");
        jPanel34.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 470, 30));

        btsrcmntrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrcmntrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrcmntrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrcmntrsMouseClicked(evt);
            }
        });
        jPanel34.add(btsrcmntrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 30, 30));

        tbmntrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbmntrs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbmntrsFocusLost(evt);
            }
        });
        tbmntrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbmntrsMouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(tbmntrs);

        jPanel34.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 550, 160));

        jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane17.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setFocusable(false);
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel89.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel89.setText("Nama Menu");
        jPanel35.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, 20));

        txnmmndtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txnmmndtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel35.add(txnmmndtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 170, 30));

        jLabel85.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel85.setText("Harga");
        jPanel35.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 170, 20));

        txhrgdtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txhrgdtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel35.add(txhrgdtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 170, 30));

        jLabel90.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel90.setText("Jumlah");
        jPanel35.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 170, 20));

        txjmlhdtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txjmlhdtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txjmlhdtr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txjmlhdtrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txjmlhdtrKeyTyped(evt);
            }
        });
        jPanel35.add(txjmlhdtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, 30));

        jLabel92.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel92.setText("Harga Total");
        jPanel35.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 170, 20));

        txhrgttldtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txhrgttldtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel35.add(txhrgttldtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 170, 30));

        btminmndtr.setBackground(new java.awt.Color(175, 5, 30));
        btminmndtr.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btminmndtr.setForeground(new java.awt.Color(255, 255, 255));
        btminmndtr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/minmenu.png"))); // NOI18N
        btminmndtr.setText("Batalkan Orderan");
        btminmndtr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btminmndtr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btminmndtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btminmndtrActionPerformed(evt);
            }
        });
        jPanel35.add(btminmndtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 150, 30));

        btprosesmndtr.setBackground(new java.awt.Color(175, 5, 30));
        btprosesmndtr.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btprosesmndtr.setForeground(new java.awt.Color(255, 255, 255));
        btprosesmndtr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/admenu.png"))); // NOI18N
        btprosesmndtr.setText("Proses Orderan");
        btprosesmndtr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btprosesmndtr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btprosesmndtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprosesmndtrActionPerformed(evt);
            }
        });
        jPanel35.add(btprosesmndtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 150, 30));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel35.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 220, 40));

        jPanel6.setBackground(new java.awt.Color(175, 5, 30));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setText("Form Pesanan");
        jPanel6.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 40));

        jPanel35.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 40));

        txidmndtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidmndtr.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txidmndtr.setFocusable(false);
        jPanel35.add(txidmndtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, 90, 30));

        txidpgntrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidpgntrs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txidpgntrs.setFocusable(false);
        jPanel35.add(txidpgntrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 90, 30));

        txidmjtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidmjtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txidmjtrs.setFocusable(false);
        jPanel35.add(txidmjtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 90, 30));

        txidplgtrs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txidplgtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txidplgtrs.setFocusable(false);
        jPanel35.add(txidplgtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 90, 30));

        txstkdtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txstkdtr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txstkdtr.setFocusable(false);
        jPanel35.add(txstkdtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 90, 30));

        btendtrs.setBackground(new java.awt.Color(175, 5, 30));
        btendtrs.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btendtrs.setForeground(new java.awt.Color(255, 255, 255));
        btendtrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/sav.png"))); // NOI18N
        btendtrs.setText("Selesai");
        btendtrs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btendtrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btendtrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btendtrsActionPerformed(evt);
            }
        });
        jPanel35.add(btendtrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 150, 50));

        txiddtr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txiddtr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txiddtr.setFocusable(false);
        jPanel35.add(txiddtr, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 90, 30));

        jScrollPane17.setViewportView(jPanel35);

        jPanel34.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 220, 450));

        tbdtr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbdtr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbdtrFocusLost(evt);
            }
        });
        tbdtr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdtrMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbdtr);

        jPanel34.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 550, 160));

        jLabel101.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setText("Tabel Pesanan");
        jPanel34.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 460, 50));

        jTabbedPane1.addTab("Pesanan", jPanel34);

        formtransaksi.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 770, 500));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        formtransaksi.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 660));

        master.addTab("Transaksi", formtransaksi);

        report.setBackground(new java.awt.Color(255, 255, 255));
        report.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(230, 0, 48));

        jLabel87.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("HISTORI TRANSAKSI");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        report.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1090, 70));

        jPanel32.setBackground(new java.awt.Color(230, 0, 48));
        jPanel32.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txsrctrs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txsrctrs.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel32.add(txsrctrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 610, 30));

        jLabel96.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Tabel Transaksi");
        jPanel32.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 30));

        btsrctrs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/srch.png"))); // NOI18N
        btsrctrs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsrctrs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsrctrsMouseClicked(evt);
            }
        });
        jPanel32.add(btsrctrs, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 30, 30));

        tbtrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbtrs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tbtrsFocusLost(evt);
            }
        });
        jScrollPane21.setViewportView(tbtrs);

        jPanel32.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1050, 410));

        jLabel102.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Sampai");
        jPanel32.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 50, 30));

        jLabel103.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Dari");
        jPanel32.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 40, 30));

        jdc1.setDateFormatString("yyyy-MM-dd");
        jPanel32.add(jdc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 130, 30));

        jdc2.setDateFormatString("yyyy-MM-dd");
        jPanel32.add(jdc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 50, 130, 30));

        report.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 1050, 500));

        btprinttrs1.setBackground(new java.awt.Color(175, 5, 30));
        btprinttrs1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 13)); // NOI18N
        btprinttrs1.setForeground(new java.awt.Color(255, 255, 255));
        btprinttrs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/print.png"))); // NOI18N
        btprinttrs1.setText("Cetak Data");
        btprinttrs1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btprinttrs1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btprinttrs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprinttrs1ActionPerformed(evt);
            }
        });
        report.add(btprinttrs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 110, 40));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bg.png"))); // NOI18N
        report.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 660));

        master.addTab("Report", report);

        bg.add(master, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 1100, 690));
        master.getAccessibleContext().setAccessibleName("Beranda");

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 1310, 690));

        jMenu2.setText("Data Master");

        mnitmn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mnitmn.setText("Data Menu");
        mnitmn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitmnActionPerformed(evt);
            }
        });
        jMenu2.add(mnitmn);

        mnitmj.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mnitmj.setText("Data Meja");
        mnitmj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitmjActionPerformed(evt);
            }
        });
        jMenu2.add(mnitmj);

        mnitpgw.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnitpgw.setText("Data Pegawai");
        mnitpgw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitpgwActionPerformed(evt);
            }
        });
        jMenu2.add(mnitpgw);

        mnitplg.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnitplg.setText("Data Pelanggan");
        mnitplg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitplgActionPerformed(evt);
            }
        });
        jMenu2.add(mnitplg);

        mnitpgn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mnitpgn.setText("Data Pengguna");
        mnitpgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitpgnActionPerformed(evt);
            }
        });
        jMenu2.add(mnitpgn);

        mnithome.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mnithome.setText("Beranda");
        mnithome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnithomeActionPerformed(evt);
            }
        });
        jMenu2.add(mnithome);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Aktivitas");

        mnittrs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        mnittrs.setText("Transaksi");
        mnittrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnittrsActionPerformed(evt);
            }
        });
        jMenu3.add(mnittrs);

        mnitrpt.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        mnitrpt.setText("Report Transaksi");
        mnitrpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitrptActionPerformed(evt);
            }
        });
        jMenu3.add(mnitrpt);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Log");

        mnitout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        mnitout.setText("Log Out");
        mnitout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnitoutActionPerformed(evt);
            }
        });
        jMenu4.add(mnitout);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //FUNGSI APLIKASI
    //<editor-fold defaultstate="collapsed"  desc="Method untuk event yang terjadi saat user berinteraksi dengan aplikasi">
    
    //FUNGSI SIDEBAR
    //<editor-fold defaultstate="collapsed">
    private void menuoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuoutMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin melakukan Log out?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            new formlogin().setVisible(true);
            this.dispose();
        }else{
            
        }
    }//GEN-LAST:event_menuoutMouseClicked

    private void menumnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menumnMouseClicked
        master.setSelectedIndex(1);
        setbg(menumn, selmn);
        resetmenu(menuhome, menumj, menupgw, menuplg, menupgn, menutrs, menurpt, selmj, selpgw, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_menumnMouseClicked

    private void menumjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menumjMouseClicked
        master.setSelectedIndex(2);
        setbg(menumj, selmj);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menutrs, menurpt, selmn, selpgw, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_menumjMouseClicked

    private void menupgwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menupgwMouseClicked
        master.setSelectedIndex(3);
        setbg(menupgw, selpgw);
        resetmenu(menuhome, menumn, menumj, menuplg, menupgn, menutrs, menurpt, selmj, selmn, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_menupgwMouseClicked

    private void menuplgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuplgMouseClicked
        master.setSelectedIndex(4);
        setbg(menuplg, selplg);
        resetmenu(menuhome, menumn, menupgw, menumj, menupgn, menutrs, menurpt, selmj, selpgw, selmn, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_menuplgMouseClicked

    private void menupgnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menupgnMouseClicked
        master.setSelectedIndex(5);
        setbg(menupgn, selpgn);
        resetmenu(menuhome, menumn, menupgw, menuplg, menumj, menutrs, menurpt, selmj, selpgw, selplg, selmn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_menupgnMouseClicked

    private void menutrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menutrsMouseClicked
        master.setSelectedIndex(6);
        setbg(menutrs, seltrs);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menumj, menurpt, selmj, selpgw, selplg, selpgn, selhm, selmn, selrpt);
    }//GEN-LAST:event_menutrsMouseClicked

    private void menurptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menurptMouseClicked
        master.setSelectedIndex(7);
        setbg(menurpt, selrpt);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menutrs, menumj, selmj, selpgw, selplg, selpgn, selhm, seltrs, selmn);
    }//GEN-LAST:event_menurptMouseClicked

    private void menuhomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuhomeMouseClicked
        master.setSelectedIndex(0);
        setbg(menuhome, selhm);
        resetmenu(menumj, menumn, menupgw, menuplg, menupgn, menutrs, menurpt, selmj, selpgw, selplg, selpgn, selmn, seltrs, selrpt);
    }//GEN-LAST:event_menuhomeMouseClicked
    //</editor-fold>
    
    //FORM PEGAWAI
    //<editor-fold defaultstate="collapsed">
    private void btsrcpgwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcpgwMouseClicked
        searchpgw(txsrcpgw, mdpgw);
    }//GEN-LAST:event_btsrcpgwMouseClicked
                                                            
    private void btadpgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadpgwActionPerformed
        actpgw();
        btedpgw.setEnabled(false);
        btdepgw.setEnabled(false);
    }//GEN-LAST:event_btadpgwActionPerformed

    private void btsvpgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsvpgwActionPerformed
        savepgw();
        nonactpgw();
        emptypgw();
    }//GEN-LAST:event_btsvpgwActionPerformed

    private void btedpgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedpgwActionPerformed
        editpgw();
        nonactpgw();
        emptypgw();
    }//GEN-LAST:event_btedpgwActionPerformed

    private void btdepgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdepgwActionPerformed
        deletepgw();
        nonactpgw();
        emptypgw();
    }//GEN-LAST:event_btdepgwActionPerformed
    //</editor-fold>

    //FORM MENU
    //<editor-fold defaultstate="collapsed">
    private void btadmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadmnActionPerformed
        actmn();
        btedmn.setEnabled(false);
        btdemn.setEnabled(false);
    }//GEN-LAST:event_btadmnActionPerformed

    private void btsvmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsvmnActionPerformed
        savemn();
        nonactmn();
        emptymn();
    }//GEN-LAST:event_btsvmnActionPerformed

    private void btedmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedmnActionPerformed
        editmn();
        nonactmn();
        emptymn();
    }//GEN-LAST:event_btedmnActionPerformed

    private void btdemnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdemnActionPerformed
        deletemn();
        nonactmn();
        emptymn();
    }//GEN-LAST:event_btdemnActionPerformed

    private void btsrcmnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcmnMouseClicked
        searchmn(txsrcmn, mdmn);
    }//GEN-LAST:event_btsrcmnMouseClicked
    //</editor-fold>

    //FORM MEJA
    //<editor-fold defaultstate="collapsed">
    private void btsvmjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsvmjActionPerformed
        savemj();
        nonactmj();
        emptymj();
    }//GEN-LAST:event_btsvmjActionPerformed

    private void btedmjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedmjActionPerformed
        editmj();
        nonactmj();
        emptymj();
    }//GEN-LAST:event_btedmjActionPerformed

    private void btdemjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdemjActionPerformed
        deletemj();
        nonactmj();
        emptymj();
    }//GEN-LAST:event_btdemjActionPerformed

    private void btadmjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadmjActionPerformed
        actmj();
        btedmj.setEnabled(false);
        btdemj.setEnabled(false);
    }//GEN-LAST:event_btadmjActionPerformed

    private void btsrcmjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcmjMouseClicked
        searchmj(txsrcmj, mdmj);
    }//GEN-LAST:event_btsrcmjMouseClicked
    //</editor-fold>

    //FORM PELANGGAN
    //<editor-fold defaultstate="collapsed">
    private void btsvplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsvplgActionPerformed
        saveplg();
        nonactplg();
        emptyplg();
    }//GEN-LAST:event_btsvplgActionPerformed

    private void btedplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedplgActionPerformed
        editplg();
        nonactplg();
        emptyplg();
    }//GEN-LAST:event_btedplgActionPerformed

    private void btdeplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeplgActionPerformed
        deleteplg();
        nonactplg();
        emptyplg();
    }//GEN-LAST:event_btdeplgActionPerformed

    private void btadplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadplgActionPerformed
        actplg();
        btedplg.setEnabled(false);
        btdeplg.setEnabled(false);
    }//GEN-LAST:event_btadplgActionPerformed

    private void btsrcplgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcplgMouseClicked
        searchplg(txsrcplg, mdplg);
    }//GEN-LAST:event_btsrcplgMouseClicked
    //</editor-fold>

    //FORM PENGGUNA
    //<editor-fold defaultstate="collapsed">
    private void btsvpgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsvpgnActionPerformed
        savepgn();
        nonactpgn();
        emptypgn();
    }//GEN-LAST:event_btsvpgnActionPerformed

    private void btedpgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btedpgnActionPerformed
        editpgn();
        nonactpgn();
        emptypgn();
    }//GEN-LAST:event_btedpgnActionPerformed

    private void btdepgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdepgnActionPerformed
        deletepgn();
        nonactpgn();
        emptypgn();
    }//GEN-LAST:event_btdepgnActionPerformed

    private void btadpgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadpgnActionPerformed
        actpgn();
        tbpgwi.setEnabled(true);
        tbpgn.setEnabled(false);
        btedpgn.setEnabled(false);
        btdepgn.setEnabled(false);
    }//GEN-LAST:event_btadpgnActionPerformed

    private void btsrcpgnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcpgnMouseClicked
        searchpgn();
    }//GEN-LAST:event_btsrcpgnMouseClicked

    private void btsrcpgwiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcpgwiMouseClicked
        searchpgw(txsrcpgwi, mdpgwi);
    }//GEN-LAST:event_btsrcpgwiMouseClicked
    //</editor-fold>

    //TABEL
    //<editor-fold defaultstate="collapsed">
    private void tbmnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmnMouseClicked
        selectmn();
    }//GEN-LAST:event_tbmnMouseClicked

    private void tbmjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmjMouseClicked
        selectmj();
    }//GEN-LAST:event_tbmjMouseClicked

    private void tbpgwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpgwMouseClicked
        selectpgw();
    }//GEN-LAST:event_tbpgwMouseClicked

    private void tbplgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbplgMouseClicked
        selectplg();
    }//GEN-LAST:event_tbplgMouseClicked

    private void tbpgnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpgnMouseClicked
        selectpgn();
    }//GEN-LAST:event_tbpgnMouseClicked

    private void tbpgwiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpgwiMouseClicked
        selectpgwi();
    }//GEN-LAST:event_tbpgwiMouseClicked
    //</editor-fold>

    //BATAL INPUT
    //<editor-fold defaultstate="collapsed">
    private void btlmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlmnActionPerformed
        emptymn();
        nonactmn();
    }//GEN-LAST:event_btlmnActionPerformed

    private void btlmjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlmjActionPerformed
        emptymj();
        nonactmj();
    }//GEN-LAST:event_btlmjActionPerformed

    private void btlplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlplgActionPerformed
        emptyplg();
        nonactplg();
    }//GEN-LAST:event_btlplgActionPerformed

    private void btlpgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlpgnActionPerformed
        emptypgn();
        nonactpgn();
    }//GEN-LAST:event_btlpgnActionPerformed

    private void btlpgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlpgwActionPerformed
        emptypgw();
        nonactpgw();
    }//GEN-LAST:event_btlpgwActionPerformed
    //</editor-fold>

    //PLACEHOLDER
    //<editor-fold defaultstate="collapsed">
    private void txnmmnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmmnFocusGained
        clickplaceholder(txnmmn, "Masukan Nama Menu...");
    }//GEN-LAST:event_txnmmnFocusGained

    private void txnmmnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmmnFocusLost
        setplaceholder(txnmmn, "Masukan Nama Menu...");
    }//GEN-LAST:event_txnmmnFocusLost

    private void txhrgmnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txhrgmnFocusGained
        clickplaceholder(txhrgmn, "Masukan Harga Menu...");
    }//GEN-LAST:event_txhrgmnFocusGained

    private void txhrgmnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txhrgmnFocusLost
        setplaceholder(txhrgmn, "Masukan Harga Menu...");
    }//GEN-LAST:event_txhrgmnFocusLost

    private void txstkmnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txstkmnFocusGained
        clickplaceholder(txstkmn, "Masukan Stok Menu...");
    }//GEN-LAST:event_txstkmnFocusGained

    private void txstkmnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txstkmnFocusLost
        setplaceholder(txstkmn, "Masukan Stok Menu...");
    }//GEN-LAST:event_txstkmnFocusLost

    private void txnmpgwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmpgwFocusGained
        clickplaceholder(txnmpgw, "Masukan Nama Pegawai...");
    }//GEN-LAST:event_txnmpgwFocusGained

    private void txnmpgwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmpgwFocusLost
        setplaceholder(txnmpgw, "Masukan Nama Pegawai...");
    }//GEN-LAST:event_txnmpgwFocusLost

    private void txtmptlhrpgwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmptlhrpgwFocusGained
        clickplaceholder(txtmptlhrpgw, "Masukan Tempat Lahir Pegawai...");
    }//GEN-LAST:event_txtmptlhrpgwFocusGained

    private void txtmptlhrpgwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmptlhrpgwFocusLost
        setplaceholder(txtmptlhrpgw, "Masukan Tempat Lahir Pegawai...");
    }//GEN-LAST:event_txtmptlhrpgwFocusLost

    private void txnohppgwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnohppgwFocusGained
        clickplaceholder(txnohppgw, "Masukan No. Telepon Pegawai...");
    }//GEN-LAST:event_txnohppgwFocusGained

    private void txnohppgwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnohppgwFocusLost
        setplaceholder(txnohppgw, "Masukan No. Telepon Pegawai...");
    }//GEN-LAST:event_txnohppgwFocusLost

    private void txnmplgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmplgFocusGained
        clickplaceholder(txnmplg, "Masukan Nama Pelanggan...");
    }//GEN-LAST:event_txnmplgFocusGained

    private void txnmplgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnmplgFocusLost
        setplaceholder(txnmplg, "Masukan Nama Pelanggan...");
    }//GEN-LAST:event_txnmplgFocusLost

    private void txtmptlhrplgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmptlhrplgFocusGained
        clickplaceholder(txtmptlhrplg, "Masukan Tempat Lahir Pelanggan...");
    }//GEN-LAST:event_txtmptlhrplgFocusGained

    private void txtmptlhrplgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmptlhrplgFocusLost
        setplaceholder(txtmptlhrplg, "Masukan Tempat Lahir Pelanggan...");
    }//GEN-LAST:event_txtmptlhrplgFocusLost

    private void txnohpplgFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnohpplgFocusGained
        clickplaceholder(txnohpplg, "Masukan No. Telepon Pelanggan...");
    }//GEN-LAST:event_txnohpplgFocusGained

    private void txnohpplgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txnohpplgFocusLost
        setplaceholder(txnohpplg, "Masukan No. Telepon Pelanggan...");
    }//GEN-LAST:event_txnohpplgFocusLost

    private void txpwpgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txpwpgnFocusGained
        clickplaceholder(txpwpgn, "Masukan Password Pengguna...");
    }//GEN-LAST:event_txpwpgnFocusGained

    private void txpwpgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txpwpgnFocusLost
        setplaceholder(txpwpgn, "Masukan Password Pengguna...");
    }//GEN-LAST:event_txpwpgnFocusLost

    private void txusnmpgnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txusnmpgnFocusGained
        clickplaceholder(txusnmpgn, "Masukan Username Pengguna...");
    }//GEN-LAST:event_txusnmpgnFocusGained

    private void txusnmpgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txusnmpgnFocusLost
        setplaceholder(txusnmpgn, "Masukan Username Pengguna...");
    }//GEN-LAST:event_txusnmpgnFocusLost
    //</editor-fold>

    //FUNGSI MENU BAR
    //<editor-fold defaultstate="collapsed">
    private void mnitoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin melakukan Log out?", "Konfirmasi",JOptionPane.OK_CANCEL_OPTION);
        if(confirm == JOptionPane.OK_OPTION){
            new formlogin().setVisible(true);
            this.dispose();
        }else{
            
        }
    }//GEN-LAST:event_mnitoutActionPerformed

    private void mnitrptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitrptActionPerformed
        master.setSelectedIndex(7);
        setbg(menurpt, selrpt);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menutrs, menumj, selmj, selpgw, selplg, selpgn, selhm, seltrs, selmn);
    }//GEN-LAST:event_mnitrptActionPerformed

    private void mnittrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnittrsActionPerformed
        master.setSelectedIndex(6);
        setbg(menutrs, seltrs);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menumj, menurpt, selmj, selpgw, selplg, selpgn, selhm, selmn, selrpt);
    }//GEN-LAST:event_mnittrsActionPerformed

    private void mnitpgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitpgnActionPerformed
        master.setSelectedIndex(5);
        setbg(menupgn, selpgn);
        resetmenu(menuhome, menumn, menupgw, menuplg, menumj, menutrs, menurpt, selmj, selpgw, selplg, selmn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_mnitpgnActionPerformed

    private void mnitplgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitplgActionPerformed
        master.setSelectedIndex(4);
        setbg(menuplg, selplg);
        resetmenu(menuhome, menumn, menupgw, menumj, menupgn, menutrs, menurpt, selmj, selpgw, selmn, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_mnitplgActionPerformed

    private void mnitpgwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitpgwActionPerformed
        master.setSelectedIndex(3);
        setbg(menupgw, selpgw);
        resetmenu(menuhome, menumn, menumj, menuplg, menupgn, menutrs, menurpt, selmj, selmn, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_mnitpgwActionPerformed

    private void mnitmjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitmjActionPerformed
        master.setSelectedIndex(2);
        setbg(menumj, selmj);
        resetmenu(menuhome, menumn, menupgw, menuplg, menupgn, menutrs, menurpt, selmn, selpgw, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_mnitmjActionPerformed

    private void mnitmnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnitmnActionPerformed
        master.setSelectedIndex(1);
        setbg(menumn, selmn);
        resetmenu(menuhome, menumj, menupgw, menuplg, menupgn, menutrs, menurpt, selmj, selpgw, selplg, selpgn, selhm, seltrs, selrpt);
    }//GEN-LAST:event_mnitmnActionPerformed

    private void mnithomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnithomeActionPerformed
        master.setSelectedIndex(0);
        setbg(menuhome, selhm);
        resetmenu(menumn, menumj, menupgw, menuplg, menupgn, menutrs, menurpt, selmj, selpgw, selplg, selpgn, selmn, seltrs, selrpt);
    }//GEN-LAST:event_mnithomeActionPerformed
    //</editor-fold>

    //LIMITASI TEKS
    //<editor-fold defaultstate="collapsed">
    private void txnmmnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnmmnKeyTyped
        txlimit(evt, txnmmn, 20);
    }//GEN-LAST:event_txnmmnKeyTyped

    private void txhrgmnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txhrgmnKeyTyped
        txnumb(evt);
        txlimit(evt, txhrgmn, 10);
    }//GEN-LAST:event_txhrgmnKeyTyped

    private void txstkmnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txstkmnKeyTyped
        txnumb(evt);
        txlimit(evt, txstkmn, 10);
    }//GEN-LAST:event_txstkmnKeyTyped

    private void txnmpgwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnmpgwKeyTyped
        txlimit(evt, txnmpgw, 40);
    }//GEN-LAST:event_txnmpgwKeyTyped

    private void txtmptlhrpgwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmptlhrpgwKeyTyped
        txlimit(evt, txtmptlhrpgw, 30);
    }//GEN-LAST:event_txtmptlhrpgwKeyTyped

    private void txnohppgwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnohppgwKeyTyped
        txnumb(evt);
        txlimit(evt, txnohppgw, 20);
    }//GEN-LAST:event_txnohppgwKeyTyped

    private void txalmtpgwKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txalmtpgwKeyTyped
        talimit(evt, txalmtpgw, 255);
    }//GEN-LAST:event_txalmtpgwKeyTyped

    private void txnmplgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnmplgKeyTyped
        txlimit(evt, txnmplg, 40);
    }//GEN-LAST:event_txnmplgKeyTyped

    private void txtmptlhrplgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmptlhrplgKeyTyped
        txlimit(evt, txtmptlhrplg, 30);
    }//GEN-LAST:event_txtmptlhrplgKeyTyped

    private void txnohpplgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txnohpplgKeyTyped
        txnumb(evt);
        txlimit(evt, txnohpplg, 20);
    }//GEN-LAST:event_txnohpplgKeyTyped

    private void txalmtplgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txalmtplgKeyTyped
        talimit(evt, txalmtplg, 255);
    }//GEN-LAST:event_txalmtplgKeyTyped

    private void txpwpgnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txpwpgnKeyTyped
        txlimit(evt, txpwpgn, 255);
    }//GEN-LAST:event_txpwpgnKeyTyped

    //</editor-fold>
    
    //TRANSAKSI
    //<editor-fold defaultstate="collapsed">

    private void txjmlhdtrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjmlhdtrKeyReleased
        try{
        int hrgdtr, jmlhdtr, hrgttldtr;
        hrgdtr = Integer.parseInt(txhrgdtr.getText());
        jmlhdtr = Integer.parseInt(txjmlhdtr.getText());
        hrgttldtr = hrgdtr * jmlhdtr;
        txhrgttldtr.setText("" + hrgttldtr);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txjmlhdtrKeyReleased

    private void btsrcmntrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcmntrsMouseClicked
        searchmntrs(txsrcmntrs, mdmntrs);
    }//GEN-LAST:event_btsrcmntrsMouseClicked

    private void btsrcplgtrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcplgtrsMouseClicked
        searchplg(txsrcplgtrs, mdplgtrs);
    }//GEN-LAST:event_btsrcplgtrsMouseClicked

    private void btsrcmjtrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrcmjtrsMouseClicked
        searchmjtrs(txsrcmjtrs, mdmjtrs);
    }//GEN-LAST:event_btsrcmjtrsMouseClicked

    private void tbplgtrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbplgtrsMouseClicked
        selectplgtrs();
    }//GEN-LAST:event_tbplgtrsMouseClicked

    private void tbmjtrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmjtrsMouseClicked
        if (lvlpgnlog.equalsIgnoreCase("Administrator")) {
            selectmjtrs();
            idautotrs();
        } else if (lvlpgnlog.equalsIgnoreCase("Kasir")) {
            selectmjtrs();
            idautotrs();
        } else if (lvlpgnlog.equalsIgnoreCase("Pelayan")) {
        }
    }//GEN-LAST:event_tbmjtrsMouseClicked

    private void tbmntrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbmntrsMouseClicked
        if (lvlpgnlog.equalsIgnoreCase("Administrator")) {
            selectmndtr();
            txjmlhdtr.setEnabled(true);
            txjmlhdtr.requestFocus();
            btprosesmndtr.setEnabled(true);
            btminmndtr.setEnabled(false);
        } else if (lvlpgnlog.equalsIgnoreCase("Kasir")) {
            selectmndtr();
            txjmlhdtr.setEnabled(true);
            txjmlhdtr.requestFocus();
            btprosesmndtr.setEnabled(true);
            btminmndtr.setEnabled(false);
        } else if (lvlpgnlog.equalsIgnoreCase("Pelayan")) {
        }
    }//GEN-LAST:event_tbmntrsMouseClicked

    private void btprosesmndtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprosesmndtrActionPerformed
        savedtr();
        updatestock();
        updatemj();
        btnoff(btminmndtr);
        btnon(btendtrs);
    }//GEN-LAST:event_btprosesmndtrActionPerformed

    private void tbdtrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdtrMouseClicked
        selectdtr();
        btminmndtr.setEnabled(true);
        btprosesmndtr.setEnabled(false);
    }//GEN-LAST:event_tbdtrMouseClicked

    private void txbyrtrsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbyrtrsKeyReleased
        try{
        int ttlbyrtrs, byrtrs, kmbltrs;
        ttlbyrtrs = Integer.parseInt(txttlbyrtrs.getText());
        byrtrs = Integer.parseInt(txbyrtrs.getText());
        kmbltrs = byrtrs - ttlbyrtrs;
        txkmbltrs.setText("" + kmbltrs);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txbyrtrsKeyReleased

    private void btadtrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btadtrsActionPerformed
        txempty(txnmplgtrs, txnmmjtrs, txidtrs);
        btnoff(btadtrs, btprinttrs);
        txoff(txbyrtrs);
        idautotrs();
        txttlbyrtrs.setText("0");
        txbyrtrs.setText("0");
        txkmbltrs.setText("0");
    }//GEN-LAST:event_btadtrsActionPerformed

    private void btminmndtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btminmndtrActionPerformed
        if(tbdtr.getRowCount()>1){
        mintrs();
        getdtr();
        emptytrs();
        btminmndtr.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(this, "Tambahkan pesanan lain terlebih dahulu");
        }
        btprosesmndtr.setEnabled(false);
    }//GEN-LAST:event_btminmndtrActionPerformed

    private void btendtrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btendtrsActionPerformed
        int total = 0;
        for (int i = 0; i < tbdtr.getRowCount(); i++){
            int amount = Integer.parseInt((String)tbdtr.getValueAt(i, 6));
            total += amount;
        }
        txttlbyrtrs.setText(""+total);
        txon(txbyrtrs);
        btnon(btprinttrs);
        btnoff(btprosesmndtr, btminmndtr, btendtrs);
        txbyrtrs.requestFocus();
    }//GEN-LAST:event_btendtrsActionPerformed

    private void btprinttrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprinttrsActionPerformed
        //end();
        ubahdata();
        //simpandatatransaksi();
        JasperReport jasperReport;
        JasperDesign jasperDesign;
        JasperPrint jasperPrint;
        Map<String,Object>parameter = new HashMap<String, Object>();
        JasperDesign jasDes;
         try {
            parameter.put("idtransaksi",txidtrs.getText());
            File file = new File("src/Report/reporttrs.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn.connDb());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
         btnon(btadtrs);
         btnoff(btendtrs);
    }//GEN-LAST:event_btprinttrsActionPerformed

    private void txjmlhdtrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txjmlhdtrKeyTyped
        txnumb(evt);
    }//GEN-LAST:event_txjmlhdtrKeyTyped

    private void txbyrtrsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txbyrtrsKeyTyped
        txnumb(evt);
    }//GEN-LAST:event_txbyrtrsKeyTyped

    private void tbmnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbmnFocusLost
        tbmn.setSelectionMode(0);
    }//GEN-LAST:event_tbmnFocusLost

    private void tbmjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbmjFocusLost
        tbmj.setSelectionMode(0);
    }//GEN-LAST:event_tbmjFocusLost

    private void tbpgwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbpgwFocusLost
        tbpgw.setSelectionMode(0);
    }//GEN-LAST:event_tbpgwFocusLost

    private void tbplgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbplgFocusLost
        tbplg.setSelectionMode(0);
    }//GEN-LAST:event_tbplgFocusLost

    private void tbpgnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbpgnFocusLost
        tbpgn.setSelectionMode(0);
    }//GEN-LAST:event_tbpgnFocusLost

    private void tbpgwiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbpgwiFocusLost
        tbpgwi.setSelectionMode(0);
    }//GEN-LAST:event_tbpgwiFocusLost

    private void tbplgtrsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbplgtrsFocusLost
        tbplgtrs.setSelectionMode(0);
    }//GEN-LAST:event_tbplgtrsFocusLost

    private void tbmjtrsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbmjtrsFocusLost
        tbmjtrs.setSelectionMode(0);
    }//GEN-LAST:event_tbmjtrsFocusLost

    private void tbmntrsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbmntrsFocusLost
        tbmntrs.setSelectionMode(0);
    }//GEN-LAST:event_tbmntrsFocusLost

    private void tbdtrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbdtrFocusLost
        tbdtr.setSelectionMode(0);
    }//GEN-LAST:event_tbdtrFocusLost

    private void btprinttrs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprinttrs1ActionPerformed
        printhistori("historitrs.jasper");
    }//GEN-LAST:event_btprinttrs1ActionPerformed

    private void tbtrsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbtrsFocusLost
        tbtrs.setSelectionMode(0);
    }//GEN-LAST:event_tbtrsFocusLost

    private void btsrctrsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsrctrsMouseClicked
        srchistori();
    }//GEN-LAST:event_btsrctrsMouseClicked

    private void btaboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btaboutMouseClicked
    }//GEN-LAST:event_btaboutMouseClicked

    private void txusnmpgnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txusnmpgnKeyTyped
        txlimit(evt, txusnmpgn, 10);
    }//GEN-LAST:event_txusnmpgnKeyTyped
    
    //</editor-fold>

    //</editor-fold>

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
            java.util.logging.Logger.getLogger(formmaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formmaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formmaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formmaster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formmaster().setVisible(true);
            }
        });
    }
    
    //DEKLARASI VARIABEL
    //<editor-fold defaultstate="collapsed" desc="Deklarasi variabel komponen pada aplikasi">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel beranda;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel btabout;
    private javax.swing.JButton btadmj;
    private javax.swing.JButton btadmn;
    private javax.swing.JButton btadpgn;
    private javax.swing.JButton btadpgw;
    private javax.swing.JButton btadplg;
    private javax.swing.JButton btadtrs;
    private javax.swing.JButton btdemj;
    private javax.swing.JButton btdemn;
    private javax.swing.JButton btdepgn;
    private javax.swing.JButton btdepgw;
    private javax.swing.JButton btdeplg;
    private javax.swing.JButton btedmj;
    private javax.swing.JButton btedmn;
    private javax.swing.JButton btedpgn;
    private javax.swing.JButton btedpgw;
    private javax.swing.JButton btedplg;
    private javax.swing.JButton btendtrs;
    private javax.swing.JButton btlmj;
    private javax.swing.JButton btlmn;
    private javax.swing.JButton btlpgn;
    private javax.swing.JButton btlpgw;
    private javax.swing.JButton btlplg;
    private javax.swing.JButton btminmndtr;
    private javax.swing.JButton btprinttrs;
    private javax.swing.JButton btprinttrs1;
    private javax.swing.JButton btprosesmndtr;
    private javax.swing.JLabel btsrcmj;
    private javax.swing.JLabel btsrcmjtrs;
    private javax.swing.JLabel btsrcmn;
    private javax.swing.JLabel btsrcmntrs;
    private javax.swing.JLabel btsrcpgn;
    private javax.swing.JLabel btsrcpgw;
    private javax.swing.JLabel btsrcpgwi;
    private javax.swing.JLabel btsrcplg;
    private javax.swing.JLabel btsrcplgtrs;
    private javax.swing.JLabel btsrctrs;
    private javax.swing.JButton btsvmj;
    private javax.swing.JButton btsvmn;
    private javax.swing.JButton btsvpgn;
    private javax.swing.JButton btsvpgw;
    private javax.swing.JButton btsvplg;
    private javax.swing.JComboBox<String> cbjobpgw;
    private javax.swing.JComboBox<String> cbktgmj;
    private javax.swing.JComboBox<String> cbktgmn;
    private javax.swing.JComboBox<String> cblvlpgn;
    private javax.swing.JComboBox<String> cbsttsmj;
    private javax.swing.JComboBox<String> cbsxpgw;
    private javax.swing.JComboBox<String> cbsxplg;
    private javax.swing.JPanel formmeja;
    private javax.swing.JPanel formmenu;
    private javax.swing.JPanel formpegawai;
    private javax.swing.JPanel formpelanggan;
    private javax.swing.JPanel formpengguna;
    private javax.swing.JPanel formtransaksi;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdc1;
    private com.toedter.calendar.JDateChooser jdc2;
    private com.toedter.calendar.JDateChooser jdctgllhrpgw;
    private com.toedter.calendar.JDateChooser jdctgllhrplg;
    private javax.swing.JLabel lblvlpgnhm;
    private javax.swing.JLabel lbnmpgnhm;
    private javax.swing.JLabel lbtglhm;
    private javax.swing.JLabel lbusnmpgnhm;
    private javax.swing.JLabel lbwelcome;
    private javax.swing.JLabel lbwkthm;
    private javax.swing.JTabbedPane master;
    private javax.swing.JPanel menuhome;
    private javax.swing.JPanel menumj;
    private javax.swing.JPanel menumn;
    private javax.swing.JPanel menuout;
    private javax.swing.JPanel menupgn;
    private javax.swing.JPanel menupgw;
    private javax.swing.JPanel menuplg;
    private javax.swing.JPanel menurpt;
    private javax.swing.JPanel menutrs;
    private javax.swing.JMenuItem mnithome;
    private javax.swing.JMenuItem mnitmj;
    private javax.swing.JMenuItem mnitmn;
    private javax.swing.JMenuItem mnitout;
    private javax.swing.JMenuItem mnitpgn;
    private javax.swing.JMenuItem mnitpgw;
    private javax.swing.JMenuItem mnitplg;
    private javax.swing.JMenuItem mnitrpt;
    private javax.swing.JMenuItem mnittrs;
    private javax.swing.JPanel report;
    private javax.swing.JPanel selhm;
    private javax.swing.JPanel selmj;
    private javax.swing.JPanel selmn;
    private javax.swing.JPanel selpgn;
    private javax.swing.JPanel selpgw;
    private javax.swing.JPanel selplg;
    private javax.swing.JPanel selrpt;
    private javax.swing.JPanel selrpt1;
    private javax.swing.JPanel seltrs;
    private javax.swing.JPanel sidepanel;
    private javax.swing.JTable tbdtr;
    private javax.swing.JTable tbmj;
    private javax.swing.JTable tbmjtrs;
    private javax.swing.JTable tbmn;
    private javax.swing.JTable tbmntrs;
    private javax.swing.JTable tbpgn;
    private javax.swing.JTable tbpgw;
    private javax.swing.JTable tbpgwi;
    private javax.swing.JTable tbplg;
    private javax.swing.JTable tbplgtrs;
    private javax.swing.JTable tbtrs;
    private javax.swing.JTextArea txalmtpgw;
    private javax.swing.JTextArea txalmtplg;
    private javax.swing.JTextField txbyrtrs;
    private javax.swing.JTextField txhrgdtr;
    private javax.swing.JTextField txhrgmn;
    private javax.swing.JTextField txhrgttldtr;
    private javax.swing.JTextField txiddtr;
    private javax.swing.JTextField txidmj;
    private javax.swing.JTextField txidmjtrs;
    private javax.swing.JTextField txidmn;
    private javax.swing.JTextField txidmndtr;
    private javax.swing.JTextField txidpgn;
    private javax.swing.JTextField txidpgntrs;
    private javax.swing.JTextField txidpgw;
    private javax.swing.JTextField txidpgwpgn;
    private javax.swing.JTextField txidplg;
    private javax.swing.JTextField txidplgtrs;
    private javax.swing.JTextField txidtrs;
    private javax.swing.JTextField txjmlhdtr;
    private javax.swing.JTextField txkmbltrs;
    private javax.swing.JTextField txnmmj;
    private javax.swing.JTextField txnmmjtrs;
    private javax.swing.JTextField txnmmn;
    private javax.swing.JTextField txnmmndtr;
    private javax.swing.JTextField txnmpgn;
    private javax.swing.JTextField txnmpgntrs;
    private javax.swing.JTextField txnmpgw;
    private javax.swing.JTextField txnmplg;
    private javax.swing.JTextField txnmplgtrs;
    private javax.swing.JTextField txnohppgw;
    private javax.swing.JTextField txnohpplg;
    private javax.swing.JTextField txpwpgn;
    private javax.swing.JTextField txsrcmj;
    private javax.swing.JTextField txsrcmjtrs;
    private javax.swing.JTextField txsrcmn;
    private javax.swing.JTextField txsrcmntrs;
    private javax.swing.JTextField txsrcpgn;
    private javax.swing.JTextField txsrcpgw;
    private javax.swing.JTextField txsrcpgwi;
    private javax.swing.JTextField txsrcplg;
    private javax.swing.JTextField txsrcplgtrs;
    private javax.swing.JTextField txsrctrs;
    private javax.swing.JTextField txstkdtr;
    private javax.swing.JTextField txstkmn;
    private javax.swing.JTextField txtgltrs;
    private javax.swing.JTextField txtmptlhrpgw;
    private javax.swing.JTextField txtmptlhrplg;
    private javax.swing.JTextField txttlbyrtrs;
    private javax.swing.JTextField txusnmpgn;
    private javax.swing.JTextField txwkttrs;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
//</editor-fold>