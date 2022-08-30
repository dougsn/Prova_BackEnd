package Clinica_Odontologica.dao.imp;


import Clinica_Odontologica.dao.ConfiguracaoJDBC;
import Clinica_Odontologica.dao.IDao;
import Clinica_Odontologica.model.Dentista;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public DentistaDAOH2(ConfiguracaoJDBC configuracaoJDBC) {
        this.configuracaoJDBC = configuracaoJDBC;
    }

    final static Logger log = Logger.getLogger(DentistaDAOH2.class);


    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {

        log.info("Salvando o dentista: " + dentista.getNome());
        Connection connection = configuracaoJDBC.getConnection();
        Statement statement = null;
        String SqlInsert = String.format("INSERT INTO DENTISTA(NOME,SOBRENOME,MATRICULA)" +
                "VALUES ('%s','%s','%s',", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());

        try {
            statement = connection.createStatement();
            statement.executeUpdate(SqlInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next()){
                dentista.setId(keys.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            statement.close();
        }

        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        log.info("Abrindo uma conexão no Banco de Dados");
        Connection connection = configuracaoJDBC.getConnection();
        Statement statement = null;
        String query = "SELECT * FROM DENTISTA";
        List<Dentista> dentistas = new ArrayList<>();

        try{

            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(query);
            log.info("Buscando todos os produtos do banco");

            while(resultado.next()){
                dentistas.add(criarObjetoDentista(resultado));
            }


        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            connection.close();
            statement.close();
        }

        return dentistas;
    }


    @Override
    public Dentista buscarPorId(int id) throws SQLException {

        log.info("Abrindo uma conexão com o Banco de Dados");
        Connection connection = configuracaoJDBC.getConnection();
        Statement statement = null;
        String query = String.format("SELECT * FROM DENTISTA WHERE ID = %s", id);

        Dentista dentista = null;

        try {
            log.info("Buscando produto por id: " + id);
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(query);

            while (resultado.next()){
                dentista = criarObjetoDentista(resultado);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("Fechando a conexão com o Banco de Dados");
            connection.close();
            statement.close();
        }
        return dentista;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {

    }

    @Override
    public void excluir(int id) throws SQLException {

    }

    private Dentista criarObjetoDentista(ResultSet resultSet) throws SQLException{

        int id = resultSet.getInt("ID");
        String nome = resultSet.getString("NOME");
        String sobrenome = resultSet.getString("SOBRENOME");
        String matricula = resultSet.getString("MATRICULA");

        return new Dentista(id,nome,sobrenome,matricula);
    }

}
