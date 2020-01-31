package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.DokterModel;
import java.util.List;

public interface DokterService {
    void createDokterNIP(DokterModel dokter);

    void addDokter(DokterModel dokter);
    void deleteDokter(DokterModel dokter);
    DokterModel getDokterById(Long dokterId);
    DokterModel getDokterByNIK(String dokterNik);
    DokterModel getDokterByNIP(String dokterNip);
    List<DokterModel> getListDokter();
    DokterModel changeDokter(DokterModel dokter);

    //List<DokterModel> findAllDokterByPoliId(BigInteger poliId);
    //List<DokterModel> findAllDokterBySpesialisasiId(BigInteger spesialisasiId);
}