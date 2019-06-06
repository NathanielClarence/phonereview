/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;
import Entity.Smartphones;
import admin.Home;
import admin.Login;
import database.Database;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import user.UserHome;
import user.ViewSearch;
/**
 *
 * @author kevingiovanni
 */
public class ViewSearch extends javax.swing.JFrame {
    private int mainCamera;
    private int secondaryCamera;
    private int ram;
    private int rom;
    private String keyword = "";
    ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
    /**
     * Creates new form userHome
     */
    public ViewSearch() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setVisible(true);
    }

    /**
     * @return the mainCamera
     */
    public int getMainCamera() {
        return mainCamera;
    }

    /**
     * @param mainCamera the mainCamera to set
     */
    public void setMainCamera(int mainCamera) {
        this.mainCamera = mainCamera;
    }

    /**
     * @return the secondaryCamera
     */
    public int getSecondaryCamera() {
        return secondaryCamera;
    }

    /**
     * @param secondaryCamera the secondaryCamera to set
     */
    public void setSecondaryCamera(int secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    /**
     * @return the ram
     */
    public int getRam() {
        return ram;
    }

    /**
     * @param ram the ram to set
     */
    public void setRam(int ram) {
        this.ram = ram;
    }

    /**
     * @return the rom
     */
    public int getRom() {
        return rom;
    }

    /**
     * @param rom the rom to set
     */
    public void setRom(int rom) {
        this.rom = rom;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public void showSearch(String flag) throws ClassNotFoundException{
        showSearch("sort",flag);
    }
    
    public void showSearch(String keyword, String indikator) throws ClassNotFoundException{
        Map<String, JButton> btnPost = new HashMap<String, JButton>();
        jPanel3.setLayout(new GridLayout(0,4));
        Database db = new Database();
        if(keyword=="sort"){
            System.out.println(this.getKeyword());
            listSmartphones = db.sortPrice(this.getKeyword(), indikator);
            System.out.println("masuk");
            System.out.println(listSmartphones.size());
            jComboBox1.setSelectedItem(indikator);
        }else{
            this.setKeyword(keyword);
            jComboBox3.setSelectedItem(indikator);
            listSmartphones = db.search(keyword,indikator);
        }
        for (int i = 0; i < listSmartphones.size(); i++) {
            String nama_smartphone = listSmartphones.get(i).getNama();
            String gambar = "src/img-smartphone/" + listSmartphones.get(i).getGambar();
            String kode_smartphone = listSmartphones.get(i).getKode();
            System.out.println(i+1 + ". " + nama_smartphone);
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(gambar));
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Image newImg = img.getScaledInstance(145,210, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);
                
            btnPost.put(kode_smartphone, new JButton(nama_smartphone,imgIcon){
                {
                    setSize(145,210);
                    setMaximumSize(getSize());
                    setMinimumSize(getSize());
                }
            });
	}
        jPanel3.removeAll();
        jPanel3.revalidate();
        jPanel3.repaint();
        for(String key: btnPost.keySet()){
            System.out.println(btnPost.get(key));
            btnPost.get(key).setVerticalTextPosition(SwingConstants.BOTTOM);
            btnPost.get(key).setHorizontalTextPosition(SwingConstants.CENTER);
            jPanel3.add(btnPost.get(key));
            System.out.println(btnPost.get(key));
            btnPost.get(key).addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent event){
                    try {
                        btnPostActionPerformed(event,key);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        
        jTextField1.setText(this.getKeyword());
    }
    
    public void btnPostActionPerformed(java.awt.event.ActionEvent event, String kode_smartphone) throws ClassNotFoundException, SQLException{
        new Phone().showDetail(kode_smartphone);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jHome = new javax.swing.JPanel();
        jlabelHome = new javax.swing.JLabel();
        jPopuler = new javax.swing.JPanel();
        jLabelPopuler = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cb_ram = new javax.swing.JComboBox<>();
        cb_rom = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jCheckBox1.setText("jCheckBox1");

        jCheckBox2.setText("jCheckBox2");

        jCheckBox3.setText("jCheckBox3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(930, 700));

        jButton1.setText("LOGIN ADMIN");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Search");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jHome.setBackground(new java.awt.Color(0, 102, 204));
        jHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jlabelHome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jlabelHome.setText("Home");

        javax.swing.GroupLayout jHomeLayout = new javax.swing.GroupLayout(jHome);
        jHome.setLayout(jHomeLayout);
        jHomeLayout.setHorizontalGroup(
            jHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelHome)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jHomeLayout.setVerticalGroup(
            jHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jHomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlabelHome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPopuler.setBackground(new java.awt.Color(0, 102, 204));
        jPopuler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPopuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPopulerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPopulerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPopulerMouseExited(evt);
            }
        });

        jLabelPopuler.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPopuler.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPopuler.setText("Popular");

        javax.swing.GroupLayout jPopulerLayout = new javax.swing.GroupLayout(jPopuler);
        jPopuler.setLayout(jPopulerLayout);
        jPopulerLayout.setHorizontalGroup(
            jPopulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPopulerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelPopuler)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPopulerLayout.setVerticalGroup(
            jPopulerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPopulerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelPopuler, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nama", "Merek", "Tahun" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPopuler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPopuler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nubz-logo.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(907, 923));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1127, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Selamat Datang di Nubz BRO & SIS");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Main Camera:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ROM:");

        cb_ram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih", "< 12MP", "12-16MP", "> 16MP" }));

        cb_rom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih", "16GB", "32GB", "64GB", "> 64GB" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Price:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih", "Terendah", "Tertinggi" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pilih", "Tertinggi", "Terendah" }));
        jComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText("Rating:");

        jButton3.setText("Filter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(137, 137, 137)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_rom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jButton1))
                        .addGap(32, 32, 32)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(cb_ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_rom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        //home h = new home();
        //h.setVisible(true);
        Login L = new Login();
        L.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseClicked
        UserHome show = null;
        try {
            show = new UserHome();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        show.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jHomeMouseClicked

    private void jPopulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopulerMouseClicked
        Popular show = null;
        try {
            show = new Popular();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        show.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jPopulerMouseClicked

    private void jHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseEntered
        setColor(jHome);
    }//GEN-LAST:event_jHomeMouseEntered

    private void jHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHomeMouseExited
        resetColor(jHome);
    }//GEN-LAST:event_jHomeMouseExited

    private void jPopulerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopulerMouseEntered
        setColor(jPopuler);
    }//GEN-LAST:event_jPopulerMouseEntered

    private void jPopulerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopulerMouseExited
        resetColor(jPopuler);
    }//GEN-LAST:event_jPopulerMouseExited

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        jComboBox2.setSelectedIndex(0);
        jPanel3.removeAll();
        String sort = "";
        if(jComboBox1.getSelectedItem().toString() == "Tertinggi"){
            //sort = "desc";
            Collections.sort(listSmartphones, Smartphones.SortPriceDesc);
        }else{
            Collections.sort(listSmartphones, Smartphones.SortPriceAsc);
        }
        Map<String, JButton> button = new HashMap<String, JButton>();
        jPanel3.setLayout(new GridLayout(0,4));
        for (int i = 0; i < listSmartphones.size(); i++) {
            String nama_smartphone = listSmartphones.get(i).getNama();
            String gambar = "src/img-smartphone/" + listSmartphones.get(i).getGambar();
            String kode_smartphone = listSmartphones.get(i).getKode();
            System.out.println(i+1 + ". " + nama_smartphone);
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(gambar));
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Image newImg = img.getScaledInstance(145,210, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);
                
            button.put(kode_smartphone, new JButton(nama_smartphone,imgIcon){
                {
                    setSize(145,210);
                    setMaximumSize(getSize());
                    setMinimumSize(getSize());
                }
            });
            button.get(kode_smartphone).setVerticalTextPosition(SwingConstants.BOTTOM);
            button.get(kode_smartphone).setHorizontalTextPosition(SwingConstants.CENTER);
            System.out.println(kode_smartphone);
            jPanel3.add(button.get(kode_smartphone));
            button.get(kode_smartphone).addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent event){
                    try {
                        btnPostActionPerformed(event,kode_smartphone);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
	}
        jPanel3.revalidate();
        jPanel3.repaint();
        jTextField1.setText(this.getKeyword());
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        cb_ram.setSelectedIndex(0);
        cb_rom.setSelectedIndex(0);
        this.repaint();
        String key = jTextField1.getText();
        try {
            this.showSearch(key, jComboBox3.getSelectedItem().toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox2PropertyChange
        // TODO add your handling code here:
        jComboBox1.setSelectedIndex(0);
        jPanel3.removeAll();
        if(jComboBox2.getSelectedItem().toString() == "Tertinggi"){
            System.out.println("masuk");
            Collections.sort(this.listSmartphones, Smartphones.SortRatingDesc);
        }else{
            Collections.sort(this.listSmartphones, Smartphones.SortRatingAsc);
        }
        Map<String, JButton> button = new HashMap<String, JButton>();
        jPanel3.setLayout(new GridLayout(0,4));
        for (int i = this.listSmartphones.size()-1; i > -1; i--) {
            String nama_smartphone = this.listSmartphones.get(i).getNama();
            String gambar = "src/img-smartphone/" + this.listSmartphones.get(i).getGambar();
            String kode_smartphone = this.listSmartphones.get(i).getKode();
            System.out.println(i+1 + ". " + nama_smartphone);
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(gambar));
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Image newImg = img.getScaledInstance(145,210, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);
            System.out.println(kode_smartphone);
            button.put(kode_smartphone, new JButton(nama_smartphone,imgIcon){
                {
                    setSize(145,210);
                    setMaximumSize(getSize());
                    setMinimumSize(getSize());
                }
            });
            button.get(kode_smartphone).setVerticalTextPosition(SwingConstants.BOTTOM);
            button.get(kode_smartphone).setHorizontalTextPosition(SwingConstants.CENTER);
            jPanel3.add(button.get(kode_smartphone));
            button.get(kode_smartphone).addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent event){
                    try {
                        btnPostActionPerformed(event,kode_smartphone);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
	}
        jPanel3.revalidate();
        jPanel3.repaint();
        jTextField1.setText(this.getKeyword());
    }//GEN-LAST:event_jComboBox2PropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String rom = cb_rom.getSelectedItem().toString();
        String mainCam = cb_ram.getSelectedItem().toString();
        ArrayList<Smartphones> list = new ArrayList<Smartphones>();
        list = this.listSmartphones;
        String romElse = "no";
        if(mainCam == "pilih"){
            mainCam = "";
        }
        if(rom == "pilih"){
            rom = "";
            romElse = "exp";
        }else if(rom == "16GB"){
            rom = "16";
        }else if(rom == "32GB"){
            rom = "32";
        }else if(rom == "64GB"){
            rom = "64";
        }else{
            romElse = "yes";
        }
        System.out.println(rom);
        System.out.println(mainCam);
        System.out.println(romElse);
        jPanel3.removeAll();
        Map<String, JButton> button = new HashMap<String, JButton>();
        jPanel3.setLayout(new GridLayout(0,4));
        for (int i = 0; i < list.size(); i++) {
            String nama_smartphone = list.get(i).getNama();
            String gambar = "src/img-smartphone/" + list.get(i).getGambar();
            String kode_smartphone = list.get(i).getKode();
            BufferedImage img = null;
            try{
                img = ImageIO.read(new File(gambar));
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            Image newImg = img.getScaledInstance(145,210, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(newImg);
                
            button.put(kode_smartphone, new JButton(nama_smartphone,imgIcon){
                {
                    setSize(145,210);
                    setMaximumSize(getSize());
                    setMinimumSize(getSize());
                }
            });
            button.get(kode_smartphone).setVerticalTextPosition(SwingConstants.BOTTOM);
            button.get(kode_smartphone).setHorizontalTextPosition(SwingConstants.CENTER);
            if(mainCam == "" && rom == ""){
                jPanel3.add(button.get(kode_smartphone));
            }else if(romElse == "yes"){
               if(mainCam == ""){
                   if(list.get(i).getRom() > 64){
                       jPanel3.add(button.get(kode_smartphone));
                   }
               }else if(mainCam == "< 12MP"){
                   if(list.get(i).getRom() > 64 && list.get(i).getKamera_belakang() < 2){
                       jPanel3.add(button.get(kode_smartphone));
                   }
               }else if(mainCam == "12-16MP"){
                   if(list.get(i).getRom() > 64 && list.get(i).getKamera_belakang() <= 4 && list.get(i).getKamera_belakang() >= 2){
                       jPanel3.add(button.get(kode_smartphone));
                   }
               }else if(mainCam == "> 16MP"){
                   if(list.get(i).getRom() > 64 && list.get(i).getKamera_belakang() > 4){
                       jPanel3.add(button.get(kode_smartphone));
                   }
               }
               
            }else if(romElse == "no"){
                if(mainCam == ""){
                   if(list.get(i).getRom() == Integer.parseInt(rom)){
                       jPanel3.add(button.get(kode_smartphone));
                   }
                }else if(mainCam == "< 12MP"){
                    if(list.get(i).getRom() == Integer.parseInt(rom) && list.get(i).getKamera_belakang() < 12){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }else if(mainCam == "12-16MP"){
                    if(list.get(i).getRom() == Integer.parseInt(rom) && list.get(i).getKamera_belakang() <= 16 && list.get(i).getKamera_belakang() >= 12){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }else if(mainCam == "> 16MP"){
                    if(list.get(i).getRom() == Integer.parseInt(rom) && list.get(i).getKamera_belakang() > 16){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }
            }else if(rom == ""){
                if(mainCam == ""){
                   jPanel3.add(button.get(kode_smartphone));
                }else if(mainCam == "< 12MP"){
                    if(list.get(i).getKamera_belakang() < 12){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }else if(mainCam == "12-16MP"){
                    System.out.println("masuk 12-16");
                    if(list.get(i).getKamera_belakang() <= 16 && list.get(i).getKamera_belakang() >= 12){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }else if(mainCam == "> 16MP"){
                    if(list.get(i).getKamera_belakang() > 16){
                       jPanel3.add(button.get(kode_smartphone));
                    }
                }
            }
            button.get(kode_smartphone).addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent event){
                    try {
                        btnPostActionPerformed(event,kode_smartphone);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
	}
        jPanel3.revalidate();
        jPanel3.repaint();
        jTextField1.setText(this.getKeyword());
        
        // showSorting(this.getKeyword(),rating, harga, rom, ram, mainCam, secCam)
    }//GEN-LAST:event_jButton3ActionPerformed

    public void setColor(JPanel panel){
        panel.setBackground(new java.awt.Color(204,204,204));
    }
    
    public void resetColor(JPanel panel){
        panel.setBackground(new java.awt.Color(0,102,204));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
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
            java.util.logging.Logger.getLogger(ViewSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ViewSearch().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ViewSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_ram;
    private javax.swing.JComboBox<String> cb_rom;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JPanel jHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelPopuler;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPopuler;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jlabelHome;
    // End of variables declaration//GEN-END:variables
}
