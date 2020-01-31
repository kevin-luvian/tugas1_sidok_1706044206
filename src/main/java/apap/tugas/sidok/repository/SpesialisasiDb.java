package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.SpesialisasiModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpesialisasiDb extends JpaRepository<SpesialisasiModel, Long> {
    public Optional<SpesialisasiModel> findById(Long id);
}