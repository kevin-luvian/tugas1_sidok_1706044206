package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import apap.tugas.sidok.repository.SpesialisasiDb;
import apap.tugas.sidok.repository.SpesialisasiDokterDb;
import apap.tugas.sidok.service.SpesialisasiDokterService;
import apap.tugas.sidok.service.SpesialisasiService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpesialisasiDokterServiceImpl implements SpesialisasiDokterService {
    @Autowired
    SpesialisasiDokterDb spesialisasiDokterDb;

    @Override
    public void addSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel) {
        spesialisasiDokterDb.save(spesialisasiDokterModel);
    }

    @Override
    public void deleteSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel) {
        spesialisasiDokterDb.delete(spesialisasiDokterModel);

    }

    @Override
    public List<SpesialisasiDokterModel> getByDokter(DokterModel dokterModel) {
        return spesialisasiDokterDb.findByDokterModel(dokterModel);
    }

    @Override
    public void createSpesialisasiDokter(DokterModel dokter, SpesialisasiModel spesialisasi) {
        SpesialisasiDokterModel newSpesialisasiDokter = new SpesialisasiDokterModel();
        newSpesialisasiDokter.setDokterModel(dokter);
        newSpesialisasiDokter.setSpesialisasiModel(spesialisasi);
        addSpesialisasiDokter(newSpesialisasiDokter);
    }

    @Override
    public List<SpesialisasiDokterModel> getBySpesialisasi(SpesialisasiModel spesialisasiModel) {
        return spesialisasiDokterDb.findBySpesialisasiModel(spesialisasiModel);
    }
}
