package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.repository.DokterDb;
import apap.tugas.sidok.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DokterServiceImpl implements DokterService {
    @Autowired
    DokterDb dokterDb;

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
        dokterDb.save(targetDokter);
        return targetDokter;
    }
}
