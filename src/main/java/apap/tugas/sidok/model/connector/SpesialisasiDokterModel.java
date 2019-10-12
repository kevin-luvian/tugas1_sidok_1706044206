package apap.tugas.sidok.model.connector;

import apap.tugas.sidok.model.base.DoctorModel;
import apap.tugas.sidok.model.base.SpecializationModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Table
public class SpesialisasiDokterModel {
    @Id
    @Size(min = 20, max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_spesialisasi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private SpecializationModel specializationModel;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DoctorModel doctorModel;
}