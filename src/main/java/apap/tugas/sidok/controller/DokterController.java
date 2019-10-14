package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.DokterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.implementation.DokterServiceImpl;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DokterController {
    @Autowired
    private DokterService dokterService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String home(Model model) {
        List<Map> dokterList = new ArrayList<>();
        for(DokterModel dokter : dokterService.getListDokter()){
            Map dokterMap = DokterServiceImpl.parseDokterModel(dokter);
            dokterList.add(dokterMap);
        }
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

    @RequestMapping(value="dokter/update/{dokterId}", method = RequestMethod.GET)
    public String updateDokterFormPage(
            @PathVariable Long dokterId,
            Model model
    ) {
        DokterModel targetDokter = dokterService.getDokterById(dokterId);
        model.addAttribute("dokter", targetDokter);
        return "form-update-dokter";
    }

    //API yg digunakan utk submit form change store
    @RequestMapping(value="dokter/update/{dokterId}", method=RequestMethod.POST)
    public String updateDokterFormSubmit(
        @PathVariable Long dokterId,
        @ModelAttribute DokterModel dokter,
        Model model
    ) {
        dokter.setId(dokterId);
        DokterModel updatedDokter = dokterService.changeDokter(dokter);
        
        model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(dokter));
        return "update-dokter";
    }

    @RequestMapping(value="/dokter", method=RequestMethod.GET)
    public String viewByNIK(
            @RequestParam(value = "nikDokter") String NIK,
            Model model ) {
        //mengambil obj berdasarkan id
        DokterModel dokter = dokterService.getDokterByNIK(NIK);
        //add obj utk di render
        model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(dokter));
        //return template
        return "view-dokter";
    }
    
    //API yg digunakan utk menuju halaman form change store
    @RequestMapping(value="dokter/delete/{dokterId}", method = RequestMethod.GET)
    public String deleteStore(
            @PathVariable Long dokterId,
            Model model
    ) {
        //mengambill existing data store
        DokterModel existingDokter = dokterService.getDokterById(dokterId);
        model.addAttribute("dokter", existingDokter);

        dokterService.deleteDokter(existingDokter);
        return "delete-dokter";
    }
}
