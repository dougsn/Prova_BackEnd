package Clinica_Odontologica.service;

import Clinica_Odontologica.dao.IDao;
import Clinica_Odontologica.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PacienteService {


    @Autowired
    IDao<Paciente> pacienteIDao;

    public Paciente salvar(Paciente paciente) throws SQLException {
        return pacienteIDao.salvar(paciente);
    }

    public List<Paciente> buscarTodos() throws SQLException {
        return pacienteIDao.buscarTodos();
    }

    public Paciente buscarPorId(int id) throws SQLException {
        return pacienteIDao.buscarPorId(id);
    }

    public String alterar(Paciente paciente) throws SQLException{
        return pacienteIDao.alterar(paciente);
    }

    public String excluir(int id) throws SQLException {
        return pacienteIDao.excluir(id);
    }


}
