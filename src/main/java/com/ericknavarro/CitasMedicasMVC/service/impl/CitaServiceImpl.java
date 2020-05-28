/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.service.impl;

import com.ericknavarro.CitasMedicasMVC.dao.CitaRepository;
import com.ericknavarro.CitasMedicasMVC.model.Cita;
import com.ericknavarro.CitasMedicasMVC.service.CitaService;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@AllArgsConstructor
public class CitaServiceImpl implements CitaService {

    public CitaRepository repository;
    
    @Override
    public List<Cita> findAllCitas() {
        return repository.findAll();
    }

    @Override
    public Cita findCitaById(Integer id) {
        return repository.findCitaById(id);
    }

    @Override
    public Cita saveCita(Cita cita) {
        cita.setFecha(new Date());
        return repository.save(cita);
    }

    @Override
    public Cita updateCita(Integer id, Cita cita) {
        Cita citaDB = findCitaById(id);
        citaDB.setNombreDoctor(cita.getNombreDoctor());
        citaDB.setNombrePaciente(cita.getNombrePaciente());
        citaDB.setFecha(new Date());
        
        repository.save(citaDB);
        
        return citaDB;
    }

    @Override
    public void deleteCita(Integer id) {
        repository.deleteById(id);
    }
    
}
