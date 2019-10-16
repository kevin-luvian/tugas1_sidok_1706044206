package apap.tugas.sidok.service;

import java.util.List;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;

public interface SpesialisasiService {
    void addSpesialisasi(SpesialisasiModel spesialisasiModel);
    void deleteSpesialisasi(SpesialisasiModel spesialisasiModel);
    void addSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel);
    List<SpesialisasiModel> getAll();
    SpesialisasiModel getSpesialisasiById(Long spesialisasiId);
    SpesialisasiModel changeSpesialisasi(SpesialisasiModel spesialisasiModel);
}