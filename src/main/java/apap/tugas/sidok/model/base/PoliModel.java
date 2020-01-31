package apap.tugas.sidok.model.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import apap.tugas.sidok.model.connector.JadwalJagaModel;

@Entity
@Table
public class PoliModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "poliModel", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadwalJagaModel> listJadwalJaga;

    public void addJadwalJaga(JadwalJagaModel jadwalJaga){
        if(listJadwalJaga == null) setListJadwalJaga(new ArrayList<>());
        listJadwalJaga.add(jadwalJaga);
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<JadwalJagaModel> getListJadwalJaga() {
        return listJadwalJaga;
    }

    public void setListJadwalJaga(List<JadwalJagaModel> listJadwalJaga) {
        this.listJadwalJaga = listJadwalJaga;
    }
}