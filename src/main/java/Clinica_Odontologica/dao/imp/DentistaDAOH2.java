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



    final static Logger log = Logger.getLogger(DentistaDAOH2.class);


    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {

        log.info("Salvando o dentista: " + dentista.getNome());
        Connection connection = null;
        Statement statement = null;
        String SqlInsert = String.format("INSERT INTO DENTISTA(NOME,SOBRENOME,MATRICULA)" +
                "VALUES ('%s','%s','%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
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
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM DENTISTA";
        List<Dentista> dentistas = new ArrayList<>();

        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
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
        Connection connection = null;
        Statement statement = null;
        String query = String.format("SELECT * FROM DENTISTA WHERE ID = %s", id);

        Dentista dentista = null;

        try {
            log.info("Buscando produto por id: " + id);
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
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
    public String alterar(Dentista dentista) throws SQLException {

        String SQLUpdate = String.format("UPDATE DENTISTA SET NOME = '%s' WHERE ID = '%s'", dentista.getNome(), dentista.getId());
        Connection connection = null;

        try {
            log.info("Alterando o nome do Dentista: " + dentista.getId() + " - " + dentista.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLUpdate);

        } catch (SQLException e){
            log.error("Erro ao atualizar o Dentista: " + dentista.getNome());
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão");
            connection.close();
        }

        return "Produto alterado com sucesso!";


    }

    @Override
    public String excluir(int id) throws SQLException {

        log.info("Abrindo conexão com o Banco de Dados");
        Statement statement = null;
        Connection connection = null;
        String SQLDelete = String.format("DELETE FROM DENTISTA WHERE ID = '%s'", id);

        try {
            log.info("Abrindo conexão com o Banco de Dados");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("Deletando o produto por id: " + id);
            statement = connection.createStatement();
            statement.execute(SQLDelete);

        } catch (SQLException e){
            log.error("Erro ao tentar deletar o produto de id: " + id);
            e.printStackTrace();
        } finally {
            log.info("Encerrando conexão com o Banco de Dados");
            connection.close();
            statement.close();
        }

        return "Produto excluído com sucesso!";

    }

    private Dentista criarObjetoDentista(ResultSet resultSet) throws SQLException{

        int id = resultSet.getInt("ID");
        String nome = resultSet.getString("NOME");
        String sobrenome = resultSet.getString("SOBRENOME");
        String matricula = resultSet.getString("MATRICULA");

        return new Dentista(id,nome,sobrenome,matricula);
    }

}
