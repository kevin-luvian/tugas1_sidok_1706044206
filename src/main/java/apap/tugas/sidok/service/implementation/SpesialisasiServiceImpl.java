package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import apap.tugas.sidok.repository.SpesialisasiDb;
import apap.tugas.sidok.service.SpesialisasiService;

import java.util.ArrayList;
import java.util.List;

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
    public SpesialisasiModel changeSpesialisasi(SpesialisasiModel spesialisasiModel) {
        SpesialisasiModel targetSpesialisasi = getSpesialisasiById(spesialisasiModel.getId());
        targetSpesialisasi.setNama(spesialisasiModel.getNama());
        targetSpesialisasi.setGelar(spesialisasiModel.getGelar());
        return targetSpesialisasi;
    }

    @Override
    public List<SpesialisasiModel> getAll() {
        return spesialisasiDb.findAll();
    }

    @Override
    public void addSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel) {
        SpesialisasiModel spesialisasi = spesialisasiDokterModel.getSpesialisasiModel();
        List<SpesialisasiDokterModel> spesialisasiDokterList = spesialisasi.getListSpesialisasiDokter();
        if(spesialisasiDokterList == null) {
            spesialisasiDokterList = new ArrayList<>();
        }
        spesialisasiDokterList.add(spesialisasiDokterModel);
        spesialisasi.setListSpesialisasiDokter(spesialisasiDokterList);
    }
}
