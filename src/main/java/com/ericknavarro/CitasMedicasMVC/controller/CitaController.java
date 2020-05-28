/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.controller;

import com.ericknavarro.CitasMedicasMVC.model.Cita;
import com.ericknavarro.CitasMedicasMVC.service.CitaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Usuario
 */
@Controller
public class CitaController {
    
    @Autowired
    private CitaService service;
    
    @Value("${spring.application.name}")
    private String appName;
    
    @GetMapping("/citas")
    public String allCitas(Model model){
        
        List<Cita> citas = service.findAllCitas();
        
        model.addAttribute("appName", appName);
        model.addAttribute("citas", citas);
        return "citas";
    }
    
    @GetMapping("/agregar")
    public String formCita(Model model){
        
        model.addAttribute("method", "post");
        model.addAttribute("url", "/agregar");
        
        return "form";
    }
    
    @PostMapping("/agregar")
    public String addCita(@ModelAttribute Cita cita){
        cita.setFecha(new Date());
        service.saveCita(cita);
        return "redirect:citas";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarFormCita(@PathVariable("id") Integer id, Model model){
        Cita cita = service.findCitaById(id);
        model.addAttribute("citaDB", cita);
        model.addAttribute("method", "PUT");
        model.addAttribute("url", "/actualizar");
                
        return "form";
    }
    
    @PostMapping("/actualizar")
    public String updateCita(@ModelAttribute Cita cita){
        service.updateCita(cita.id, cita);
        return "redirect:/citas";
    }
    
    @GetMapping("/eliminar/{id}")
    public String deleteCita(@PathVariable("id") Integer id){
        service.deleteCita(id);
        return "redirect:/citas";
    }
    
    @GetMapping("/login") 
    public String test(){
        return "login";
    }
    
}
