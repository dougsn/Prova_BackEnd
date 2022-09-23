package Clinica_Odontologica.service.impl;


import Clinica_Odontologica.exceptions.ResourceNotFoundException;
import Clinica_Odontologica.repository.IDentistaRepository;
import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.service.IClinicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements IClinicaService<DentistaEntity> {


    private final IDentistaRepository dentistaRepository;

    public DentistaServiceImpl(IDentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public List<DentistaEntity> findAll() {
        return dentistaRepository.findAll();
    }

    @Override
    public Optional<DentistaEntity> findById(Long id) {
        return dentistaRepository.findById(id);
    }

    @Override
    public DentistaEntity adicionar(DentistaEntity dentistaEntity) {
        return dentistaRepository.save(dentistaEntity);
    }

    @Override
    public String deletar(Long id) {
        dentistaRepository.deleteById(id);
        return "Dentista apagado!";
    }

    @Override
    public String atualizar(DentistaEntity dentistaEntity) {
        dentistaRepository.saveAndFlush(dentistaEntity);
        return "Dentista atualizado!";
    }
}
