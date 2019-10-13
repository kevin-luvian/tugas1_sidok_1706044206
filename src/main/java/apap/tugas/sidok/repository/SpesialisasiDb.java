package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.SpesialisasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long>{
}