package com.Habitaciones.Habitacion.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Habitaciones.Habitacion.Model.Habitacion;

@Repository
public class HabitacionRepository {
    private List<Habitacion> listaHabitacion = new ArrayList<>();

    public List<Habitacion> obtenerHabitacion(){
        return listaHabitacion;
    }


    public Habitacion buscarPorId(int id){
        for (Habitacion habitacion : listaHabitacion) {
            if(habitacion.getId() == id){
                return habitacion;
            }
        }
        return null;
    }

    public Habitacion agregarHabitacion(Habitacion habitacion){
        listaHabitacion.add(habitacion);
        return habitacion;
    }

    public void actualizarHabitacion(Habitacion habitacionActualizada){
        for (int i = 0; i < listaHabitacion.size(); i++) {
            if(listaHabitacion.get(i).getId()== habitacionActualizada.getId()){
                listaHabitacion.set(i, habitacionActualizada);
                return;
            }
        }
    }

    public void eliminarHabitacion(int id){
        Habitacion habitacionEliminar = buscarPorId(id);
        if (habitacionEliminar != null){
            listaHabitacion.remove(habitacionEliminar);
            return;
        }
    }
    
}
