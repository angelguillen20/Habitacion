package com.Habitaciones.Habitacion.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "habitaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String NumeroHabit; //Numero de la Habitacion
    private String TipoHabitacion;
    private int CapacidadMax;
    

    @OneToMany(mappedBy = "habitacion",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Camas> camas;
}
