package Clinica_Odontologica.controller;


import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.exceptions.BadRequestException;
import Clinica_Odontologica.exceptions.ResourceNotFoundException;
import Clinica_Odontologica.service.impl.DentistaServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

@RestController
@RequestMapping("/clinica/dentista")

public class DentistaController {
    final static Logger log = getLogger(String.valueOf(DentistaController.class));
    private final DentistaServiceImpl dentistaServiceImpl;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaServiceImpl = dentistaService;
    }

    @PostMapping
    public ResponseEntity<DentistaEntity> addDentista(@RequestBody DentistaEntity dentistaEntity) throws BadRequestException {

        try {
            log.info("Realizando a adição do dentista");
            return ResponseEntity.ok(dentistaServiceImpl.adicionar(dentistaEntity));
        } catch (Exception e) {
            log.error("Erro ao realizar a adição do dentista");
            throw new BadRequestException("Não foi possível salvar o dentista informado.");
        }
    }

    @GetMapping
    public ResponseEntity<List<DentistaEntity>> buscarTodos() throws ResourceNotFoundException {

        try {
            log.info("Realizando a busca de todos os dentistas");
            return ResponseEntity.ok(dentistaServiceImpl.findAll());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca de todos dentistas");
            throw new ResourceNotFoundException("Não foi possível buscar os dentistas.");
        }

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DentistaEntity> findDentistaById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            log.info("Buscando o dentista pelo ID: " + id);
            return ResponseEntity.ok(dentistaServiceImpl.findById(id).get());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca do dentista de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível buscar o dentista de ID: " + id);
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<DentistaEntity> atualizarDentista(@RequestBody DentistaEntity dentistaEntity) throws ResourceNotFoundException {

        ResponseEntity responseEntity = null;

        if (!dentistaServiceImpl.findById(dentistaEntity.getId()).isPresent()) {
            log.error("Não foi possível atualizar o dentista");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Dentista não foi encontrado para ser atualizado!");
        } else {
            log.info("Atualizando o dentista");
            responseEntity = new ResponseEntity(dentistaServiceImpl.atualizar(dentistaEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deleteDentista(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            log.info("Excluindo o dentista de ID: " + id);
            return ResponseEntity.ok(dentistaServiceImpl.deletar(id));
        } catch (Exception e) {
            log.error("Não foi possível realizar a exclusão do dentista de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível deletar o dentista de ID: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
