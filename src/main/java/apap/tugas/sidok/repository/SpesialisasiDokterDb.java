package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpesialisasiDokterDb extends JpaRepository<SpesialisasiDokterModel, Long>{
    List<SpesialisasiDokterModel> findByDokterModel(DokterModel dokterModel);
    List<SpesialisasiDokterModel> findBySpesialisasiModel(SpesialisasiModel spesialisasiModel);
}