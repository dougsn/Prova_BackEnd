package Clinica_Odontologica.controller;


import Clinica_Odontologica.entity.ConsultaEntity;
import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.exceptions.BadRequestException;
import Clinica_Odontologica.exceptions.ResourceNotFoundException;
import Clinica_Odontologica.service.impl.ConsultaServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

@RestController
@RequestMapping("/clinica/consulta")
public class ConsultaController {

    final static Logger log = getLogger(String.valueOf(ConsultaController.class));
    private final ConsultaServiceImpl consultaService;

    public ConsultaController(ConsultaServiceImpl consultaService) {
        this.consultaService = consultaService;

    }


    @PostMapping
    public ResponseEntity<ConsultaEntity> addConsulta(@RequestBody ConsultaEntity consultaEntity) throws BadRequestException {

        try {
            log.info("Realizando a adição da consulta");
            return ResponseEntity.ok(consultaService.adicionar(consultaEntity));
        } catch (Exception e) {
            log.error("Erro ao realizar a adição da consulta");
            throw new BadRequestException("Não foi possível salvar a consulta informada.");
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ConsultaEntity> addConsulta2(@RequestBody ConsultaEntity consultaEntity) throws BadRequestException {

        try {
            log.info("Realizando a adição da consulta");
            return ResponseEntity.ok(consultaService.adicionar(consultaEntity));
        } catch (Exception e) {
            log.error("Erro ao realizar a adição da consulta");
            throw new BadRequestException("Não foi possível salvar a consulta informada.");
        }

    }

    @GetMapping
    public ResponseEntity<List<ConsultaEntity>> buscarTodos() throws ResourceNotFoundException {
        log.info("Realizando a busca de todas as consultas");
        try {
            return ResponseEntity.ok(consultaService.findAll());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca de todas as consultas");
            throw new ResourceNotFoundException("Não foi possível realizar a buscar as consultas.");
        }

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ConsultaEntity> findConsultaById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            log.info("Buscando a consulta pelo ID: " + id);
            return ResponseEntity.ok(consultaService.findById(id).get());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca da consulta de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível buscar a consulta de ID: " + id);
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<ConsultaEntity> atualizarConsulta(@RequestBody ConsultaEntity consultaEntity) throws ResourceNotFoundException {

        ResponseEntity responseEntity = null;

        if (!consultaService.findById(consultaEntity.getId()).isPresent()) {
            log.error("Não foi possível atualizar a consulta");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Consulta não foi encontrado para ser atualizado!");
        } else {
            log.info("Atualizando a consulta");
            responseEntity = new ResponseEntity(consultaService.atualizar(consultaEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws ResourceNotFoundException {

        try {
            log.info("Excluindo a consulta de ID: " + id);
            return ResponseEntity.ok(consultaService.deletar(id));
        } catch (Exception e) {
            log.error("Não foi possível realizar a exclusão da consulta de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível deletar a consulta de ID: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
