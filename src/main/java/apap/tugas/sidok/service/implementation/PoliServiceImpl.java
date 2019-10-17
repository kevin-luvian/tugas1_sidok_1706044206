package apap.tugas.sidok.service.implementation;

import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.repository.PoliDb;
import apap.tugas.sidok.service.PoliService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PoliServiceImpl implements PoliService {
    @Autowired
    PoliDb poliDb;

    @Override
    public void addPoli(PoliModel poliModel) {
        poliDb.save(poliModel);
    }

    @Override
    public void deletePoli(PoliModel poliModel) {
        poliDb.delete(poliModel);
    }

    @Override
    public PoliModel getPoliById(Long poliId) {
        Optional<PoliModel> o = poliDb.findById(poliId);
        if(o.isPresent()) return o.get();
        return null;
    }

    @Override
    public PoliModel changePoli(PoliModel poliModel) {
        PoliModel targetPoli = getPoliById(poliModel.getId());
        targetPoli.setNama(poliModel.getNama());
        targetPoli.setLocation(poliModel.getLocation());
        return targetPoli;
    }

    @Override
    public List<PoliModel> getAll() {
        return poliDb.findAll();
    }
}
