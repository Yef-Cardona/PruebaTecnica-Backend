package com.pruebatecnica.Prueba.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rol")
public class Rol{

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Rol;
    @Column(name = "nombre")
    private String nombre;

    public Rol() {
    }
    public Rol(int id_Rol, String nombre) {
        this.id_Rol = id_Rol;
        this.nombre = nombre;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
