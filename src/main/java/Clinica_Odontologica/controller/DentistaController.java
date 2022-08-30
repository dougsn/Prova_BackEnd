package Clinica_Odontologica.controller;


import Clinica_Odontologica.model.Dentista;
import Clinica_Odontologica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")

public class DentistaController {

    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public Dentista salvar(@RequestBody Dentista dentista) throws SQLException{
        return dentistaService.salvar(dentista);
    }

    @GetMapping
    public List<Dentista> buscarTodos() throws SQLException {
        return dentistaService.buscarTodos();
    }

    @GetMapping("/buscar/{id}")
    public Dentista buscarPorId(@PathVariable int id) throws SQLException {
        return dentistaService.buscarPorId(id);
    }

    @PutMapping("/alterar")
    public String alterar(@RequestBody Dentista dentista) throws SQLException{
        return dentistaService.alterar(dentista);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluir(@PathVariable int id) throws SQLException {
        return dentistaService.excluir(id);
    }




}
