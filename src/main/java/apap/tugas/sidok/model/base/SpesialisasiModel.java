package apap.tugas.sidok.model.base;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;

@Entity
@Table
public class SpesialisasiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="gelar", nullable = false)
    private String gelar;

    @OneToMany(mappedBy = "spesialisasiModel", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SpesialisasiDokterModel> listSpesialisasiDokter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public List<SpesialisasiDokterModel> getListSpesialisasiDokter() {
        return listSpesialisasiDokter;
    }

    public void setListSpesialisasiDokter(List<SpesialisasiDokterModel> listSpesialisasiDokter) {
        this.listSpesialisasiDokter = listSpesialisasiDokter;
    }
}