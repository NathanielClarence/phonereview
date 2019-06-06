package user;

//package admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Smartphones;
import admin.Home;
import admin.EditDelete;
import admin.CommentsRatings;
import admin.CheckLogin;
import admin.ViewEdit;
import database.Database;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author Clarence
 */
public class Phone extends javax.swing.JFrame {
    private String kodeSmartphone;
    private String lokasiGambar;
    private Smartphones smartphone;
    /**
     * Creates new form Phone
     */
    public Phone(){
        initComponents();
        
        if(CheckLogin.getStatus() == false){
            deleteBtn.setVisible(false);
            editBtn.setVisible(false);
        }else{
            deleteBtn.setVisible(true);
            editBtn.setVisible(true);
        }
    }
    
    public void showHistory() throws SQLException, ClassNotFoundException{
        //Smartphones smartphone = null;
        Database db = new Database();
        //smartphone = db.fetchPost(this.kodeSmartphone);
        
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        listSmartphones = db.fetchHistory(smartphone.getModel(),smartphone.getKode());
        Map<String, JButton> btnPost = new HashMap<String, JButton>();
        if(listSmartphones.size() == 0){
            jScrollPane1.setVisible(false);
            historyPanel.setVisible(false);
            return;
        }else{
            jScrollPane1.setVisible(true);
            historyPanel.setVisible(true);
        }
        jScrollPane1.setMaximumSize(jPanel2.getSize());
        historyPanel.setMaximumSize(jPanel2.getSize());
        historyPanel.setLayout(new GridLayout(0,listSmartphones.size()));
        
        for (int i = 0; i < listSmartphones.size(); i++) {
            String nama_smartphone = listSmartphones.get(i).getNama();
            String gambar = "src/img-smartphone/" + listSmartphones.get(i).getGambar();
            String codeSm = listSmartphones.get(i).getKode();
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(gambar));
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Image newImg = img.getScaledInstance(100,160, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);
                
            JButton btn = new JButton(nama_smartphone, imgIcon);
            btn.setSize(100,160);
            btnPost.put(codeSm, btn);
	      }
        for(String key: btnPost.keySet()){
            btnPost.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
            btnPost.get(key).setHorizontalTextPosition(SwingConstants.CENTER);
            historyPanel.add(btnPost.get(key));
            btnPost.get(key).addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent event){
                    try {
                        btnPostActionPerformed(event,key);
                    } catch (Exception ex) {
                        Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    public void btnPostActionPerformed(java.awt.event.ActionEvent event, String kode_smartphone) throws ClassNotFoundException, SQLException{
        new Phone().showDetail(kode_smartphone);
        this.dispose();
    }
    
    public void showDetail(String kode_smartphone) throws ClassNotFoundException, SQLException{
        this.setVisible(true);
        
        Database db = new Database();
        smartphone = db.fetchPost(kode_smartphone);
        
        this.kodeSmartphone = kode_smartphone;
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
        float rating = smartphone.getRating();
        
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(gambar));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        this.lokasiGambar = gambar;
        Image newImg = img.getScaledInstance(imgPreviewLabel.getWidth(),imgPreviewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(newImg);
            
        imgPreviewLabel.setIcon(imgIcon);
        namaLabel.setText(nama_smartphone);
        id_announce.setText(launchDate);
        id_camPri.setText(Integer.toString(kamera_belakang) + "MP");
        id_camSec.setText(Integer.toString(kamera_depan) + "MP");
        id_chip.setText(chipset);
        id_status.setText(status);
        id_gpu.setText(gpu);
        id_cpu.setText(cpu);
        id_internal.setText(Integer.toString(rom) + "GB");
        id_os.setText(os);
        id_size.setText(displaySize);
        id_res.setText(displayResolution);
        id_card.setText(memoryCard);
        id_sim.setText(sim);
        id_harga.setText("Rp. " + Integer.toString(harga));
        id_weight.setText(weight);
        id_dimension.setText(dimension);
        id_network.setText(network);
        id_type.setText(displayType);
        ratingLabel.setText(Float.toString(rating));
        this.showHistory();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        id_network = new javax.swing.JLabel();
        id_announce = new javax.swing.JLabel();
        id_status = new javax.swing.JLabel();
        id_dimension = new javax.swing.JLabel();
        id_weight = new javax.swing.JLabel();
        id_sim = new javax.swing.JLabel();
        id_type = new javax.swing.JLabel();
        id_size = new javax.swing.JLabel();
        id_res = new javax.swing.JLabel();
        id_os = new javax.swing.JLabel();
        id_chip = new javax.swing.JLabel();
        id_cpu = new javax.swing.JLabel();
        id_gpu = new javax.swing.JLabel();
        id_card = new javax.swing.JLabel();
        id_internal = new javax.swing.JLabel();
        id_camPri = new javax.swing.JLabel();
        id_camSec = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        id_harga = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        imgPreviewLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        namaLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        historyPanel = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setAutoscrolls(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("NETWORK");

        jLabel4.setText("LAUNCH");

        jLabel5.setText("BODY");

        jLabel6.setText("DISPLAY");

        jLabel7.setText("PLATFORM");

        jLabel15.setText("MEMORY");

        jLabel16.setText("CAMERA");

        jLabel22.setText("Technology");

        jLabel23.setText("Announced");

        jLabel24.setText("Status");

        jLabel25.setText("Dimensions");

        jLabel26.setText("Weight");

        jLabel27.setText("SIM");

        jLabel28.setText("Type");

        jLabel29.setText("Size");

        jLabel30.setText("Resolution");

        jLabel34.setText("OS");

        jLabel35.setText("Chipset");

        jLabel36.setText("CPU");

        jLabel37.setText("GPU");

        jLabel38.setText("Card Slot");

        jLabel39.setText("Internal");

        jLabel40.setText("Primary");

        jLabel41.setText("Secondary");

        id_network.setText("jLabel2");

        id_announce.setText("jLabel8");

        id_status.setText("jLabel9");

        id_dimension.setText("jLabel10");

        id_weight.setText("jLabel11");

        id_sim.setText("jLabel12");

        id_type.setText("jLabel13");

        id_size.setText("jLabel14");

        id_res.setText("jLabel17");

        id_os.setText("jLabel18");

        id_chip.setText("jLabel19");

        id_cpu.setText("jLabel21");

        id_gpu.setText("jLabel31");

        id_card.setText("jLabel32");

        id_internal.setText("jLabel33");

        id_camPri.setText("jLabel42");

        id_camSec.setText("jLabel43");

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("RATING");

        ratingLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ratingLabel.setText("4");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("of 5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(id_network))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(id_status))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(id_announce))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id_res)
                                    .addComponent(id_size)
                                    .addComponent(id_type)
                                    .addComponent(id_sim)
                                    .addComponent(id_weight)
                                    .addComponent(id_dimension)
                                    .addComponent(id_os)
                                    .addComponent(id_chip)
                                    .addComponent(id_cpu)
                                    .addComponent(id_gpu)
                                    .addComponent(id_card)
                                    .addComponent(id_internal)
                                    .addComponent(id_camPri)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel38)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(id_camSec, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ratingLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(80, 80, 80))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel22)
                            .addComponent(id_network))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel23)
                            .addComponent(id_announce))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(id_status))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel25)
                            .addComponent(id_dimension))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(id_weight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(id_sim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel28)
                            .addComponent(id_type))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(id_size))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(id_res))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel34)
                            .addComponent(id_os))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(id_chip))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(id_cpu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(id_gpu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel38)
                            .addComponent(id_card))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(id_internal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel40)
                            .addComponent(id_camPri))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(ratingLabel)
                                .addComponent(jLabel8))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel41)
                                .addComponent(id_camSec))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        id_network.getAccessibleContext().setAccessibleDescription("network lte/3g/h+");
        id_announce.getAccessibleContext().setAccessibleDescription("release date");
        id_status.getAccessibleContext().setAccessibleDescription("released/discontinued/upcoming");
        id_dimension.getAccessibleContext().setAccessibleDescription("panjang, lebar, tebal");
        id_weight.getAccessibleContext().setAccessibleDescription("berat");
        id_sim.getAccessibleContext().setAccessibleDescription("microsim/sim/nano, dual/single");
        id_type.getAccessibleContext().setAccessibleDescription("gorrilaglass dll");
        id_size.getAccessibleContext().setAccessibleDescription("ukuran layar ");
        id_res.getAccessibleContext().setAccessibleDescription("resolusi");
        id_os.getAccessibleContext().setAccessibleDescription("android/ios/symbian");
        id_chip.getAccessibleContext().setAccessibleDescription("quadcore/dual");
        id_cpu.getAccessibleContext().setAccessibleDescription("snapdragon/intel");
        id_gpu.getAccessibleContext().setAccessibleDescription("vga");
        id_card.getAccessibleContext().setAccessibleDescription("yes/no, xGB");
        id_internal.getAccessibleContext().setAccessibleDescription("32/64GB internal storage");
        id_camPri.getAccessibleContext().setAccessibleDescription("kamera belakang xMP");
        id_camSec.getAccessibleContext().setAccessibleDescription("kamera depan xMP");

        id_harga.setBackground(new java.awt.Color(255, 255, 255));
        id_harga.setForeground(new java.awt.Color(255, 0, 0));
        id_harga.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id_harga.setText("harga");
        id_harga.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 400));

        imgPreviewLabel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgPreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgPreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Make A Review");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("See Reviews");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        namaLabel.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        namaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaLabel.setText("namaLabel");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(namaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(namaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(994, 210));

        javax.swing.GroupLayout historyPanelLayout = new javax.swing.GroupLayout(historyPanel);
        historyPanel.setLayout(historyPanelLayout);
        historyPanelLayout.setHorizontalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        historyPanelLayout.setVerticalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(historyPanel);

        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(id_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // admin -> udh ada isinya
        if(CheckLogin.getStatus() == false){
            try {
                new UserHome().setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                new EditDelete().setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        Database db = new Database();
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this smartphone", "WARNING", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                if(db.deletePost(this.kodeSmartphone)){
                    File imageFile = new File(this.lokasiGambar);
                    imageFile.delete();
                    System.out.println("berhasil delete!");
                    JOptionPane.showMessageDialog(null, "Berhasil delete smartphone");
                    try {
                        new EditDelete().setVisible(true);
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.out.println("gagal delete!");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        try {
            // TODO add your handling code here:
            new ViewEdit().showDetail(this.kodeSmartphone);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Phone.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_editBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
            Database db = new Database();
            int id_post = db.getIdPost(this.kodeSmartphone);
            MakeReview newRev = new MakeReview();
            newRev.setId_post(id_post);
            newRev.setKode_smartphone(this.kodeSmartphone);
            newRev.setVisible(true);
            this.dispose();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            Database db = new Database();
            int id_post = db.getIdPost(this.kodeSmartphone);
            SeeReviews seeRev = new SeeReviews();
            seeRev.setId_post(id_post);
            seeRev.setKodeSmartphone(this.kodeSmartphone);
            seeRev.showAll(id_post);
            seeRev.setVisible(true);
            this.dispose();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Phone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Phone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Phone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Phone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Phone().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JPanel historyPanel;
    private javax.swing.JLabel id_announce;
    private javax.swing.JLabel id_camPri;
    private javax.swing.JLabel id_camSec;
    private javax.swing.JLabel id_card;
    private javax.swing.JLabel id_chip;
    private javax.swing.JLabel id_cpu;
    private javax.swing.JLabel id_dimension;
    private javax.swing.JLabel id_gpu;
    private javax.swing.JLabel id_harga;
    private javax.swing.JLabel id_internal;
    private javax.swing.JLabel id_network;
    private javax.swing.JLabel id_os;
    private javax.swing.JLabel id_res;
    private javax.swing.JLabel id_sim;
    private javax.swing.JLabel id_size;
    private javax.swing.JLabel id_status;
    private javax.swing.JLabel id_type;
    private javax.swing.JLabel id_weight;
    private javax.swing.JLabel imgPreviewLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JLabel ratingLabel;
    // End of variables declaration//GEN-END:variables
}
