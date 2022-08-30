package Clinica_Odontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public T salvar(T t) throws SQLException;
    public List<T> buscarTodos() throws SQLException;
    public T buscarPorId (int id) throws SQLException;
    public String alterar(T t) throws SQLException;
    public String excluir(int id) throws SQLException;

}
