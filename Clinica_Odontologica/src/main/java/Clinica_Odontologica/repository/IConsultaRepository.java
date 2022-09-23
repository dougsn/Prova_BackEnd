package Clinica_Odontologica.repository;

import Clinica_Odontologica.entity.ConsultaEntity;
import Clinica_Odontologica.entity.DentistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    @Query(value = "select d from Dentista d where d.matricula = ?1 ", nativeQuery = true)
    Optional<DentistaEntity> findDentista(String matricula);

}
