package Clinica_Odontologica.dao.imp;

import Clinica_Odontologica.dao.ConfiguracaoJDBC;
import Clinica_Odontologica.dao.IDao;
import Clinica_Odontologica.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class PacienteDAOH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC = new ConfiguracaoJDBC();
    final static Logger log = Logger.getLogger(PacienteDAOH2.class);

    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Salvando o paciente: " + paciente.getNome());
        Connection connection = null;
        PreparedStatement pstatement = null;
        String SqlInsert = String.format("INSERT INTO PACIENTE(NOME,SOBRENOME,ENDERECO,DATAALTA)" +
                "VALUES (?,?,?,?)");

        try {
            connection = configuracaoJDBC.getConnection();
            pstatement = connection.prepareStatement(SqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            pstatement.setString(1, paciente.getNome());
            pstatement.setString(2, paciente.getSobrenome());
            pstatement.setString(3, paciente.getEndereco());
            pstatement.setDate(4, Date.valueOf(paciente.getDataAlta()));
            pstatement.execute();
            ResultSet keys = pstatement.getGeneratedKeys();

            if (keys.next()){
                paciente.setId(keys.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            pstatement.close();
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        log.info("Abrindo uma conexão no Banco de Dados");
        Connection connection = null;
        PreparedStatement pstatement = null;
        String query = "SELECT * FROM PACIENTE";
        List<Paciente> pacientes = new ArrayList<>();

        try{
            connection = configuracaoJDBC.getConnection();
            pstatement = connection.prepareStatement(query);
            ResultSet resultado = pstatement.executeQuery();
            log.info("Buscando todos os pacientes do banco");

            while(resultado.next()){
                pacientes.add(criarObjetoPaciente(resultado));
            }


        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            connection.close();
            pstatement.close();
        }
        return pacientes;
    }

    private Paciente criarObjetoPaciente(ResultSet resultado) throws SQLException{
        int id = resultado.getInt("ID");
        String nome = resultado.getString("NOME");
        String sobrenome = resultado.getString("SOBRENOME");
        String endereco = resultado.getString("ENDERECO");
        LocalDate dataAlta = resultado.getDate("DATAALTA").toLocalDate();

        return new Paciente(id,nome,sobrenome,endereco,dataAlta);

    }

    @Override
    public Paciente buscarPorId(int id) throws SQLException {

        log.info("Abrindo uma conexão com o Banco de Dados");
        Connection connection = null;
        PreparedStatement pstatement = null;
        String query = String.format("SELECT * FROM PACIENTE WHERE ID = ?");

        Paciente paciente = null;

        try {
            log.info("Buscando paciente por id: " + id);
            connection = configuracaoJDBC.getConnection();
            pstatement = connection.prepareStatement(query);
            pstatement.setInt(1, id);
            ResultSet resultado = pstatement.executeQuery();

            while (resultado.next()){
                paciente = criarObjetoPaciente(resultado);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Fechando a conexão com o Banco de Dados");
            connection.close();
            pstatement.close();
        }
        return paciente;

    }

    @Override
    public String alterar(Paciente paciente) throws SQLException {

        String SQLUpdate = String.format("UPDATE PACIENTE SET NOME = ? WHERE ID = ?");
        Connection connection = null;

        try {
            log.info("Alterando o nome do Paciente: " + paciente.getId() + " - " + paciente.getNome());
            connection = configuracaoJDBC.getConnection();
            PreparedStatement pstatement = connection.prepareStatement(SQLUpdate);
            pstatement.setString(1,paciente.getNome());
            pstatement.setInt(2,paciente.getId());
            pstatement.executeUpdate();

        } catch (SQLException e){
            log.error("Erro ao atualizar o Paciente: " + paciente.getNome());
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão");
            connection.close();
        }

        return "Paciente alterado com sucesso!";

    }

    @Override
    public String excluir(int id) throws SQLException {

        PreparedStatement pstatement = null;
        Connection connection = null;
        String SQLDelete = String.format("DELETE FROM PACIENTE WHERE ID = ?", id);

        try {
            log.info("Abrindo conexão com o Banco de Dados");
            connection = configuracaoJDBC.getConnection();
            log.info("Deletando o Paciente por id: " + id);
            pstatement = connection.prepareStatement(SQLDelete);
            pstatement.execute();

        } catch (SQLException e){
            log.error("Erro ao tentar deletar o Paciente de id: " + id);
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            connection.close();
            pstatement.close();
        }

        return "Paciente excluído com sucesso!";

    }
}
