package com.Habitaciones.Habitacion.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Habitaciones.Habitacion.Model.Camas;
import com.Habitaciones.Habitacion.Model.Habitacion;
import com.Habitaciones.Habitacion.Repository.HabitacionRepository;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerHabitacion() {
        return habitacionRepository.obtenerHabitacion();
    }

    public Habitacion buscarPorId(int id){
        return habitacionRepository.buscarPorId(id);
    }

    public Habitacion agregarHabitacion(Habitacion habitacion){
       return habitacionRepository.agregarHabitacion(habitacion);
    }
    public void eliminarHabitacion(int id){
        habitacionRepository.eliminarHabitacion(id);
    }
    public void actualizarHabitacion(Habitacion habitacion){
        habitacionRepository.actualizarHabitacion(habitacion);
    }

    public List<Camas> obtenerCamasDeHabitacion(int habitacionId) {
        Habitacion habitacion = habitacionRepository.buscarPorId(habitacionId);
        if (habitacion != null && habitacion.getCamas() != null) {
            return habitacion.getCamas();
        }
        return new ArrayList<>();
    }

    public boolean agregarCamasHabitacion(int habitacion_id, Camas camas){
        Habitacion habitacion = habitacionRepository.buscarPorId(habitacion_id);
        if (habitacion != null) {
            if (habitacion.getCamas() == null){
                habitacion.setCamas(new ArrayList<>());
            }
            habitacion.getCamas().add(camas);
            return true;
        }
        return false;
    }

    public boolean actualizarCamaEnHabitacion(int habitacion_id, Camas camaactualizada){
        Habitacion habitacion = habitacionRepository.buscarPorId(habitacion_id);
        if(habitacion != null && habitacion.getCamas() != null){
            List<Camas> camas = habitacion.getCamas();
            for (int i = 0; i < camas.size(); i++) {
                if (camas.get(i).getNumeroCama().equals(camaactualizada.getNumeroCama())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eliminarCamasHabitacion(int habitacion_id,long camaid){
        Habitacion habitacion = habitacionRepository.buscarPorId(habitacion_id);
        if(habitacion != null && habitacion.getCamas() != null){
            return habitacion.getCamas().removeIf(c -> c.getId().equals(camaid));
        }
        return false;
    }
}
