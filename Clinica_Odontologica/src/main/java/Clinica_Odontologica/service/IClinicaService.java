package Clinica_Odontologica.service;

import java.util.List;
import java.util.Optional;

public interface IClinicaService<T> {
    public List<T> findAll();
    public Optional<T> findById(Long id);
    public T adicionar(T t);
    public String deletar(Long id);
    public String atualizar(T t);
}
