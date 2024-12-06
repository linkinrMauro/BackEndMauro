package com.proyecto.taller.model.DTO;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private List<String> imagenesUrls; // URLs de las imágenes

    // Getters y Setters
}
