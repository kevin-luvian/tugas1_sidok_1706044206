package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long>{
    List<JadwalJagaModel> findByDokterModel(DokterModel dokterModel);
    List<JadwalJagaModel> findByPoliModel(PoliModel PoliModel);
}