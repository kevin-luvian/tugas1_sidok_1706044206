package apap.tugas.sidok.model.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

@Entity
@Table
public class DokterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nip", nullable = false)
    private String nip;

    @NotNull
    @Column(name = "nik", nullable = false)
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

    public String toStringJenisKelamin() {
        if(getJenisKelamin() == 1) return "laki-laki";
        return "perempuan";
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

    public String toStringTanggalLahir() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Jakarta"));
        cal.setTime(getTanggalLahir());
        String year = String.format("%04d", cal.get(Calendar.YEAR));
        String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
        String day = String.format("%02d",cal.get(Calendar.DAY_OF_MONTH));
        
        return year+"-"+month+"-"+day;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
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
}