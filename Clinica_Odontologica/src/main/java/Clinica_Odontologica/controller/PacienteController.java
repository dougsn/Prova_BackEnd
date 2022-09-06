package Clinica_Odontologica.controller;

import Clinica_Odontologica.entity.PacienteEntity;
import Clinica_Odontologica.service.IPacienteService;
import Clinica_Odontologica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> addPaciente(@RequestBody PacienteEntity pacienteEntity) throws SQLException {

        ResponseEntity responseEntity = null;

        PacienteEntity pacienteEntity1 = pacienteService.addPaciente(pacienteEntity);

        if(pacienteEntity1.getNome() == null  || pacienteEntity1.getSobrenome() == null || pacienteEntity1.getEndereco() == null || pacienteEntity1.getDataAlta() == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteEntity1,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<PacienteEntity>> buscarTodos() throws SQLException {

        ResponseEntity responseEntity = null;

        List<PacienteEntity> pacienteEntities;
        pacienteEntities = pacienteService.findAllPacientes();

        if(pacienteEntities.size() == 0){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteEntities,HttpStatus.OK);
        }
        return responseEntity;

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteEntity> findPacienteById(@PathVariable Long id) throws SQLException {

        ResponseEntity responseEntity = null;

        if(pacienteService.findPacienteById(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.findPacienteById(id), HttpStatus.OK);
        }
        return responseEntity;

    }

    @PutMapping("/alterar")
    public ResponseEntity<PacienteEntity> atualizarPaciente(@RequestBody PacienteEntity pacienteEntity) throws SQLException{

        ResponseEntity responseEntity = null;

        if(pacienteService.findPacienteById(pacienteEntity.getId())==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.atualizarPaciente(pacienteEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws SQLException {
        ResponseEntity responseEntity = null;

        if(pacienteService.findPacienteById(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(pacienteService.deletePaciente(id), HttpStatus.OK);
        }
        return responseEntity;
    }

}