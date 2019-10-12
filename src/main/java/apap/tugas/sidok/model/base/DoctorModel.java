package apap.tugas.sidok.model.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table
public class DoctorModel {

    @Id
    @Size(min = 20, max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @UniqueElements
    @Size(min = 13, max = 13)
    @Column(name = "NIP", nullable = false)
    private String NIP;

    @NotNull
    @Size(min = 13, max = 13)
    @Column(name = "NIK", nullable = false)
    private String NIK;

    @NotNull
    @Size(max = 30)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private int jenisKelamin;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
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
}