package com.example.clase2gtics.controller;

import com.example.clase2gtics.entity.Territory;
import com.example.clase2gtics.repository.RegionRepository;
import com.example.clase2gtics.repository.TerritoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/territory")
public class TerritoryController {

    @Autowired
    TerritoryRepository tr;
    @Autowired
    RegionRepository rr;

    @GetMapping({"","/listar"})
    public String lista(Model model){
        model.addAttribute("listaTerritorios",tr.findAll());
        return "territory/lista";
    }
    @GetMapping("/crear")
    public String formCrear(Model model){
        model.addAttribute("listaRegiones", rr.findAll());
        return "territory/crear";
    }

    @GetMapping("/editar")
    public String formCrear(Model model, @RequestParam("id") String id){
        model.addAttribute("territorio",tr.findByTerritoryID(id));
        model.addAttribute("listaRegiones", rr.findAll());
        return "territory/editar";
    }

    @PostMapping("/guardar")
    public String crearTerritorio(Territory territorio){

        System.out.println("Id del territorio: " + territorio.getTerritoryID());
        System.out.println("Descripción: " + territorio.getTerritoryDescription());
        System.out.println("Id de Región: " + territorio.getRegion().getRegionID());

        tr.save(territorio);
        return "redirect:/territory/lista";
    }
}
