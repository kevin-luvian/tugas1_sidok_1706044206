package apap.tugas.sidok.model.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

@Entity
@Table
public class DokterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nip", nullable = false, unique = true)
    private String nip;

    @NotNull
    @Column(name = "nik", nullable = false, unique = true)
    private String nik;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_lahir", nullable = false, columnDefinition = "DATE")
    private Date tanggalLahir;

    @NotNull
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @OneToMany(mappedBy = "dokterModel", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listJadwalJaga;

    @OneToMany(mappedBy = "dokterModel", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpesialisasiDokterModel> listSpesialisasiDokter;

    public String toStringListSpesialisasiDokter(){
        String output = "";
        for(SpesialisasiDokterModel sdm : getListSpesialisasiDokter()){
            output += sdm.getSpesialisasiModel().getNama();
            output += "; ";
        }
        return output;
    }

    public void addJadwalJaga(JadwalJagaModel jadwalJaga){
        if(listJadwalJaga == null) setListJadwalJaga(new ArrayList<>());
        listJadwalJaga.add(jadwalJaga);
    }

    public String toStringJenisKelamin() {
        if(getJenisKelamin() == 1) return "laki-laki";
        return "perempuan";
    }

    public String toStringTanggalLahir() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"));
        cal.setTime(getTanggalLahir());
        String year = String.format("%04d", cal.get(Calendar.YEAR));
        String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
        String day = String.format("%02d",cal.get(Calendar.DAY_OF_MONTH));
        
        return year+"-"+month+"-"+day;
    }

    public String createNIP(){
        Random rnd = new Random();
        String NIP = (Calendar.getInstance().get(Calendar.YEAR) + 5) +"";
        NIP += getStringTanggalLahir();
        NIP += getJenisKelamin();
        NIP += (char) (rnd.nextInt(26)+65);
        NIP += (char) (rnd.nextInt(26)+65);
        return NIP;
    }

    private String getStringTanggalLahir(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"));
        cal.setTime(getTanggalLahir());
        String year = String.format("%02d", cal.get(Calendar.YEAR)%100);
        String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
        String day = String.format("%02d",cal.get(Calendar.DAY_OF_MONTH));
        return day+month+year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public List<JadwalJagaModel> getListJadwalJaga() {
        return listJadwalJaga;
    }

    public void setListJadwalJaga(List<JadwalJagaModel> listJadwalJaga) {
        this.listJadwalJaga = listJadwalJaga;
    }

    public List<SpesialisasiDokterModel> getListSpesialisasiDokter() {
        return listSpesialisasiDokter;
    }

    public void setListSpesialisasiDokter(List<SpesialisasiDokterModel> listSpesialisasiDokter) {
        this.listSpesialisasiDokter = listSpesialisasiDokter;
    }
}