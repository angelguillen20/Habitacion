package com.Habitaciones.Habitacion.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Habitaciones.Habitacion.Model.Camas;
import com.Habitaciones.Habitacion.Model.Habitacion;

@Repository
public class HabitacionRepository {

    
    public HabitacionRepository() {
        // Creamos la habitación primero (sin camas)
        Habitacion habitacion1 = new Habitacion(1, "101", "Individual", 1, new ArrayList<>());
        Habitacion habitacion2 = new Habitacion(2, "102", "Doble", 2, new ArrayList<>());

        // Creamos camas asociadas a cada habitación
        Camas cama1 = new Camas(1L, "Cama 1", 1, "Disponible", habitacion1);
        Camas cama2 = new Camas(2L, "Cama 1", 1, "Ocupada", habitacion2);
        Camas cama3 = new Camas(3L, "Cama 2", 1, "Disponible", habitacion2);

        // Asociamos las camas a las habitaciones
        habitacion1.getCamas().add(cama1);

        habitacion2.getCamas().add(cama2);
        habitacion2.getCamas().add(cama3);

        // Añadimos habitaciones a la lista
        listaHabitacion.add(habitacion1);
        listaHabitacion.add(habitacion2);
    }



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
