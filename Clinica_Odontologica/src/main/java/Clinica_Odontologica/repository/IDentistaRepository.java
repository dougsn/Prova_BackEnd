package Clinica_Odontologica.repository;

import Clinica_Odontologica.entity.DentistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDentistaRepository extends JpaRepository<DentistaEntity, Long> {

}
