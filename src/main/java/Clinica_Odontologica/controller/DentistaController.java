package Clinica_Odontologica.controller;


import Clinica_Odontologica.model.Dentista;
import Clinica_Odontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dentista")

public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public ResponseEntity<Dentista> salvar(@RequestBody Dentista dentista) throws SQLException{

      ResponseEntity responseEntity = null;

      Dentista dentista1 = dentistaService.salvar(dentista);

        if(dentista1.getNome() == null || dentista1.getMatricula() == null || dentista1.getSobrenome() == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentista1,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Dentista>>  buscarTodos() throws SQLException {

        ResponseEntity responseEntity = null;

        List<Dentista> dentistas;
        dentistas = dentistaService.buscarTodos();

        if(dentistas.size() == 0){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistas,HttpStatus.OK);
        }
        return responseEntity;

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Dentista> buscarPorId(@PathVariable int id) throws SQLException {

        ResponseEntity responseEntity = null;

        if(dentistaService.buscarPorId(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaService.buscarPorId(id), HttpStatus.OK);
        }
        return responseEntity;

    }

    @PutMapping("/alterar")
    public ResponseEntity<Dentista> alterar(@RequestBody Dentista dentista) throws SQLException{

        ResponseEntity responseEntity = null;

        if(dentistaService.buscarPorId(dentista.getId())==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaService.alterar(dentista), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable int id) throws SQLException {
        ResponseEntity responseEntity = null;

        if(dentistaService.buscarPorId(id)==null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(dentistaService.excluir(id), HttpStatus.OK);
        }
        return responseEntity;
    }




}
