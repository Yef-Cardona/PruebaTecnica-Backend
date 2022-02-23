package com.pruebatecnica.Prueba.Service;

import com.pruebatecnica.Prueba.Model.Usuario;
import com.pruebatecnica.Prueba.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     * Transaccion para obtener todos los usuarios
     * @return List<Usuario>
     */
    @Transactional
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    /**
     * Transaccion para buscar o listar los usuarios
     * segun el texto ingresado
     * @param nombre
     * @return Usuario
     */
    @Transactional
    public List<Usuario> getUsuariosSearched(String nombre){
        return usuarioRepository.findByname(nombre);
    }

    /**
     * Transaccion para registrar un usuario
     * @param usuario
     */
    @Transactional
    public boolean crearUsuario(Usuario usuario){
        //busca si el usuario que se va a crear ya existe pero esta eliminado de manera logica
        List<Usuario> usuarioEncontrado = usuarioRepository.findByname(usuario.getNombre());
        try {
            if(usuarioEncontrado.get(0).getActivo() =='N') {
                // si lo encuentra, lo actualiza si y solo si esta inactivo
                usuarioEncontrado.get(0).setActivo(usuario.getActivo());
                usuarioEncontrado.get(0).setId_Rol(usuario.getId_Rol());
                usuarioRepository.save(usuarioEncontrado.get(0));
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            //Si no lo encuentra, entra al catch y lo crea como nuevo
            System.out.println(usuario.getId_Rol());
            usuarioRepository.save(usuario);
            return true;
        }

    }

    /**
     * Transaccion para actualizar el usuario vigente
     * @param usuario
     * @return Usuario
     */
    @Transactional
    public boolean actualizarUsuario(int id_Usuario, Usuario usuario){
        Optional<Usuario> user = usuarioRepository.findById(id_Usuario);
        List<Usuario> usuarioEncontrado = usuarioRepository.findByname(usuario.getNombre());

        try {
                if (usuarioEncontrado.get(0).getNombre().equalsIgnoreCase(usuario.getNombre())) {
                    if (usuarioEncontrado.get(0).getId_Usuario() == id_Usuario) {
                        System.out.println(usuarioEncontrado.get(0).getId_Usuario());
                        usuarioEncontrado.get(0).setActivo(usuario.getActivo());
                        usuarioEncontrado.get(0).setId_Rol(usuario.getId_Rol());
                        System.out.println(usuarioEncontrado.get(0).getNombre());
                        usuarioEncontrado.get(0).setNombre(usuario.getNombre());
                        usuarioRepository.save(usuarioEncontrado.get(0));
                        return true;
                    } else {
                        return false;
                    }
            }
            user.get().setActivo(usuario.getActivo());
            user.get().setId_Rol(usuario.getId_Rol());
            user.get().setNombre(usuario.getNombre());
            usuarioRepository.save(user.get());
            return true;
        }catch(Exception e){
            user.get().setActivo(usuario.getActivo());
            user.get().setId_Rol(usuario.getId_Rol());
            user.get().setNombre(usuario.getNombre());
            usuarioRepository.save(user.get());
            return true;
        }
    }

    /**
     * Transaccion para eliminar de manera logica el usuario
     * es decir marcar el campo deleted_at
     * @param id
     * @return
     */
    @Transactional
    public boolean eliminarUsuario(int id){
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        usuarioRepository.delete(usuarioEncontrado.get());
        return true;
    }
}
