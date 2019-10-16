package apap.tugas.sidok.model.connector;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
@Table
public class SpesialisasiDokterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_spesialisasi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpesialisasiModel spesialisasiModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DokterModel dokterModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpesialisasiModel getSpesialisasiModel() {
        return spesialisasiModel;
    }

    public void setSpesialisasiModel(SpesialisasiModel spesialisasiModel) {
        this.spesialisasiModel = spesialisasiModel;
    }

    public DokterModel getDokterModel() {
        return dokterModel;
    }

    public void setDokterModel(DokterModel dokterModel) {
        this.dokterModel = dokterModel;
    }
}