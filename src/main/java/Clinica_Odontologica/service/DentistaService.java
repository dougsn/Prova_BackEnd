package Clinica_Odontologica.service;


import Clinica_Odontologica.dao.ConfiguracaoJDBC;
import Clinica_Odontologica.dao.IDao;
import Clinica_Odontologica.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistaService {

    private ConfiguracaoJDBC configuracaoJDBC;



    @Autowired
    IDao<Dentista> dentistaIDaoH2;

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaIDaoH2.salvar(dentista);
    }

    public List<Dentista> buscarTodos() throws SQLException {
        return dentistaIDaoH2.buscarTodos();
    }

    public Dentista buscarPorId(int id) throws SQLException {
        return dentistaIDaoH2.buscarPorId(id);
    }

    public String alterar(Dentista dentista) throws SQLException{
        return dentistaIDaoH2.alterar(dentista);
    }

    public String excluir(int id) throws SQLException {
        return dentistaIDaoH2.excluir(id);
    }

}
