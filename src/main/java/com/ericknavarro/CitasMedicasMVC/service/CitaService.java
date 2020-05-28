/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.service;

import com.ericknavarro.CitasMedicasMVC.model.Cita;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface CitaService {
    
    List<Cita> findAllCitas();
    
    Cita findCitaById(Integer id);
    
    Cita saveCita(Cita cita);
    
    Cita updateCita(Integer id, Cita cita);
    
    void deleteCita(Integer id);
    
}
