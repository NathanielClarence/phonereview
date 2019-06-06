/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Entity.Comments;
import Entity.Smartphones;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kevingiovanni
 */
public class Database {
    private Connection connect() throws ClassNotFoundException {
        // SQLite connection string
        Connection conn = null;
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/database/nubz.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public boolean checkData(){
        
        return false;
    }
    
    public boolean insertSmartphone(String nama, String gambar, String kode, String cpu, String gpu, int kamera_depan, int kamera_belakang, int rom,
    String status, String displaySize, String displayType, String displayResolution, String network, String os, String chipset, String memoryCard,
    String launchDate, String dimension, String weight, String sim, int harga, String merek, int version, String model) throws ClassNotFoundException {
        String sql = "insert into smartphones(kode_smartphone,nama_smartphone,merek,cpu,gpu,rom,kamera_depan,kamera_belakang,version,model,gambar,launchDate,network,status,displayType,displaySize,displayResolution,os,chipset,memoryCard,harga,dimension,weight,sim) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(gambar);
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setString(3, merek);
            pstmt.setString(4, cpu);
            pstmt.setString(5, gpu);
            pstmt.setInt(6, rom);
            pstmt.setInt(7, kamera_depan);
            pstmt.setInt(8, kamera_belakang);
            pstmt.setInt(9, version);
            pstmt.setString(10, model);
            pstmt.setString(11, gambar);
            pstmt.setString(12, launchDate);
            pstmt.setString(13, network);
            pstmt.setString(14, status);
            pstmt.setString(15, displayType);
            pstmt.setString(16, displaySize);
            pstmt.setString(17, displayResolution);
            pstmt.setString(18, os);
            pstmt.setString(19, chipset);
            pstmt.setString(20, memoryCard);
            pstmt.setInt(21, harga);
            pstmt.setString(22, dimension);
            pstmt.setString(23, weight);
            pstmt.setString(24, sim);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid!", "ERROR", JOptionPane.ERROR_MESSAGE); 
            return false;
        }
        
        String sql_post = "insert into posts(kode_smartphone) values(?)";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql_post)) {
            pstmt.setString(1, kode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean deletePost(String kode_smartphone) throws ClassNotFoundException {
        String sql_post = "delete from posts where kode_smartphone=?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql_post)) {
            pstmt.setString(1, kode_smartphone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        String sql = "delete from smartphones where kode_smartphone=?";
 
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kode_smartphone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean checkAdmin(String username, String password) throws ClassNotFoundException{
        String sql = "select * from admin where username=? and password=? limit 1";
 
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            //
            ResultSet rs  = pstmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 1){
                System.out.println("password benar");
                return true;
            } 
            else{
                System.out.println("password salah");
                return false;
            }
                
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Smartphones> fetchAll() throws ClassNotFoundException{
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        String sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone order by p.waktu desc";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listSmartphones.add(new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), result.getString("status"), result.getString("displaySize"), result.getString("displayType"), result.getString("displayResolution"), result.getString("network"), result.getString("os"), result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listSmartphones;
    }
    
    public ArrayList<Smartphones> search(String keyword, String indikator) throws ClassNotFoundException{
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        String sql = "";
        if(indikator == "Nama"){
            sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating  from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and lower(s.nama_smartphone) like ? order by p.waktu desc";
        }else if(indikator == "Merek"){
            sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating  from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and lower(s.merek) like ? order by p.waktu desc";
        }else if(indikator == "Tahun"){
            sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating  from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and lower(s.launchDate) like ? order by p.waktu desc";
        }
        
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listSmartphones.add(new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), result.getString("status"), result.getString("displaySize"), result.getString("displayType"), result.getString("displayResolution"), result.getString("network"), result.getString("os"), result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listSmartphones;
    }
    
    public ArrayList<Smartphones> fetchHistory(String model, String kodeSmp) throws ClassNotFoundException{
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        String sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating"
                + " from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and s.model = ? and s.kode_smartphone != ? order by s.version desc";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, model);
            pstmt.setString(2, kodeSmp);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listSmartphones.add(new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), result.getString("status"), result.getString("displaySize"), result.getString("displayType"), result.getString("displayResolution"), result.getString("network"), result.getString("os"), result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listSmartphones;
    }
    
    public ArrayList<Smartphones> sortPrice(String keyword, String flag) throws ClassNotFoundException{
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        String sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating  from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and lower(s.nama_smartphone) like ? order by  s.harga " + flag;
        System.out.println(sql);
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listSmartphones.add(new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), result.getString("status"), result.getString("displaySize"), result.getString("displayType"), result.getString("displayResolution"), result.getString("network"), result.getString("os"), result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listSmartphones;
    }
    
    public Smartphones fetchPost(String kode_smartphone) throws ClassNotFoundException{
        Smartphones smartphone = null;
        String sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and s.kode_smartphone=? order by p.waktu desc";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, kode_smartphone);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
//                String nama, String gambar, String kode, String cpu, String gpu, int kamera_depan, int kamera_belakang, int rom,
//                String status, String displaySize, String displayType, String displayResolution, String network, String os, String chipset, int memoryCard,
//                String launchDate, String dimension, String weight, String sim, int harga
                smartphone = new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), 
                        result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), 
                        result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), 
                        result.getString("status"), result.getString("displaySize"), result.getString("displayType"), 
                        result.getString("displayResolution"), result.getString("network"), result.getString("os"), 
                        result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), 
                        result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), 
                        result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return smartphone;
    }
    
    public ArrayList<Smartphones> getPopularSmartphones() throws ClassNotFoundException{
        ArrayList<Smartphones> listSmartphones = new ArrayList<Smartphones>();
        String sql = "select p.id_post, s.nama_smartphone, s.gambar, s.kode_smartphone, s.cpu, s.gpu, s.kamera_depan, s.kamera_belakang, s.rom, s.status, s.displayType, s.displaySize, s.displayResolution, s.network, s.os, s.chipset, s.launchDate, s.dimension, s.weight, s.sim, s.harga, s.merek, s.version, s.model, s.memoryCard, s.rating from posts p, smartphones s where p.kode_smartphone=s.kode_smartphone and s.rating>0 order by s.rating desc, p.waktu desc";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listSmartphones.add(new Smartphones(result.getInt("id_post"), result.getString("nama_smartphone"), result.getString("gambar"), result.getString("kode_smartphone"), result.getString("cpu"), result.getString("gpu"), result.getInt("kamera_depan"), result.getInt("kamera_belakang"), result.getInt("rom"), result.getString("status"), result.getString("displaySize"), result.getString("displayType"), result.getString("displayResolution"), result.getString("network"), result.getString("os"), result.getString("chipset"), result.getString("memoryCard"), result.getString("launchDate"), result.getString("dimension"), result.getString("weight"), result.getString("sim"), result.getInt("harga"), result.getString("merek"), result.getInt("version"), result.getString("model"), result.getFloat("rating")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listSmartphones;
    }
    
    public boolean updateSmartphone(String nama, String gambar, String kode, String cpu, String gpu, int kamera_depan, int kamera_belakang, int rom,
    String status, String displaySize, String displayType, String displayResolution, String network, String os, String chipset, String memoryCard,
    String launchDate, String dimension, String weight, String sim, int harga, String merek, int version, String model) throws ClassNotFoundException{
        String sql = "update smartphones set nama_smartphone=?  ,"
                + "merek=?,"
                + "cpu=?,"
                + "gpu=?,"
                + "rom=?,"
                + "kamera_depan=?,"
                + "kamera_belakang=?,"
                + "version=?,"
                + "model=?,"
                + "gambar=?,"
                + "launchDate=?,"
                + "network=?,"
                + "status=?,"
                + "displayType=?,"
                + "displaySize=?,"
                + "displayResolution=?,"
                + "os=?,"
                + "chipset=?,"
                + "memoryCard=?,"
                + "harga=?,"
                + "dimension=?,"
                + "weight=?,"
                + "sim=? "
                + "where kode_smartphone=?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, nama);
            pstmt.setString(2, merek);
            pstmt.setString(3, cpu);
            pstmt.setString(4, gpu);
            pstmt.setInt(5, rom);
            pstmt.setInt(6, kamera_depan);
            pstmt.setInt(7, kamera_belakang);
            pstmt.setInt(8, version);
            pstmt.setString(9, model);
            pstmt.setString(10, gambar);
            pstmt.setString(11, launchDate);
            pstmt.setString(12, network);
            pstmt.setString(13, status);
            pstmt.setString(14, displayType);
            pstmt.setString(15, displaySize);
            pstmt.setString(16, displayResolution);
            pstmt.setString(17, os);
            pstmt.setString(18, chipset);
            pstmt.setString(19, memoryCard);
            pstmt.setInt(20, harga);
            pstmt.setString(21, dimension);
            pstmt.setString(22, weight);
            pstmt.setString(23, sim);
            pstmt.setString(24, kode);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Input itdak valid!", "ERROR", JOptionPane.ERROR_MESSAGE); 
            return false;
        }
        return true;
    }
    
    
    
    public boolean updateRating(String kode) throws ClassNotFoundException {
        String sql = "update smartphones set rating=(select round(avg(rating),1) from ratings where kode_smartphone=?) where kode_smartphone=?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kode);
            pstmt.setString(2, kode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean insertRating(int rating, String kode) throws ClassNotFoundException {
        String sql = "insert into ratings(rating,kode_smartphone) values(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rating);
            pstmt.setString(2, kode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean insertComment(String kode, String review) throws ClassNotFoundException{
        String sql = "insert into comments(comment,kode_smartphone,approved) values(?,?,?)";
        int approved = 0;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, review);
            pstmt.setString(2, kode);
            pstmt.setInt(3, approved);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public ArrayList<Comments> getAllCR(String kode) throws ClassNotFoundException {
        ArrayList<Comments> listCR = new ArrayList<Comments>();
        String sql = "select c.comment, c.kode_smartphone, c.id_comment, c.approved, s.nama_smartphone from comments c, smartphones s  where c.kode_smartphone=s.kode_smartphone and c.kode_smartphone=? and c.approved=1 order by c.waktu";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1,kode);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listCR.add(new Comments(result.getString("comment"),result.getString("kode_smartphone"), result.getInt("id_comment"), result.getString("nama_smartphone"), result.getInt("approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listCR;
    }
    
    public ArrayList<Comments> getComment() throws ClassNotFoundException {
        ArrayList<Comments> listCR = new ArrayList<Comments>();
        String sql = "select c.comment, c.kode_smartphone, c.id_comment, c.approved, s.nama_smartphone from comments c, smartphones s  where c.kode_smartphone=s.kode_smartphone and c.approved=0 order by c.waktu";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listCR.add(new Comments(result.getString("comment"),result.getString("kode_smartphone"), result.getInt("id_comment"), result.getString("nama_smartphone"), result.getInt("approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listCR;
    }
    
    public ArrayList<Comments> getAllComment() throws ClassNotFoundException {
        ArrayList<Comments> listCR = new ArrayList<Comments>();
        String sql = "select c.comment, c.kode_smartphone, c.id_comment, c.approved, s.nama_smartphone from comments c, smartphones s  where c.kode_smartphone=s.kode_smartphone order by c.waktu";
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                listCR.add(new Comments(result.getString("comment"),result.getString("kode_smartphone"), result.getInt("id_comment"), result.getString("nama_smartphone"), result.getInt("approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listCR;
    }
    
    public boolean deleteComment(int id_comment) throws ClassNotFoundException{
        String sql = "delete from comments where id_comment=?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean approveComment(int id_comment) throws ClassNotFoundException{
        String sql = "update comments set approved=1 where id_comment=?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public int getIdPost(String kode) throws ClassNotFoundException{
        String sql = "select id_post from posts where kode_smartphone=? limit 1";
        int id_post = 0;
        try (Connection conn = this.connect();
            PreparedStatement pstmt  = conn.prepareStatement(sql)){
            pstmt.setString(1, kode);
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                id_post = result.getInt("id_post");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return id_post;
    }
    
    public Database(){
    }
}
