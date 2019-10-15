package apap.tugas.sidok.repository;

import apap.tugas.sidok.model.base.PoliModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliDb extends JpaRepository<PoliModel, Long>{
}