package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.service.SpesialisasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SpesialisasiController {
    @Autowired
    private SpesialisasiService spesialisasiService;

    @RequestMapping(value = "/spesialisasi/tambah", method = RequestMethod.GET)
    public String addSpesialisasiFormPage(Model model) {
        SpesialisasiModel newSpesialisasi = new SpesialisasiModel();
        model.addAttribute("spesialisasi", newSpesialisasi);
        return "form-add-spesialisasi";
    }

    @RequestMapping(value = "/spesialisasi/tambah", method = RequestMethod.POST)
    private String addSpesialisasiSubmit(@ModelAttribute SpesialisasiModel spesialisasi, Model model){
        spesialisasiService.addSpesialisasi(spesialisasi);
        model.addAttribute("spesialisasi", spesialisasi);
        return "add-spesialisasi";
    }
}
