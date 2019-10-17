package apap.tugas.sidok.service.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import apap.tugas.sidok.repository.SpesialisasiDokterDb;
import apap.tugas.sidok.service.SpesialisasiDokterService;

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

    @Override
    public List<DokterModel> getDokterBySpesialisasi(SpesialisasiModel spesialisasiModel) {
        List<DokterModel> dokterList = new ArrayList<>();
        for (SpesialisasiDokterModel spesialisasiDokter : getBySpesialisasi(spesialisasiModel)) {
            dokterList.add(spesialisasiDokter.getDokterModel());
        }
        return dokterList;
    }

    @Override
    public List<SpesialisasiDokterModel> getAll() {
        return spesialisasiDokterDb.findAll();
    }

    @Override
    public List<Map> getAllSpesialisasiCount(List<SpesialisasiModel> spesialisasiList) {
        List<SpesialisasiDokterModel> listSpesialisasiDokter = getAll();
        Map<String, Integer> map = new HashMap();

        for(SpesialisasiModel sm : spesialisasiList){
            map.put(sm.getNama(), 0);
        }

        for(SpesialisasiDokterModel sdm : listSpesialisasiDokter){
            SpesialisasiModel spesialisasi = sdm.getSpesialisasiModel();
            map.put(spesialisasi.getNama(), map.get(spesialisasi.getNama())+1);
        }

        List<Map> result = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            Map<String, String> temp = new HashMap();
            temp.put("nama", entry.getKey());
            temp.put("total", entry.getValue()+"");
            result.add(temp);
        }

        return result;
    }
}
