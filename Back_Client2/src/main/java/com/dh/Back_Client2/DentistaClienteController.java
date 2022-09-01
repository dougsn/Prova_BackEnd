package com.dh.Back_Client2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class DentistaClienteController {

    private DentistaCliente dentistaCliente = new DentistaCliente();

    @PostMapping("/salvar")
    public Dentista salvar(@RequestBody Dentista dentista){
        return dentistaCliente.salvar(dentista);
    }

    @GetMapping("/buscarTodos")
    public List<Dentista> buscarTodos(){
        return dentistaCliente.buscarTodos();
    }

    @GetMapping("/{id}")
    public Dentista buscarPorId(@PathVariable Integer id){
        return dentistaCliente.buscarPorId(id);
    }

    @PutMapping("/alterar")
    public Dentista alterar(@RequestBody Dentista dentista){
        return dentistaCliente.alterar(dentista);
    }

    @PostMapping("/{id}")
    public String excluir(@PathVariable Integer id){
        return dentistaCliente.excluir(id);
    }
}
