package apap.tugas.sidok.model.connector;

import apap.tugas.sidok.model.base.DoctorModel;
import apap.tugas.sidok.model.base.PoliModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Table
public class JadwalJagaModel {
    @Id
    @Size(min = 20, max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Column(name="day", nullable = false)
    private String day;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_poli", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PoliModel poliModel;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_dokter", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DoctorModel doctorModel;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public PoliModel getPoliModel() {
        return poliModel;
    }

    public void setPoliModel(PoliModel poliModel) {
        this.poliModel = poliModel;
    }

    public DoctorModel getDoctorModel() {
        return doctorModel;
    }

    public void setDoctorModel(DoctorModel doctorModel) {
        this.doctorModel = doctorModel;
    }
}