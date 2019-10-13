package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.connector.JadwalJagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JadwalJagaDb extends JpaRepository<JadwalJagaModel, Long>{
}