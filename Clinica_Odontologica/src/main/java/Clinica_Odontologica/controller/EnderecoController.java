package Clinica_Odontologica.controller;


import Clinica_Odontologica.entity.ConsultaEntity;
import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.entity.EnderecoEntity;
import Clinica_Odontologica.exceptions.BadRequestException;
import Clinica_Odontologica.exceptions.ResourceNotFoundException;
import Clinica_Odontologica.service.impl.EnderecoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

@RestController
@RequestMapping("/clinica/endereco")
public class EnderecoController {

    final static Logger log = getLogger(String.valueOf(EnderecoController.class));
    private EnderecoServiceImpl enderecoServiceImpl;

    public EnderecoController(EnderecoServiceImpl enderecoService) {
        this.enderecoServiceImpl = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoEntity> addEndereco(@RequestBody EnderecoEntity enderecoEntity) throws BadRequestException {

        try {
            log.info("Realizando a adição do endereço");
            return ResponseEntity.ok(enderecoServiceImpl.adicionar(enderecoEntity));
        } catch (Exception e) {
            log.error("Erro ao realizar a adição do endereço");
            throw new BadRequestException("Não foi possível salvar o endereço informado.");
        }

    }

    @GetMapping
    public ResponseEntity<List<EnderecoEntity>> buscarTodos() throws ResourceNotFoundException {

        try {
            log.info("Realizando a busca de todos endereços");
            return ResponseEntity.ok(enderecoServiceImpl.findAll());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca de todos endereços");
            throw new ResourceNotFoundException("Não foi possível buscar os endereços.");
        }

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EnderecoEntity> findEnderecoById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            log.info("Buscando o endereço pelo ID: " + id);
            return ResponseEntity.ok(enderecoServiceImpl.findById(id).get());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca do endereço de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível buscar o endereço de ID: " + id);
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<EnderecoEntity> atualizarEndereco(@RequestBody EnderecoEntity enderecoEntity) throws ResourceNotFoundException {

        ResponseEntity responseEntity = null;

        if (!enderecoServiceImpl.findById(enderecoEntity.getId()).isPresent()) {
            log.error("Não foi possível atualizar o endereco");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Endereço não foi encontrado para ser atualizado!");
        } else {
            log.info("Atualizando o endereço");
            responseEntity = new ResponseEntity(enderecoServiceImpl.atualizar(enderecoEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws ResourceNotFoundException {

        try {
            log.info("Excluindo o endereco de ID: " + id);
            return ResponseEntity.ok(enderecoServiceImpl.deletar(id));
        } catch (Exception e) {
            log.error("Não foi possível realizar a exclusão do endereço de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível deletar o endereço de ID: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
