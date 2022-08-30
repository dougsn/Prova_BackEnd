package Clinica_Odontologica.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    public T salvar(T t) throws SQLException;
    public List<T> buscarTodos() throws SQLException;
    public T buscarPorId (int id) throws SQLException;
    public void alterar(T t) throws SQLException;
    public void excluir(int id) throws SQLException;

}
