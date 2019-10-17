package apap.tugas.sidok.service;

import java.util.List;
import java.util.Map;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;

public interface SpesialisasiDokterService {
    void addSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel);
    void deleteSpesialisasiDokter(SpesialisasiDokterModel spesialisasiDokterModel);
    void createSpesialisasiDokter(DokterModel dokter, SpesialisasiModel spesialisasi);
    List<SpesialisasiDokterModel> getAll();
    List<Map> getAllSpesialisasiCount(List<SpesialisasiModel> spesialisasiList);
    List<SpesialisasiDokterModel> getBySpesialisasi(SpesialisasiModel spesialisasiModel);
    List<DokterModel> getDokterBySpesialisasi(SpesialisasiModel spesialisasiModel);
    List<SpesialisasiDokterModel> getByDokter(DokterModel dokterModel);
}