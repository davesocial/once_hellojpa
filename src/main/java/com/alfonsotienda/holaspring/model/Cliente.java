package com.alfonsotienda.holaspring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Cliente
 */
@Entity // Genera objetos en base de datos
 public class Cliente {

    @Id // Hace la primary key
    private Integer id;

        private String nombre;

            private String apellido;
            
                private String telefono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    
}