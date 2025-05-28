package com.Habitaciones.Habitacion.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Habitaciones.Habitacion.Model.Camas;

@Repository
public interface CamasRepository extends JpaRepository<Camas, Integer> {

 
}
