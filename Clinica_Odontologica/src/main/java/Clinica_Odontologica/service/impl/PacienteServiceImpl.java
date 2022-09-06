package Clinica_Odontologica.service.impl;

import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.entity.PacienteEntity;
import Clinica_Odontologica.repository.IDentistaRepository;
import Clinica_Odontologica.repository.IPacienteRepository;
import Clinica_Odontologica.service.IClinicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IClinicaService<PacienteEntity> {

    private final IPacienteRepository pacienteRepository;

    public PacienteServiceImpl(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<PacienteEntity> findAll() {
        return this.pacienteRepository.findAll();
    }

    @Override
    public Optional<PacienteEntity> findById(Long id) {
        return this.pacienteRepository.findById(id);
    }

    @Override
    public PacienteEntity adicionar(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null){
            return pacienteRepository.save(pacienteEntity);
        }
        return new PacienteEntity();
    }

    @Override
    public String deletar(Long id) {
        if(pacienteRepository.findById(id).isPresent()){
            pacienteRepository.deleteById(id);
            return "Paciente apagado!";
        }
        return "Paciente não encontrado.";
    }

    @Override
    public String atualizar(PacienteEntity pacienteEntity) {
        if(pacienteEntity != null && pacienteRepository.findById(pacienteEntity.getId()).isPresent()){
            pacienteRepository.saveAndFlush(pacienteEntity);
            return "Paciente atualizado!";
        }
        return "Não foi possível atualizar o paciente.";
    }
}
