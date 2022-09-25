package Clinica_Odontologica.service.impl;

import Clinica_Odontologica.entity.ConsultaEntity;
import Clinica_Odontologica.repository.IConsultaRepository;
import Clinica_Odontologica.service.IClinicaService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.log4j.Logger.getLogger;


@Service
public class ConsultaServiceImpl implements IClinicaService<ConsultaEntity> {

    final static Logger log = getLogger(String.valueOf(ConsultaServiceImpl.class));


    private final IConsultaRepository consultaRepository;

    public ConsultaServiceImpl(IConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }


    @Override
    public List<ConsultaEntity> findAll() {
        return consultaRepository.findAll();
    }

    @Override
    public Optional<ConsultaEntity> findById(Long id) {
        return consultaRepository.findById(id);
    }

    @Override
    public ConsultaEntity adicionar(ConsultaEntity consultaEntity) {

        return consultaRepository.save(consultaEntity);
    }

    @Override
    public String deletar(Long id) {
        log.info("Realizando a exclusão da consulta");
        consultaRepository.deleteById(id);
        return "Consulta apagada!";
    }


    @Override
    public String atualizar(ConsultaEntity consultaEntity) {
        consultaRepository.saveAndFlush(consultaEntity);
        return "Consulta Atualizada.";
    }


}
