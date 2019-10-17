package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.repository.DokterDb;
import apap.tugas.sidok.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    DokterDb dokterDb;

    public static Map parseDokterModel(DokterModel dokter) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("nama", dokter.getNama());
        map.put("id", dokter.getId()+"");
        map.put("nip", dokter.getNip());
        map.put("nik", dokter.getNik());
        map.put("tempatLahir", dokter.getTempatLahir());
        map.put("tanggalLahir", dokter.toStringTanggalLahir());
        map.put("jenisKelamin", dokter.toStringJenisKelamin());        
        map.put("listSpesialisasiDokter", dokter.toStringListSpesialisasiDokter());
        return map;
    }

    public static List<Map> compareDokterList(List<DokterModel> aList, List<DokterModel> bList){
        List<Map> dokterList = new ArrayList<>();
        for (DokterModel dokter : aList) {
            if (bList.contains(dokter)) {
                dokterList.add(parseDokterModel(dokter));
            }
        }
        return dokterList;
    }

    @Override
    public void createDokterNIP(DokterModel dokter) {
        String NIP = dokter.getNip();
        while (getDokterByNIP(NIP) != (null) || NIP.equals("none")){
            NIP = dokter.createNIP();
        }
        dokter.setNip(NIP);
    }

    @Override
    public List<DokterModel> getListDokter(){
        return dokterDb.findAll();
    }

    @Override
    public void addDokter(DokterModel dokter) {
        createDokterNIP(dokter);
        dokterDb.save(dokter);
    }

    @Override
    public void deleteDokter(DokterModel dokter) {
        dokterDb.delete(dokter);
    }

    @Override
    public DokterModel getDokterById(Long dokterId) {
        return dokterDb.findById(dokterId).get();
    }

    @Override
    public DokterModel getDokterByNIK(String dokterNIK) {
        return dokterDb.findByNik(dokterNIK);
    }

    @Override
    public DokterModel getDokterByNIP(String dokterNIP) {
        return dokterDb.findByNip(dokterNIP);
    }

    @Override
    public DokterModel changeDokter(DokterModel dokter) {
        DokterModel targetDokter = getDokterById(dokter.getId());
        targetDokter.setNama(dokter.getNama());
        targetDokter.setTempatLahir(dokter.getTempatLahir());
        targetDokter.setTanggalLahir(dokter.getTanggalLahir());
        targetDokter.setNik(dokter.getNik());
        targetDokter.setJenisKelamin(dokter.getJenisKelamin());
        targetDokter.setListSpesialisasiDokter(dokter.getListSpesialisasiDokter());
        targetDokter.setListJadwalJaga(dokter.getListJadwalJaga());
        addDokter(targetDokter);
        return targetDokter;
    }
}
