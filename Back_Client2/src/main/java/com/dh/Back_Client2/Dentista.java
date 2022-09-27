package com.dh.Back_Client2;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Dentista {

    private int id;
    private String nome;
    private String sobrenome;
    private String matricula;
    @OneToMany(mappedBy = "dentista", fetch = FetchType.LAZY)
    private Set<Consulta> consultas = new HashSet<Consulta>();

    @JsonManagedReference
    public Set<Consulta> getConsultas(){
        return consultas;
    }


}
