/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.dao;

import com.ericknavarro.CitasMedicasMVC.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>{
    
    Cita findCitaById(Integer id);
    
}
