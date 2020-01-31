package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long>{
    public DokterModel findByNik(String nik);
    public DokterModel findByNip(String nip);
}