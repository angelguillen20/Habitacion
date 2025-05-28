package com.Habitaciones.Habitacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Habitaciones.Habitacion.Model.Camas;
import com.Habitaciones.Habitacion.Model.Habitacion;
import com.Habitaciones.Habitacion.Service.HabitacionService;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.obtenerTodasLasHabitaciones();
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscarHabitacion(@PathVariable int id) {
        return habitacionService.obtenerHabitacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Habitacion> guardarHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion habitacionGuardada = habitacionService.crearHabitacion(habitacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacionGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHabitacion(
            @PathVariable int id,
            @RequestBody Habitacion habitacion) {
        String mensaje = habitacionService.actualizarHabitacion(id, habitacion);
        if (mensaje.contains("no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable int id) {
        String mensaje = habitacionService.eliminarHabitacion(id);
        if (mensaje.contains("no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{id}/camas")
    public ResponseEntity<List<Camas>> obtenerCamas(@PathVariable int id) {
        return habitacionService.obtenerHabitacionPorId(id)
                .map(habitacion -> {
                    List<Camas> camas = habitacion.getCamas();
                    return ResponseEntity.ok(camas);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
