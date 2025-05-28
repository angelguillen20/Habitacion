package com.Habitaciones.Habitacion.Service;
import com.Habitaciones.Habitacion.Repository.HabitacionRepository;
import com.Habitaciones.Habitacion.Model.Habitacion;
import com.Habitaciones.Habitacion.Repository.CamasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private CamasRepository camaRepository;

    @Transactional
    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Optional<Habitacion> obtenerHabitacionPorId(int id) {
        return habitacionRepository.findById(id);
    }

    public List<Habitacion> obtenerTodasLasHabitaciones() {
        return habitacionRepository.findAll();
    }

    @Transactional
    public String actualizarHabitacion(int id, Habitacion nuevaHabitacion) {
        Optional<Habitacion> optionalHabitacion = habitacionRepository.findById(id);
        if (optionalHabitacion.isPresent()) {
            Habitacion existente = optionalHabitacion.get();
            existente.setNumeroHabit(nuevaHabitacion.getNumeroHabit());
            existente.setTipoHabitacion(nuevaHabitacion.getTipoHabitacion());
            existente.setCapacidadMax(nuevaHabitacion.getCapacidadMax());

            // Actualiza camas asociadas
            if (nuevaHabitacion.getCamas() != null) {
                existente.getCamas().clear();
                nuevaHabitacion.getCamas().forEach(cama -> {
                    cama.setHabitacion(existente);
                    existente.getCamas().add(cama);
                });
            }

            habitacionRepository.save(existente);
            return "Habitación actualizada correctamente";
        } else {
            return "Habitación no encontrada";
        }
    }

    @Transactional
    public String eliminarHabitacion(int id) {
        Optional<Habitacion> habitacionOpt = habitacionRepository.findById(id);
        if (habitacionOpt.isPresent()) {
            habitacionRepository.delete(habitacionOpt.get());
            return "Habitación eliminada correctamente";
        } else {
            return "Habitación no encontrada";
        }
    }
}


