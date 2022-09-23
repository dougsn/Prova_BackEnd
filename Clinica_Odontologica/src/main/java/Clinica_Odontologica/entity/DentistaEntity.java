package Clinica_Odontologica.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Dentista")
public class DentistaEntity {

    @Id
    @SequenceGenerator(name = "dentista_sequence", sequenceName = "dentista_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dentista_sequence")
    private Long id;
    private String nome;
    private String sobrenome;
    private String matricula;
    @OneToMany(mappedBy = "dentistaEntity", fetch = FetchType.LAZY)
    private Set<ConsultaEntity> consultas = new HashSet<ConsultaEntity>();

}
