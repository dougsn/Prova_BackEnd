package Clinica_Odontologica.repository;

import Clinica_Odontologica.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<PacienteEntity, Long> {
}
