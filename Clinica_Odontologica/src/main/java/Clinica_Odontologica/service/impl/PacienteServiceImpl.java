package Clinica_Odontologica.service.impl;

import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.entity.PacienteEntity;
import Clinica_Odontologica.repository.IPacienteRepository;
import Clinica_Odontologica.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService<PacienteEntity> {

    private final IPacienteRepository pacienteRepository;

    public PacienteServiceImpl(IPacienteRepository pacienteService) {
        this.pacienteRepository = pacienteService;
    }


    @Override
    public List<PacienteEntity> findAllPacientes() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Optional<PacienteEntity> findPacienteById(Long id) {
        return this.pacienteRepository.findById(id);
    }

    @Override
    public PacienteEntity addPaciente(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null){
            return pacienteRepository.save(pacienteEntity);
        }
        return new PacienteEntity();
    }

    @Override
    public String deletePaciente(Long id) {
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
            return "Paciente apagado!";
        }
        return "Paciente não encontrado.";
    }

    @Override
    public String atualizarPaciente(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null && pacienteRepository.findById(pacienteEntity.getId()).isPresent()){
            pacienteRepository.saveAndFlush(pacienteEntity);
            return "Paciente atualizado!";
        }
        return "Não foi possível atualizar o paciente.";
    }
}
