package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.SpesialisasiModel;

public interface SpesialisasiService {
    void addSpesialisasi(SpesialisasiModel spesialisasiModel);
    void deleteSpesialisasi(SpesialisasiModel spesialisasiModel);
    SpesialisasiModel getSpesialisasiById(Long spesialisasiId);
    SpesialisasiModel changePoli(SpesialisasiModel spesialisasiModel);
}