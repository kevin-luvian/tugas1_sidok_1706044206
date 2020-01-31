package apap.tugas.sidok.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sidok.model.base.DokterModel;
import apap.tugas.sidok.model.base.SpesialisasiModel;
import apap.tugas.sidok.model.connector.SpesialisasiDokterModel;
import apap.tugas.sidok.service.DokterService;
import apap.tugas.sidok.service.SpesialisasiDokterService;
import apap.tugas.sidok.service.SpesialisasiService;
import apap.tugas.sidok.service.implementation.DokterServiceImpl;

@Controller
public class DokterController {
    @Autowired
    private DokterService dokterService;

    @Autowired
    private SpesialisasiService spesialisasiService;

    @Autowired
    private SpesialisasiDokterService spesialisasiDokterService;

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
        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getAll();

        DokterModel newDokter = new DokterModel();
        newDokter.setNip("none");
        
        SpesialisasiDokterModel newSpesialisasiDokter = new SpesialisasiDokterModel();
        newSpesialisasiDokter.setDokterModel(newDokter);

        ArrayList<SpesialisasiDokterModel> listSpesialisasiDokter = new ArrayList<>();
        listSpesialisasiDokter.add(newSpesialisasiDokter);

        newDokter.setListSpesialisasiDokter(listSpesialisasiDokter);

        model.addAttribute("dokter", newDokter);
        model.addAttribute("spesialisasiList", spesialisasiList);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST, params= {"submit"})
    private String addDokterSubmit(
        @ModelAttribute DokterModel dokter,
        Model model
    ) {
        if(dokterService.getDokterByNIK(dokter.getNik()) != null){
            model.addAttribute("nik", dokter.getNik());
            return "error-dokter-nik";
        }
        List<SpesialisasiDokterModel> spesialisasiDokterList = dokter.getListSpesialisasiDokter();
        dokter.setListSpesialisasiDokter(null);
        dokterService.addDokter(dokter);

        for (SpesialisasiDokterModel spesialisasiDokter : spesialisasiDokterList){
            if(spesialisasiDokter.getSpesialisasiModel() != null){
                SpesialisasiDokterModel newSpesialisasiDokter = new SpesialisasiDokterModel();
                newSpesialisasiDokter.setDokterModel(dokter);
                newSpesialisasiDokter.setSpesialisasiModel(spesialisasiService.getSpesialisasiById(spesialisasiDokter.getSpesialisasiModel().getId()));
                spesialisasiDokterService.addSpesialisasiDokter(newSpesialisasiDokter);
                spesialisasiService.addSpesialisasiDokter(newSpesialisasiDokter);
            }
        }

        if(dokter.getListJadwalJaga() == null) dokter.setListJadwalJaga(new ArrayList<>());


        model.addAttribute("dokter", dokter);
        return "add-dokter";
    }
    
    @RequestMapping(value="/dokter/tambah", method = RequestMethod.POST, params={"addRow"})
    public String addRow(@ModelAttribute DokterModel formDokter, BindingResult bindingResult, Model model) {
        List<SpesialisasiModel> spesialisasiList = spesialisasiService.getAll();

        if(formDokter.getListSpesialisasiDokter() == null){
            formDokter.setListSpesialisasiDokter(new ArrayList<SpesialisasiDokterModel>());
        }

        SpesialisasiDokterModel newSpesialisasiDokter = new SpesialisasiDokterModel();
        newSpesialisasiDokter.setDokterModel(formDokter);
        formDokter.getListSpesialisasiDokter().add(newSpesialisasiDokter);

        model.addAttribute("dokter", formDokter);
        model.addAttribute("spesialisasiList", spesialisasiList);
        return "form-add-dokter";
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

        model.addAttribute("dokter", DokterServiceImpl.parseDokterModel(updatedDokter));
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
        model.addAttribute("listSpesialisasiDokter", dokter.getListSpesialisasiDokter());
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
