package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.repository.JadwalJagaDb;
import apap.tugas.sidok.service.JadwalJagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.*;

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

    @Override
    public List<JadwalJagaModel> getByPoli(PoliModel poli) {
        return jadwalJagaDb.findByPoliModel(poli);
    }

    @Override
    public List<DokterModel> getDokterByPoli(PoliModel poli) {
        List<DokterModel> dokterList = new ArrayList<>();
        for(JadwalJagaModel jadwalJaga : getByPoli(poli)){
            dokterList.add(jadwalJaga.getDokterModel());
        }
        return dokterList;
    }

    @Override
    public DokterModel getMostDokterByPoli(PoliModel poli) {
        List<DokterModel> dokterList = getDokterByPoli(poli);

        DokterModel maxKey = null;
        int maxCounts = 0;
        
        HashMap<DokterModel, Integer> map = new HashMap<>();
        
        for(DokterModel dokter: dokterList){
            System.out.println(dokter.getNama());
            if (map.containsKey(dokter)) {
                map.put(dokter, map.get(dokter)+1);
            } else {
                map.put(dokter, 1);
            }
            if(map.get(dokter) > maxCounts){
                maxCounts = map.get(dokter);
                maxKey = dokter;
            }
        }

        return maxKey;
    }
}
