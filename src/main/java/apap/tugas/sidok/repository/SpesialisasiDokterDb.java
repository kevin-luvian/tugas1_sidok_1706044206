package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SpesialisasiDokterDb extends JpaRepository<SpesialisasiDokterModel, Long>{
}