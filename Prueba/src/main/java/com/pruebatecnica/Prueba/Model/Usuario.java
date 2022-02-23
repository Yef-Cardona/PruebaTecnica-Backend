package com.pruebatecnica.Prueba.Model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
//@Where(clause = "activo='S'")//anotacion para omitir los registros con deleted_at diferente a null
public class Usuario{

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Usuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private char activo;
    @JoinColumn(name = "id_rol")
    private int id_Rol;

    public Usuario() {
    }
    public Usuario(int id_Usuario, String nombre, char activo, int id_Rol) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.activo = activo;
        this.id_Rol = id_Rol;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_rol) {
        this.id_Rol = id_rol;
    }
}
