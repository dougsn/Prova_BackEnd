package Clinica_Odontologica.controller;

import Clinica_Odontologica.model.Paciente;
import Clinica_Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) throws SQLException {

        ResponseEntity responseEntity = null;

        Paciente Paciente1 = pacienteService.salvar(paciente);

        if(Paciente1.getNome() == null  || Paciente1.getSobrenome() == null || Paciente1.getEndereco() == null || Paciente1.getDataAlta() == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(Paciente1,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarTodos() throws SQLException {

        ResponseEntity responseEntity = null;

        List<Paciente> Pacientes;
        Pacientes = pacienteService.buscarTodos();

        if(Pacientes.size() == 0){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(Pacientes,HttpStatus.OK);
        }
        return responseEntity;

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable int id) throws SQLException {

        ResponseEntity responseEntity = null;

        if(pacienteService.buscarPorId(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.buscarPorId(id), HttpStatus.OK);
        }
        return responseEntity;

    }

    @PutMapping("/alterar")
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente) throws SQLException{

        ResponseEntity responseEntity = null;

        if(pacienteService.buscarPorId(paciente.getId())==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.alterar(paciente), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable int id) throws SQLException {
        ResponseEntity responseEntity = null;

        if(pacienteService.buscarPorId(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.excluir(id), HttpStatus.OK);
        }
        return responseEntity;
    }

}
