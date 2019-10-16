package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.JadwalJagaService;
import apap.tugas.sidok.service.PoliService;
import apap.tugas.sidok.service.SpesialisasiDokterService;
import apap.tugas.sidok.service.SpesialisasiService;
import apap.tugas.sidok.service.implementation.DokterServiceImpl;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtherController {
    @Autowired
    private SpesialisasiDokterService spesialisasiDokterService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private PoliService poliService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @RequestMapping(value="/jadwal/tambah/{nipDokter}", method = RequestMethod.GET)
    public String addJadwalJagaDokterPage(
            @PathVariable String nipDokter,
            Model model
    ) {
        DokterModel dokter = dokterService.getDokterByNIP(nipDokter);
        List<JadwalJagaModel> jadwalJagaList = jadwalJagaService.getByDokter(dokter);
        JadwalJagaModel jadwalJaga = new JadwalJagaModel();
        jadwalJaga.setDokterModel(dokter);
        List<String> hariList = Arrays.asList(new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"});
        model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(dokter));
        model.addAttribute("jadwalJaga", jadwalJaga);
        model.addAttribute("jadwalJagaList", jadwalJagaList);
        model.addAttribute("poliList", poliService.getAll());
        model.addAttribute("hariList", hariList);
        return "jadwal-jaga-dokter";
    }

    @RequestMapping(value = "/jadwal/tambah/{nipDokter}", method = RequestMethod.POST)
    private String addDokterSubmit(
        @PathVariable String nipDokter,
        @ModelAttribute JadwalJagaModel jadwalJaga,
        Model model
    ) {
        DokterModel dokter = dokterService.getDokterByNIP(nipDokter);
        dokter.addJadwalJaga(jadwalJaga);

        PoliModel poli = poliService.getPoliById(jadwalJaga.getPoliModel().getId());
        poli.addJadwalJaga(jadwalJaga);

        jadwalJagaService.addJadwalJaga(jadwalJaga);
        model.addAttribute("jadwalJaga", jadwalJaga);
        return "add-jadwal-jaga";
    }

    @RequestMapping(value="/cari", method=RequestMethod.GET)
    public String viewByNIK(
            @RequestParam(value = "idSpesialisasi") String idSpesialisasi,
            @RequestParam(value = "idPoli") String idPoli,
            Model model ) {
        //mengambil obj berdasarkan id
        SpesialisasiModel spesialisasi = spesialisasiService.getSpesialisasiById(Long.valueOf(idSpesialisasi));
        List<SpesialisasiDokterModel> spesialisasiList = spesialisasiDokterService.getBySpesialisasi(spesialisasi);
        //add obj utk di render
        PoliModel poli = poliService.getPoliById(Long.valueOf(idPoli));
        List<JadwalJagaModel> jadwalJagaList = jadwalJagaService.getByPoli(poli);
        //return template
        
        return "view-dokter";
    }
}
