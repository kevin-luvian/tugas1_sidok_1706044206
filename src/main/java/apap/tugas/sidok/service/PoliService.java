package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.PoliModel;
import java.util.List;

public interface PoliService {
    void addPoli(PoliModel poliModel);
    void deletePoli(PoliModel poliModel);
    List<PoliModel> getAll();
    PoliModel getPoliById(Long poliId);
    PoliModel changePoli(PoliModel poliModel);
}