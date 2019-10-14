package apap.tugas.sidok.controller;

import apap.tugas.sidok.model.base.PoliModel;
import apap.tugas.sidok.service.PoliService;

import java.util.List;

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
}
