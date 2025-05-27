package com.Habitaciones.Habitacion.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity 
@Table(name = "camas")
@NoArgsConstructor
@AllArgsConstructor
public class Camas{

    @Id
    private Long id;
    private String NumeroCama;
    private int CantidadCamas;
    private String EstadoCama;
    
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    @JsonBackReference
    private Habitacion habitacion;
}
