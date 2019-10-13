package apap.tugas.sidok.service;

import apap.tugas.sidok.model.base.PoliModel;
import java.math.BigInteger;

public interface PoliService {
    void addPoli(PoliModel poliModel);
    void deletePoli(PoliModel poliModel);
    PoliModel getPoliById(Long poliId);
    PoliModel changePoli(PoliModel poliModel);
}