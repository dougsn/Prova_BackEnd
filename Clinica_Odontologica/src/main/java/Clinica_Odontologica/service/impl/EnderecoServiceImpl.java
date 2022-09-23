package Clinica_Odontologica.service.impl;

import Clinica_Odontologica.entity.EnderecoEntity;
import Clinica_Odontologica.repository.IEnderecoRepository;
import Clinica_Odontologica.service.IClinicaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements IClinicaService<EnderecoEntity> {

    private IEnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(IEnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<EnderecoEntity> findAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<EnderecoEntity> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    @Override
    public EnderecoEntity adicionar(EnderecoEntity enderecoEntity) {
        return enderecoRepository.save(enderecoEntity);
    }

    @Override
    public String deletar(Long id) {
        enderecoRepository.deleteById(id);
        return "Consulta apagada!";
    }

    @Override
    public String atualizar(EnderecoEntity enderecoEntity) {
        enderecoRepository.saveAndFlush(enderecoEntity);
        return "Endereço Atualizado";
    }
}
