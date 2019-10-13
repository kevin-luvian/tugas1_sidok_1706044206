package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.repository.SpesialisasiDb;
import apap.tugas.sidok.service.SpesialisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpesialisasiServiceImpl implements SpesialisasiService {
    @Autowired
    SpesialisasiDb spesialisasiDb;

    @Override
    public void addSpesialisasi(SpesialisasiModel spesialisasiModel) {
        spesialisasiDb.save(spesialisasiModel);
    }

    @Override
    public void deleteSpesialisasi(SpesialisasiModel spesialisasiModel) {
        spesialisasiDb.delete(spesialisasiModel);
    }

    @Override
    public SpesialisasiModel getSpesialisasiById(Long spesialisasiId) {
        return spesialisasiDb.findById(spesialisasiId).get();
    }

    @Override
    public SpesialisasiModel changePoli(SpesialisasiModel spesialisasiModel) {
        SpesialisasiModel targetSpesialisasi = getSpesialisasiById(spesialisasiModel.getId());
        targetSpesialisasi.setNama(spesialisasiModel.getNama());
        targetSpesialisasi.setGelar(spesialisasiModel.getGelar());
        return targetSpesialisasi;
    }
}
