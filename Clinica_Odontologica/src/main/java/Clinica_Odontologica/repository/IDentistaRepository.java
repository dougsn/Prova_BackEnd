package Clinica_Odontologica.repository;

import Clinica_Odontologica.entity.DentistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDentistaRepository extends JpaRepository<DentistaEntity, Long> {



}
