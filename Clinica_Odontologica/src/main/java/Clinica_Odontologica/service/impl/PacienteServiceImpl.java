package Clinica_Odontologica.service.impl;

import Clinica_Odontologica.entity.PacienteEntity;
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
        return pacienteRepository.save(pacienteEntity);
    }

    @Override
    public String deletar(Long id) {

        pacienteRepository.deleteById(id);
        return "Paciente apagado!";
    }

    @Override
    public String atualizar(PacienteEntity pacienteEntity) {

        pacienteRepository.saveAndFlush(pacienteEntity);
        return "Paciente Atualizado";
    }
}
