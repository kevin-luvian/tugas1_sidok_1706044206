package apap.tugas.sidok.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.JadwalJagaService;
import apap.tugas.sidok.service.PoliService;
import apap.tugas.sidok.service.SpesialisasiDokterService;
import apap.tugas.sidok.service.SpesialisasiService;
import apap.tugas.sidok.service.implementation.DokterServiceImpl;

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

    @RequestMapping(value="/bonus", method = RequestMethod.GET)
    public String bonusPage(Model model) {
        List<Map> mappedSpesialisasiList = spesialisasiDokterService.getAllSpesialisasiCount(spesialisasiService.getAll());

        model.addAttribute("spesialisasiList", mappedSpesialisasiList);

        return "view-all-spesialisasi";
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

        List<String> hariList = Arrays.asList(new String[]{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"});
        List<JadwalJagaModel> jadwalJagaList = jadwalJagaService.getByDokter(dokter);

        model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(dokter));
        model.addAttribute("jadwalJaga", jadwalJaga);
        model.addAttribute("jadwalJagaList", jadwalJagaList);
        model.addAttribute("poliList", poliService.getAll());
        model.addAttribute("hariList", hariList);

        return "jadwal-jaga-dokter";
    }

    @RequestMapping(value="/cari", params="idSpesialisasi", method=RequestMethod.GET)
    public String viewBySpesialisasiPoli(
            @RequestParam(value = "idSpesialisasi") Long idSpesialisasi,
            @RequestParam(value = "idPoli") Long idPoli,
            Model model ) {
        //mengambil obj berdasarkan id
        SpesialisasiModel spesialisasi = spesialisasiService.getSpesialisasiById(Long.valueOf(idSpesialisasi));
        List<DokterModel> spesialisasiDokterListDokter = spesialisasiDokterService.getDokterBySpesialisasi(spesialisasi);

        PoliModel poli = poliService.getPoliById(Long.valueOf(idPoli));
        List<DokterModel> jadwalJagaListDokter = jadwalJagaService.getDokterByPoli(poli);

        List<Map> dokterList = DokterServiceImpl.compareDokterList(spesialisasiDokterListDokter, jadwalJagaListDokter);

        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getAll();
        List<PoliModel> poliList = poliService.getAll();

        model.addAttribute("spesialisasiList", spesialisasiList);
        model.addAttribute("poliList", poliList);
        model.addAttribute("dokterList", dokterList);
        
        return "view-dokter-by-spesialisasi-poli";
    }

    @RequestMapping(value="/cari", method=RequestMethod.GET)
    public String viewMostByPoli(
        @RequestParam(value = "idPoli") String idPoli,
        Model model ) {
            PoliModel poli = poliService.getPoliById(Long.valueOf(idPoli));

            DokterModel dokter = jadwalJagaService.getMostDokterByPoli(poli);
            
            List<PoliModel> poliList = poliService.getAll();

            List<SpesialisasiDokterModel> listSpesialisasiDokter = new ArrayList<>();
            if(dokter != null) listSpesialisasiDokter = dokter.getListSpesialisasiDokter();

            model.addAttribute("poliList", poliList);
            model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(dokter));
            model.addAttribute("listSpesialisasiDokter", listSpesialisasiDokter);
            return "view-most-dokter-by-poli";
        }
}
