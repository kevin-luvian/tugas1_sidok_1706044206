package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.DokterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import apap.tugas.sidok.service.DokterService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class DokterController {
    @Autowired
    private DokterService dokterService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String home(Model model) {
        List<DokterModel> dokterList = dokterService.getListDokter();
        model.addAttribute("dokterList", dokterList);
        return "home";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        DokterModel newDokter = new DokterModel();
        newDokter.setNip("none");
        model.addAttribute("dokter", newDokter);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    private String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model){
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);
        return "add-dokter";
    }

    @RequestMapping(value="/dokter", method=RequestMethod.GET)
    public String viewByNIK(
            @RequestParam(value = "nikDokter") String NIK,
            Model model ) {
        //mengambil obj berdasarkan id
        DokterModel dokter = dokterService.getDokterByNIK(NIK);

        //add obj utk di render
        model.addAttribute("dokter", dokter);
        //return template
        return "view-dokter";
    }

}
