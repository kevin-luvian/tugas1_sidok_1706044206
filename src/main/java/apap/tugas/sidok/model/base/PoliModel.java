package apap.tugas.sidok.model.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Table
public class PoliModel {
    @Id
    @Size(min = 20, max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Size(max = 30)
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "location", nullable = false)
    private int location;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}