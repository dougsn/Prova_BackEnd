package Clinica_Odontologica.service;

import java.util.List;
import java.util.Optional;

public interface IDentistaService<T> {
    public List<T> findAllDentistas();
    public Optional<T> findDentistaById(Long id);
    public T addDentista(T t);
    public String deleteDentista(Long id);
    public String atualizarDentista(T t);
}
