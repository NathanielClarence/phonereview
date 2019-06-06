/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import Entity.Smartphones;
import admin.Home;
import admin.ViewEdit;
import admin.EditDelete;
import admin.CommentsRatings;
import database.Database;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import user.UserHome;
/**
 *
 * @author David Immanuel
 */
public class ViewEdit extends javax.swing.JFrame {
    
    private String kodeSmartphone;
    private String lokasiGambar;
    private String ext;
    private String tampFileGambar;
    
    /**
     * @return the kodeSmartphone
     */
    public String getKodeSmartphone() {
        return kodeSmartphone;
    }

    /**
     * @param kodeSmartphone the kodeSmartphone to set
     */
    public void setKodeSmartphone(String kodeSmartphone) {
        this.kodeSmartphone = kodeSmartphone;
    }
    /**
     * @return the lokasiGambar
     */
    public String getLokasiGambar() {
        return lokasiGambar;
    }

    /**
     * @param lokasiGambar the lokasi_gambar to set
     */
    public void setLokasiGambar(String lokasiGambar) {
        this.lokasiGambar = lokasiGambar;
    }
    /**
     * Creates new form create
     */
    public ViewEdit() {
        initComponents();
    }
    
    public void showDetail(String kode_smartphone) throws ClassNotFoundException{
        this.setVisible(true);
        Smartphones smartphone = null;
        Database db = new Database();
        smartphone = db.fetchPost(kode_smartphone);
        this.tampFileGambar = smartphone.getGambar();
        this.setLokasiGambar(smartphone.getGambar());
        setKodeSmartphone(kode_smartphone);
        String nama_smartphone = smartphone.getNama();
        String gambar = "src/img-smartphone/" + smartphone.getGambar();
        String kode = kode_smartphone;
        String cpu = smartphone.getCpu();
        String gpu = smartphone.getGpu();
        int kamera_depan = smartphone.getKamera_depan();
        int kamera_belakang = smartphone.getKamera_belakang();
        int rom = smartphone.getRom();
        String status = smartphone.getStatus();
        String displaySize = smartphone.getDisplaySize();
        String displayType = smartphone.getDisplayType();
        String displayResolution = smartphone.getDisplayResolution();
        String network = smartphone.getNetwork();
        String os = smartphone.getOs();
        String chipset = smartphone.getChipset();
        String memoryCard = smartphone.getMemoryCard();
        String launchDate = smartphone.getLaunchDate();
        String sim = smartphone.getSim();
        int harga = smartphone.getHarga();
        String weight = smartphone.getWeight();
        String dimension = smartphone.getDimension();
        String merek = smartphone.getMerek();
        String model = smartphone.getModel();
        int version = smartphone.getVersion();
        
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(gambar));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Image newImg = img.getScaledInstance(imgLabel.getWidth(),imgLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(newImg);
        
        id_model.setText(model);
        id_merek.setText(merek);
        id_version.setText(Integer.toString(version));
        imgLabel.setIcon(imgIcon);
        id_judul.setText(nama_smartphone);
        id_announce.setText(launchDate);
        id_camPri.setText(Integer.toString(kamera_belakang));
        id_camSec.setText(Integer.toString(kamera_depan));
        id_chip.setText(chipset);
        id_status.setText(status);
        id_gpu.setText(gpu);
        id_cpu.setText(cpu);
        id_internal.setText(Integer.toString(rom));
        id_os.setText(os);
        id_size.setText(displaySize);
        id_res.setText(displayResolution);
        id_card.setText(memoryCard);
        id_sim.setText(sim);
        id_harga.setText(Integer.toString(harga));
        id_weight.setText(weight);
        id_dimension.setText(dimension);
        id_network.setText(network);
        id_type.setText(displayType);
        
        editBtn.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent event){
                try {
                    btnPostActionPerformed(event,kode_smartphone);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public boolean checkInput(){
        if(id_model.getText().trim().isEmpty() || id_merek.getText().trim().isEmpty() || id_version.getText().trim().isEmpty() || id_judul.getText().trim().isEmpty() || id_announce.getText().trim().isEmpty() || id_camPri.getText().trim().isEmpty() || id_camSec.getText().trim().isEmpty() || id_chip.getText().trim().isEmpty() || id_status.getText().trim().isEmpty() || id_gpu.getText().trim().isEmpty() || id_cpu.getText().trim().isEmpty() || id_internal.getText().trim().isEmpty() || id_os.getText().trim().isEmpty() || id_size.getText().trim().isEmpty() || id_res.getText().trim().isEmpty() || id_card.getText().trim().isEmpty() || id_sim.getText().trim().isEmpty() || id_harga.getText().trim().isEmpty() || id_weight.getText().trim().isEmpty() || id_dimension.getText().trim().isEmpty() || id_type.getText().trim().isEmpty() || id_network.getText().trim().isEmpty()){
            return false;
        }else if(id_model.getText() == " " || id_merek.getText() == " " || id_version.getText() == " " || id_judul.getText() == " " || id_announce.getText() == " " || id_camPri.getText() == " " || id_camSec.getText() == " " || id_chip.getText() == " " || id_status.getText() == " " || id_gpu.getText() == " " || id_cpu.getText() == " " || id_internal.getText() == " " || id_os.getText() == " " || id_size.getText() == " " || id_res.getText() == " " || id_card.getText() == " " || id_sim.getText() == " " || id_harga.getText() == " " || id_weight.getText() == " " || id_dimension.getText() == " " || id_type.getText() == " " || id_network.getText() == " "){
            return false;
        }else{
            return true;
        }
    }
    
    private void btnPostActionPerformed(ActionEvent event, String kode_smartphone) throws SQLException, ClassNotFoundException {
        //To change body of generated methods, choose Tools | Templates.
        boolean status = this.checkInput();
        if(status){
            // check input kamera depan & belakang 
            if (!id_camPri.getText().matches("[0-9]+")){
                id_camPri.requestFocus();
                JOptionPane.showMessageDialog(null, "Input Main Camera hanya berupa angka!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
            }else if(!id_camSec.getText().matches("[0-9]+")){
                id_camSec.requestFocus();
                JOptionPane.showMessageDialog(null, "Input Secondary Camera hanya berupa angka!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
            }else if(!id_version.getText().matches("[0-9]+")){
                id_version.requestFocus();
                JOptionPane.showMessageDialog(null, "Input Version hanya berupa angka!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
            }else if(!id_harga.getText().matches("[0-9]+")){
                id_harga.requestFocus();
                JOptionPane.showMessageDialog(null, "Input Harga hanya berupa angka!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
            }else if(!id_internal.getText().matches("[0-9]+")){
                id_internal.requestFocus();
                JOptionPane.showMessageDialog(null, "Input Memori Internal hanya berupa angka!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
            }else{
                Database db = new Database();
                //String nama, String gambar, String kode, String cpu, String gpu, int kamera_depan, int kamera_belakang, int rom,
                //String status, String displaySize, String displayType, String displayResolution, String network, String os, String chipset, String memoryCard,
                //String launchDate, String dimension, String weight, String sim, int harga, String merek, int version, String model
                String namaFile = "";
                if(this.getLokasiGambar() == this.tampFileGambar){
                    namaFile = this.tampFileGambar;
                }else{
                    namaFile = this.getKodeSmartphone() + "." + this.ext;
                }
                if(db.updateSmartphone(id_judul.getText(), namaFile , this.getKodeSmartphone(), id_cpu.getText(), id_gpu.getText(), Integer.parseInt(id_camSec.getText()), Integer.parseInt(id_camPri.getText()), Integer.parseInt(id_internal.getText()), id_status.getText(), id_size.getText(), id_type.getText(), id_res.getText(), id_network.getText(), id_os.getText(), id_chip.getText(), id_card.getText(), id_announce.getText(), id_dimension.getText(), id_weight.getText(), id_sim.getText(), Integer.parseInt(id_harga.getText()), id_merek.getText(), Integer.parseInt(id_version.getText()), id_model.getText())){
                    InputStream inStream = null;
                    OutputStream outStream = null;
                    if(this.getLokasiGambar() == this.tampFileGambar){
                        JOptionPane.showMessageDialog(null, "Berhasil mengupdate smartphone!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                        new EditDelete().setVisible(true);
                        this.dispose();
                    }else{
                        try{
                            String direktoriGambar = "src/img-smartphone/" + this.getKodeSmartphone() + "." + ext;
                            File currentDir = new File(this.getLokasiGambar());
                            File newDir = new File(direktoriGambar);
                            System.out.println(currentDir);
                            System.out.println(newDir);
                            System.out.println("masuk try untuk mindah gambar");

                            inStream = new FileInputStream(currentDir);
                            outStream = new FileOutputStream(newDir);

                            byte[] buffer = new byte[1024];

                            int length;
                            //copy the file content in bytes
                            while ((length = inStream.read(buffer)) > 0){

                                outStream.write(buffer, 0, length);

                            }

                            inStream.close();
                            outStream.close();

                            System.out.println("File is copied successful!");

                            JOptionPane.showMessageDialog(null, "Berhasil mengupdate smartphone!", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                            new EditDelete().setVisible(true);
                            this.dispose();
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(null, "Gagal mengupdate smartphone!", "Update Fail", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Gagal mengupdate smartphone!", "Update Fail", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pastikan semua field terisi!", "Gagal Update", JOptionPane.ERROR_MESSAGE); 
        }
    }
    public void editBtnActionPerformed(ActionEvent event){
        System.out.println("masuk edit yg ga kepake");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * */
    @SuppressWarnings("checked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jHome = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jCreate = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jEditDelete = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jCommentsRatings = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLogout = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_judul = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        id_network = new javax.swing.JTextField();
        id_announce = new javax.swing.JTextField();
        id_status = new javax.swing.JTextField();
        id_dimension = new javax.swing.JTextField();
        id_weight = new javax.swing.JTextField();
        id_sim = new javax.swing.JTextField();
        id_type = new javax.swing.JTextField();
        id_size = new javax.swing.JTextField();
        id_res = new javax.swing.JTextField();
        id_os = new javax.swing.JTextField();
        id_chip = new javax.swing.JTextField();
        id_cpu = new javax.swing.JTextField();
        id_gpu = new javax.swing.JTextField();
        id_card = new javax.swing.JTextField();
        id_internal = new javax.swing.JTextField();
        id_camPri = new javax.swing.JTextField();
        id_camSec = new javax.swing.JTextField();
        id_harga = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();
        id_model = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        id_version = new javax.swing.JTextField();
        imgLabel = new javax.swing.JLabel();
        id_md = new javax.swing.JLabel();
        id_merek = new javax.swing.JTextField();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jPanel3.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Pull_Down_48px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jHome.setBackground(new java.awt.Color(0, 102, 204));
        jHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jHomeMouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Home_48px.png"))); // NOI18N

        javax.swing.GroupLayout jHomeLayout = new javax.swing.GroupLayout(jHome);
        jHome.setLayout(jHomeLayout);
        jHomeLayout.setHorizontalGroup(
            jHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHomeLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jHomeLayout.setVerticalGroup(
            jHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jHomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6))
        );

        jCreate.setBackground(new java.awt.Color(0, 102, 204));
        jCreate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCreateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCreateMouseExited(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Create_48px.png"))); // NOI18N

        javax.swing.GroupLayout jCreateLayout = new javax.swing.GroupLayout(jCreate);
        jCreate.setLayout(jCreateLayout);
        jCreateLayout.setHorizontalGroup(
            jCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCreateLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jCreateLayout.setVerticalGroup(
            jCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCreateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        jEditDelete.setBackground(new java.awt.Color(0, 102, 204));
        jEditDelete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jEditDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEditDeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jEditDeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jEditDeleteMouseExited(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Edit_48px.png"))); // NOI18N

        javax.swing.GroupLayout jEditDeleteLayout = new javax.swing.GroupLayout(jEditDelete);
        jEditDelete.setLayout(jEditDeleteLayout);
        jEditDeleteLayout.setHorizontalGroup(
            jEditDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEditDeleteLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jEditDeleteLayout.setVerticalGroup(
            jEditDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEditDeleteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        jCommentsRatings.setBackground(new java.awt.Color(0, 102, 204));
        jCommentsRatings.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCommentsRatings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCommentsRatingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCommentsRatingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCommentsRatingsMouseExited(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Chat_48px.png"))); // NOI18N

        javax.swing.GroupLayout jCommentsRatingsLayout = new javax.swing.GroupLayout(jCommentsRatings);
        jCommentsRatings.setLayout(jCommentsRatingsLayout);
        jCommentsRatingsLayout.setHorizontalGroup(
            jCommentsRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCommentsRatingsLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jCommentsRatingsLayout.setVerticalGroup(
            jCommentsRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCommentsRatingsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        jLogout.setBackground(new java.awt.Color(0, 102, 204));
        jLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLogoutMouseExited(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon-menu-kiri/icons8_Logout_Rounded_Up_48px.png"))); // NOI18N

        javax.swing.GroupLayout jLogoutLayout = new javax.swing.GroupLayout(jLogout);
        jLogout.setLayout(jLogoutLayout);
        jLogoutLayout.setHorizontalGroup(
            jLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLogoutLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jLogoutLayout.setVerticalGroup(
            jLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLogoutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEditDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCommentsRatings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jEditDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jCommentsRatings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Edit");

        id_judul.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id_judul.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id_judul.setToolTipText("");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Primary");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NETWORK");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Dimensions");

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Internal");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("IMAGE");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Status");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("LAUNCH");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CAMERA");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("SIM");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Announced");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BODY");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DISPLAY");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Weight");

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Resolution");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("MEMORY");

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("OS");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Type");

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Card Slot");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("PRICE");

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Chipset");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Technology");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Size");

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("GPU");

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Secondary");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("PLATFORM");

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("CPU");

        jButton3.setText("Browse");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        editBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        id_model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_modelActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("MODEL");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("VERSION");

        id_version.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_versionActionPerformed(evt);
            }
        });

        imgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLabel.setText("VIEW IMAGE");

        id_md.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        id_md.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id_md.setText("MEREK");

        id_merek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_merekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34)
                                .addComponent(jLabel36)
                                .addComponent(jLabel37)
                                .addComponent(jLabel38)
                                .addComponent(jLabel39)
                                .addComponent(jLabel40)
                                .addComponent(jLabel41)))
                        .addComponent(jLabel17)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel9)
                                .addComponent(jLabel32)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(4, 4, 4)))
                                .addComponent(jLabel58))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jButton3))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(id_os, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel28)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(id_type, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel25)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                                    .addComponent(id_dimension, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel26)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(id_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel27)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(id_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel29)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(id_size, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel30)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(id_res, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel23)
                                                        .addComponent(jLabel24))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(id_announce, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                                        .addComponent(id_status, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                                        .addComponent(id_network, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                                        .addComponent(id_version, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                                        .addComponent(id_merek, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                    .addComponent(jLabel14)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(id_model, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(id_chip, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_cpu, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_gpu, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_card, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_internal, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_camPri, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_camSec, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(id_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(editBtn))
                                        .addComponent(jLabel22)))))
                        .addComponent(id_md))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346)))
                .addContainerGap(357, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(id_model, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_md)
                    .addComponent(id_merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(id_version, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel22)
                    .addComponent(id_network, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel23)
                    .addComponent(id_announce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel25)
                            .addComponent(id_dimension, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(id_weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(id_sim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel28)
                            .addComponent(id_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(id_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(id_res, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(id_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel34)
                    .addComponent(id_os, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(id_chip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(id_cpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(id_gpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(id_card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(id_internal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel40)
                    .addComponent(id_camPri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(id_camSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(id_harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(editBtn)
                .addContainerGap(517, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_judul, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(id_judul, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseEntered
        setColor(jHome);
    }//GEN-LAST:event_jHomeMouseEntered

    private void jHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseExited
        resetColor(jHome);
    }//GEN-LAST:event_jHomeMouseExited

    private void jCreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCreateMouseEntered
        setColor(jCreate);
    }//GEN-LAST:event_jCreateMouseEntered

    private void jCreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCreateMouseExited
        resetColor(jCreate);
    }//GEN-LAST:event_jCreateMouseExited

    private void jEditDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditDeleteMouseEntered
        setColor(jEditDelete);
    }//GEN-LAST:event_jEditDeleteMouseEntered

    private void jEditDeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditDeleteMouseExited
        resetColor(jEditDelete);
    }//GEN-LAST:event_jEditDeleteMouseExited

    private void jCommentsRatingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCommentsRatingsMouseEntered
        setColor(jCommentsRatings);
    }//GEN-LAST:event_jCommentsRatingsMouseEntered

    private void jCommentsRatingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCommentsRatingsMouseExited
        resetColor(jCommentsRatings);
    }//GEN-LAST:event_jCommentsRatingsMouseExited

    private void jLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMouseEntered
        setColor(jLogout);
    }//GEN-LAST:event_jLogoutMouseEntered

    private void jLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMouseExited
        resetColor(jLogout);
    }//GEN-LAST:event_jLogoutMouseExited

    private void jHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseClicked
        Home show = new Home();
        show.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jHomeMouseClicked

    private void jCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCreateMouseClicked
        Create show = new Create();
        show.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jCreateMouseClicked

    private void jEditDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditDeleteMouseClicked
        EditDelete show;
        try {
            show = new EditDelete();
            show.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        setVisible(false);
    }//GEN-LAST:event_jEditDeleteMouseClicked

    private void jCommentsRatingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCommentsRatingsMouseClicked
        try {
            CommentsRatings show = new CommentsRatings();
            show.setVisible(true);
            setVisible(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCommentsRatingsMouseClicked

    private void jLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLogoutMouseClicked
        CheckLogin.setStatus(false);
        CheckLogin.setUsername(null);
        try {
            new UserHome().setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLogoutMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        String temp, ext;
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            temp = fc.getSelectedFile().getAbsolutePath();
            ext = temp.substring(temp.lastIndexOf(".")+1, temp.length());
            if(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("JPG") || ext.equals("JPEG")){
                this.setLokasiGambar(temp);
                this.ext = ext;
            } else {
                JOptionPane.showMessageDialog(jPanel2, "Gambar harus berjenis JPEG/JPG");
            }
        }
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(this.getLokasiGambar()));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Image newImg = img.getScaledInstance(imgLabel.getWidth(),imgLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(newImg);
        imgLabel.setIcon(imgIcon);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void id_modelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_modelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_modelActionPerformed

    private void id_versionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_versionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_versionActionPerformed

    private void id_merekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_merekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_merekActionPerformed
    //untuk bayangan kusor
    public void setColor(JPanel panel){
        panel.setBackground(new java.awt.Color(204,204,204));
    }
    
    public void resetColor(JPanel panel){
        panel.setBackground(new java.awt.Color(0,102,204));
    }
    
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
            java.util.logging.Logger.getLogger(ViewEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField id_announce;
    private javax.swing.JTextField id_camPri;
    private javax.swing.JTextField id_camSec;
    private javax.swing.JTextField id_card;
    private javax.swing.JTextField id_chip;
    private javax.swing.JTextField id_cpu;
    private javax.swing.JTextField id_dimension;
    private javax.swing.JTextField id_gpu;
    private javax.swing.JTextField id_harga;
    private javax.swing.JTextField id_internal;
    private javax.swing.JTextField id_judul;
    private javax.swing.JLabel id_md;
    private javax.swing.JTextField id_merek;
    private javax.swing.JTextField id_model;
    private javax.swing.JTextField id_network;
    private javax.swing.JTextField id_os;
    private javax.swing.JTextField id_res;
    private javax.swing.JTextField id_sim;
    private javax.swing.JTextField id_size;
    private javax.swing.JTextField id_status;
    private javax.swing.JTextField id_type;
    private javax.swing.JTextField id_version;
    private javax.swing.JTextField id_weight;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jCommentsRatings;
    private javax.swing.JPanel jCreate;
    private javax.swing.JPanel jEditDelete;
    private javax.swing.JPanel jHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLogout;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
