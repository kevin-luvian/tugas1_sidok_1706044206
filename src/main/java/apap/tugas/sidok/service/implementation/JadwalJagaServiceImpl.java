package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.repository.JadwalJagaDb;
import apap.tugas.sidok.service.JadwalJagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
    @Autowired
    JadwalJagaDb jadwalJagaDb;

    @Override
    public void addJadwalJaga(JadwalJagaModel jadwalJagaModel) {
        jadwalJagaDb.save(jadwalJagaModel);
    }

    @Override
    public void deleteJadwalJaga(JadwalJagaModel jadwalJagaModel) {
        jadwalJagaDb.delete(jadwalJagaModel);

    }

    @Override
    public List<JadwalJagaModel> getListJadwalJaga() {
        return jadwalJagaDb.findAll();
    }

    @Override
    public List<JadwalJagaModel> getByDokter(DokterModel dokter) {
        return jadwalJagaDb.findByDokterModel(dokter);
    }
}
