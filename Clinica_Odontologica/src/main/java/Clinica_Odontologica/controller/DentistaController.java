package Clinica_Odontologica.controller;


import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")

public class DentistaController {

    private final DentistaServiceImpl dentistaServiceImpl;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaServiceImpl = dentistaService;
    }

    @PostMapping
    public ResponseEntity<DentistaEntity> addDentista(@RequestBody DentistaEntity dentistaEntity) throws SQLException{

      ResponseEntity responseEntity = null;

      DentistaEntity dentistaEntity1 = dentistaServiceImpl.addDentista(dentistaEntity);

        if(dentistaEntity1.getNome() == null || dentistaEntity1.getMatricula() == null || dentistaEntity1.getSobrenome() == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaEntity1,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<DentistaEntity>> buscarTodos() throws SQLException {

        ResponseEntity responseEntity = null;

        List<DentistaEntity> dentistaEntities;
        dentistaEntities = dentistaServiceImpl.findAllDentistas();

        if(dentistaEntities.size() == 0){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaEntities,HttpStatus.OK);
        }
        return responseEntity;

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DentistaEntity> findDentistaById(@PathVariable Long id) throws SQLException {

        ResponseEntity responseEntity = null;

        if(dentistaServiceImpl.findDentistaById(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaServiceImpl.findDentistaById(id), HttpStatus.OK);
        }
        return responseEntity;

    }

    @PutMapping("/alterar")
    public ResponseEntity<DentistaEntity> atualizarDentista(@RequestBody DentistaEntity dentistaEntity) throws SQLException{

        ResponseEntity responseEntity = null;

        if(dentistaServiceImpl.findDentistaById(dentistaEntity.getId())==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaServiceImpl.atualizarDentista(dentistaEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deleteDentista(@PathVariable Long id) throws SQLException {
        ResponseEntity responseEntity = null;

        if(dentistaServiceImpl.findDentistaById(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaServiceImpl.deleteDentista(id), HttpStatus.OK);
        }
        return responseEntity;
    }




}
