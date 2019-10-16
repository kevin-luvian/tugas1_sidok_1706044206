package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.model.connector.JadwalJagaModel;
import apap.tugas.sidok.service.JadwalJagaService;
import apap.tugas.sidok.service.PoliService;
import apap.tugas.sidok.service.implementation.DokterServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PoliController {
    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @RequestMapping(value = "/poli", method = RequestMethod.GET)
    private String home(Model model) {
        List<PoliModel> poliList = poliService.getAll();
        model.addAttribute("poliList", poliList);
        return "view-all-poli";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    public String addPoliFormPage(Model model) {
        PoliModel newPoli = new PoliModel();
        model.addAttribute("poli", newPoli);
        return "form-add-poli";
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.POST)
    private String addPoliSubmit(@ModelAttribute PoliModel poli, Model model){
        poliService.addPoli(poli);
        model.addAttribute("poli", poli);
        return "add-poli";
    }

    @RequestMapping(value="/poli/dokter/{idPoli}", method = RequestMethod.GET)
    public String viewPoliByDokterPage(
            @PathVariable Long idPoli,
            Model model
    ) {
        PoliModel poli = poliService.getPoliById(idPoli);
        List<JadwalJagaModel> jadwalJagaPoli = jadwalJagaService.getByPoli(poli);
        List<Map> dokterModels = new ArrayList<>();
        for(JadwalJagaModel i : jadwalJagaPoli){
            dokterModels.add(DokterServiceImpl.parseDokterModel(i.getDokterModel()));
        }
        model.addAttribute("dokterModels", dokterModels);
        return "view-dokter-by-poli";
    }

    @RequestMapping(value="poli/update/{poliId}", method = RequestMethod.GET)
    public String updateDokterFormPage(
            @PathVariable Long poliId,
            Model model
    ) {
        PoliModel targetPoli = poliService.getPoliById(poliId);
        model.addAttribute("poli", targetPoli);
        return "form-update-poli";
    }

    //API yg digunakan utk submit form change store
    @RequestMapping(value="poli/update/{poliId}", method=RequestMethod.POST)
    public String updatePoliFormSubmit(
        @PathVariable Long poliId,
        @ModelAttribute PoliModel poli,
        Model model
    ) {
        poli.setId(poliId);
        PoliModel updatedPoli = poliService.changePoli(poli);
        model.addAttribute("poli", updatedPoli);
        return "update-poli";
    }
    
    @RequestMapping(value="poli/delete/{poliId}", method = RequestMethod.GET)
    public String deletePoli(
            @PathVariable Long poliId,
            Model model
    ) {
        PoliModel existingPoli = poliService.getPoliById(poliId);
        model.addAttribute("poli", existingPoli);
        poliService.deletePoli(existingPoli);
        return "delete-poli";
    }
}
