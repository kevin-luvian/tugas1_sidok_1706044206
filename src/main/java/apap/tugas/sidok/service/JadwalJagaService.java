package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;

import java.util.List;

public interface JadwalJagaService {
    void addJadwalJaga(JadwalJagaModel jadwalJagaModel);
    void deleteJadwalJaga(JadwalJagaModel jadwalJagaModel);
    List<JadwalJagaModel> getListJadwalJaga();
    List<JadwalJagaModel> getByDokter(DokterModel dokter);
    List<JadwalJagaModel> getByPoli(PoliModel poli);
    List<DokterModel> getDokterByPoli(PoliModel poli);
}