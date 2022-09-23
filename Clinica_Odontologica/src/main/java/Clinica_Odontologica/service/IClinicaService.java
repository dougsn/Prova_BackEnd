package Clinica_Odontologica.service;

import Clinica_Odontologica.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IClinicaService<T> {
    public List<T> findAll();
    public Optional<T> findById(Long id);
    public T adicionar(T t);
    public String deletar(Long id);
    public String atualizar(T t) throws ResourceNotFoundException;
}
