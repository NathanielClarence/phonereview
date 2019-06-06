/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Comparator;

/**
 *
 * @author kevingiovanni
 */
public class Smartphones {

    private String nama;
    private String gambar;
    private String kode;
    private String cpu;
    private String gpu;
    private int kamera_depan;
    private int kamera_belakang;
    private int rom;
    private String status;
    private String displaySize;
    private String displayType;
    private String displayResolution;
    private String network;
    private String os;
    private String chipset;
    private String memoryCard;
    private String launchDate;
    private String dimension;
    private String weight;
    private String sim;
    private int harga;
    private String merek;
    private int version;
    private String model;
    private int id_post;
    private float rating;
    
    public Smartphones(int id_post, String nama, String gambar, String kode, String cpu, String gpu, int kamera_depan, int kamera_belakang, int rom,
    String status, String displaySize, String displayType, String displayResolution, String network, String os, String chipset, String memoryCard,
    String launchDate, String dimension, String weight, String sim, int harga, String merek, int version, String model, float rating){
        this.id_post = id_post;
        this.nama = nama;
        this.gambar = gambar;
        this.kode = kode;
        this.cpu = cpu;
        this.gpu = gpu;
        this.kamera_depan = kamera_depan;
        this.kamera_belakang = kamera_belakang;
        this.rom = rom;
        this.status = status;
        this.displaySize = displaySize;
        this.displayType = displayType;
        this.displayResolution = displayResolution;
        this.network = network;
        this.os = os;
        this.chipset = chipset;
        this.memoryCard = memoryCard;
        this.launchDate = launchDate;
        this.dimension = dimension;
        this.weight = weight;
        this.sim = sim;
        this.harga = harga;
        this.merek = merek;
        this.model = model;
        this.version = version;
        this.rating = rating;
    }

    /*Comparator for sorting the list by roll no*/
    public static Comparator<Smartphones> SortPriceAsc = new Comparator<Smartphones>() {

	public int compare(Smartphones s1, Smartphones s2) {

	   int harga1 = s1.getHarga();
	   int harga2 = s2.getHarga();

	   /*For ascending order*/
	   return harga1-harga2;

	   /*For descending order*/
	   //rollno2-rollno1;
    }};
    
    public static Comparator<Smartphones> SortPriceDesc = new Comparator<Smartphones>() {

	public int compare(Smartphones s1, Smartphones s2) {

	   int harga1 = s1.getHarga();
	   int harga2 = s2.getHarga();

	   /*For ascending order*/
	   return harga2-harga1;

	   /*For descending order*/
	   //rollno2-rollno1;
    }};
    
    public static Comparator<Smartphones> SortRatingAsc = new Comparator<Smartphones>() {

	public int compare(Smartphones s1, Smartphones s2) {

	   float rating1 = s1.getRating();
	   float rating2 = s2.getRating();

	   /*For ascending order*/
           if (rating2 < rating1){
               return -1;
           }
           if (rating2 > rating1){
               return 1;
           }else{
               return 0;
           }

	   /*For descending order*/
	   //rollno2-rollno1;
    }};
    
    public static Comparator<Smartphones> SortRatingDesc = new Comparator<Smartphones>() {

	public int compare(Smartphones s1, Smartphones s2) {

	   float rating1 = s1.getRating();
	   float rating2 = s2.getRating();

	   /*For ascending order*/
	   if (rating1 < rating2){
               return -1;
           }
           if (rating1 > rating2){
               return 1;
           }else{
               return 0;
           }
	   /*For descending order*/
	   //rollno2-rollno1;
    }};

    /**
     * @return the rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(float rating) {
        this.rating = rating;
    }
    /**
     * @return the kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * @param kode the kode to set
     */
    public void setKode(String kode) {
        this.kode = kode;
    }   
    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the gambar
     */
    public String getGambar() {
        return gambar;
    }

    /**
     * @return the cpu
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * @param cpu the cpu to set
     */
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the gpu
     */
    public String getGpu() {
        return gpu;
    }

    /**
     * @param gpu the gpu to set
     */
    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    /**
     * @return the kamera_depan
     */
    public int getKamera_depan() {
        return kamera_depan;
    }

    /**
     * @param kamera_depan the kamera_depan to set
     */
    public void setKamera_depan(int kamera_depan) {
        this.kamera_depan = kamera_depan;
    }

    /**
     * @return the kamera_belakang
     */
    public int getKamera_belakang() {
        return kamera_belakang;
    }

    /**
     * @param kamera_belakang the kamera_belakang to set
     */
    public void setKamera_belakang(int kamera_belakang) {
        this.kamera_belakang = kamera_belakang;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the displaySize
     */
    public String getDisplaySize() {
        return displaySize;
    }

    /**
     * @param displaySize the displaySize to set
     */
    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    /**
     * @return the displayType
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     * @param displayType the displayType to set
     */
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     * @return the displayResolution
     */
    public String getDisplayResolution() {
        return displayResolution;
    }

    /**
     * @param displayResolution the displayResolution to set
     */
    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    /**
     * @return the network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * @param network the network to set
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return the chipset
     */
    public String getChipset() {
        return chipset;
    }

    /**
     * @param chipset the chipset to set
     */
    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    /**
     * @return the memoryCard
     */
    public String getMemoryCard() {
        return memoryCard;
    }

    /**
     * @param memoryCard the memoryCard to set
     */
    public void setMemoryCard(String memoryCard) {
        this.memoryCard = memoryCard;
    }

    /**
     * @param gambar the gambar to set
     */
    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    /**
     * @return the launchDate
     */
    public String getLaunchDate() {
        return launchDate;
    }

    /**
     * @param launchDate the launchDate to set
     */
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    /**
     * @return the dimension
     */
    public String getDimension() {
        return dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the sim
     */
    public String getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(String sim) {
        this.sim = sim;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }

    /**
     * @return the merek
     */
    public String getMerek() {
        return merek;
    }

    /**
     * @param merek the merek to set
     */
    public void setMerek(String merek) {
        this.merek = merek;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }
    
    /**
     * @return the id_post
     */
    public int getId_post() {
        return id_post;
    }

    /**
     * @param id_post the id_post to set
     */
    public void setId_post(int id_post) {
        this.id_post = id_post;
    }
}
