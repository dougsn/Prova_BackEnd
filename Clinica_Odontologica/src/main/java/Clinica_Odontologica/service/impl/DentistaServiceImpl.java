package Clinica_Odontologica.service.impl;


import Clinica_Odontologica.repository.IDentistaRepository;
import Clinica_Odontologica.entity.DentistaEntity;
import Clinica_Odontologica.service.IDentistaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements IDentistaService<DentistaEntity> {


    private final IDentistaRepository dentistaRepository;

    public DentistaServiceImpl(IDentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    @Override
    public List<DentistaEntity> findAllDentistas() {
        return dentistaRepository.findAll();
    }

    @Override
    public Optional<DentistaEntity> findDentistaById(Long id) {
        return dentistaRepository.findById(id);
    }

    @Override
    public DentistaEntity addDentista(DentistaEntity dentistaEntity) {
        if(dentistaEntity != null){
            return dentistaRepository.save(dentistaEntity);
        }
        return new DentistaEntity();
    }

    @Override
    public String deleteDentista(Long id) {
        if(dentistaRepository.findById(id).isPresent()){
            dentistaRepository.deleteById(id);
            return "Dentista apagado!";
        }
        return "Dentista não encontrado.";
    }

    @Override
    public String atualizarDentista(DentistaEntity dentistaEntity) {
        if(dentistaEntity != null && dentistaRepository.findById(dentistaEntity.getId()).isPresent()){
            dentistaRepository.saveAndFlush(dentistaEntity);
            return "Dentista atualizado!";
        }
        return "Não foi possível atualizar o dentista.";
    }
}
