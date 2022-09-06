package Clinica_Odontologica.service;

import java.util.List;
import java.util.Optional;

public interface IPacienteService<T> {
    public List<T> findAllPacientes();
    public Optional<T> findPacienteById(Long id);
    public T addPaciente(T t);
    public String deletePaciente(Long id);
    public String atualizarPaciente(T t);
}
