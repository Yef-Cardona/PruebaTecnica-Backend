package com.pruebatecnica.Prueba.Repository;

import com.pruebatecnica.Prueba.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUsuarioRepository extends JpaRepository <Usuario,Integer> {

    //Query nativa para listar los usuarios o buscar un usuario según su nombre
    @Query(value = "Select * from usuario where nombre = ?", nativeQuery = true)
    List<Usuario> findByname(String nombre);



}
