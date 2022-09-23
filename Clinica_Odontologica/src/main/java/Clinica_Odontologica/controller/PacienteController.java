package Clinica_Odontologica.controller;

import Clinica_Odontologica.entity.EnderecoEntity;
import Clinica_Odontologica.entity.PacienteEntity;
import Clinica_Odontologica.exceptions.BadRequestException;
import Clinica_Odontologica.exceptions.ResourceNotFoundException;
import Clinica_Odontologica.service.impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

@RestController
@RequestMapping("/clinica/paciente")
public class PacienteController {

    final static Logger log = getLogger(String.valueOf(PacienteController.class));
    private final PacienteServiceImpl pacienteService;

    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteEntity> addPaciente(@RequestBody PacienteEntity pacienteEntity) throws BadRequestException {

        try {
            log.info("Realizando a adição do paciente");
            return ResponseEntity.ok(pacienteService.adicionar(pacienteEntity));
        } catch (Exception e) {
            log.error("Erro ao realizar a adição do paciente" +
                    "");
            throw new BadRequestException("Não foi possível salvar o paciente informado.");
        }

    }

    @GetMapping
    public ResponseEntity<List<PacienteEntity>> buscarTodos() throws ResourceNotFoundException {

        try {
            log.info("Realizando a busca de todos pacientes");
            return ResponseEntity.ok(pacienteService.findAll());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca de todos pacientes");
            throw new ResourceNotFoundException("Não foi possível buscar os pacientes.");
        }

    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteEntity> findPacineteById(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            log.info("Buscando o paciente pelo ID: " + id);
            return ResponseEntity.ok(pacienteService.findById(id).get());
        } catch (Exception e) {
            log.error("Não foi possível realizar a busca do paciente de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível buscar o paciente de ID: " + id);
        }
    }

    @PutMapping("/alterar")
    public ResponseEntity<PacienteEntity> atualizarPaciente(@RequestBody PacienteEntity pacienteEntity) throws ResourceNotFoundException {

        ResponseEntity responseEntity = null;

        if (!pacienteService.findById(pacienteEntity.getId()).isPresent()) {
            log.error("Não foi possível atualizar o paciente");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Paciente não foi encontrado para ser atualizado!");
        } else {
            log.info("Atualizando o paciente");
            responseEntity = new ResponseEntity(pacienteService.atualizar(pacienteEntity), HttpStatus.OK);
        }
        return responseEntity;

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity excluir(@PathVariable Long id) throws ResourceNotFoundException {

        try {
            log.info("Excluindo o paciente de ID: " + id);
            return ResponseEntity.ok(pacienteService.deletar(id));
        } catch (Exception e) {
            log.error("Não foi possível realizar a exclusão do paciente de ID: " + id);
            throw new ResourceNotFoundException("Não foi possível deletar o paciente de ID: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processarErrorBadRequest(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
