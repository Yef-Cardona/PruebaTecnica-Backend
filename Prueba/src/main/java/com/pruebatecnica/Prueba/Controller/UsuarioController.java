package com.pruebatecnica.Prueba.Controller;

import com.pruebatecnica.Prueba.Model.Usuario;
import com.pruebatecnica.Prueba.Service.UsuarioService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que recibe las peticiones para realizar todo lo que tiene que ver con el CRUD
 * de usuarios que van a manejar el sistema
 * @Author Yeferson Cardona Giraldo
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("usuario")
public class UsuarioController {

    /**
     * Injeccion de dependecia del servicio UsuarioService, donde estan los métodos para
     * realizar el CRUD de usuarios
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método que recibe la petición GET para obtener un usuario Especifico
     *
     * @Return ResponseEntity con el usuario Encontrado
     */
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> getUserSearch(@PathVariable String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUsuariosSearched(nombre));
        } catch (Exception e) {
            JSONObject objetoJson = new JSONObject();
            objetoJson.put("Codigo error", HttpStatus.NOT_FOUND.value());
            objetoJson.put("Descripción error", HttpStatus.NOT_FOUND);
            objetoJson.put("Mensaje", "No existen registros en la BD");
            String jsonString = objetoJson.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
        }
    }


    /**
     * Método que recibe la petición GET para obtener todos los usuarios registrados
     *
     * @Return ResponseEntity con los usuarios
     */
    @GetMapping("/listar")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerUsuarios());
        } catch (Exception e) {
            JSONObject objetoJson = new JSONObject();
            objetoJson.put("Codigo error", HttpStatus.NOT_FOUND.value());
            objetoJson.put("Descripción error", HttpStatus.NOT_FOUND);
            objetoJson.put("Mensaje", "No existen registros en la BD");
            String jsonString = objetoJson.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
        }
    }
    /**
     * Método que recibe la petición POST para registrar un usuario en específico
     * @RequestBody Usuario
     */
    @PostMapping("/crear")
    public  ResponseEntity<?> createUser(@RequestBody Usuario usuario){
        try {
            if(usuarioService.crearUsuario(usuario)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(null);
            }
            else {
                JSONObject objetoJson = new JSONObject();
                objetoJson.put("Codigo error", HttpStatus.BAD_REQUEST.value());
                objetoJson.put("Descripción error", HttpStatus.BAD_REQUEST);
                objetoJson.put("Mensaje", "No es posible crear el usuario, puede que ya exista uno con el mismo nombre");
                String jsonString = objetoJson.toString();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
            }
        }catch (Exception e) {
            JSONObject objetoJson = new JSONObject();
            objetoJson.put("Codigo error", HttpStatus.BAD_REQUEST.value());
            objetoJson.put("Descripción error", HttpStatus.BAD_REQUEST);
            objetoJson.put("Mensaje", "Ocurrio un problema");
            String jsonString = objetoJson.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
        }
    }

    /**
     * Método que recibe la petición PUT para editar/actualizar un usuario en específico
     * @PathVariable id_Usuario
     * @RequestBody Usuario
     */
    @PutMapping("/actualizar/{id_Usuario}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id_Usuario, @RequestBody Usuario usuario){
        try {
            if(usuarioService.actualizarUsuario(id_Usuario, usuario)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
            }
            else{
                JSONObject objetoJson = new JSONObject();
                objetoJson.put("Codigo error", HttpStatus.BAD_REQUEST.value());
                objetoJson.put("Descripción error", HttpStatus.BAD_REQUEST);
                objetoJson.put("Mensaje", "No es posible actualizar el usuario, puede que ya exista uno con el mismo nombre");
                String jsonString = objetoJson.toString();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
            }
        }catch (Exception e){
            JSONObject objetoJson = new JSONObject();

            objetoJson.put("Codigo error", HttpStatus.NOT_FOUND.value());
            objetoJson.put("Descripción error", HttpStatus.NOT_FOUND);
            objetoJson.put("Mensaje", "No existe el usuario con esa identificación");

            String jsonString = objetoJson.toString();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);

        }

    }

    /**
     * Método que recibe la petición DELETE para eliminar un usuario en específico
     * @PathVariable identificacion
     */
    @DeleteMapping("/eliminar/{id_Usuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id_Usuario){
        try {
            usuarioService.eliminarUsuario(id_Usuario);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (Exception e){
            JSONObject objetoJson = new JSONObject();

            objetoJson.put("Codigo error", HttpStatus.NOT_FOUND.value());
            objetoJson.put("Descripción error", HttpStatus.NOT_FOUND);
            objetoJson.put("Mensaje", "No es posible eliminar el usuario ya que no existen registros con la identificación indicada");

            String jsonString = objetoJson.toString();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonString);
        }

    }
}

