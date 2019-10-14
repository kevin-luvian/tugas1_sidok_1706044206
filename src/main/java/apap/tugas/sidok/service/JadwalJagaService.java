package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
    void addJadwalJaga(JadwalJagaModel jadwalJagaModel);
    void deleteJadwalJaga(JadwalJagaModel jadwalJagaModel);
    List<JadwalJagaModel> getListJadwalJaga();
    List<JadwalJagaModel> getByDokter(DokterModel dokter);

    //List<DokterModel> findAllDokterByPoliId(BigInteger poliId);
    //List<DokterModel> findAllDokterBySpesialisasiId(BigInteger spesialisasiId);
}