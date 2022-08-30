package Clinica_Odontologica.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfiguracaoJDBC {
    private String jdbcDriver;
    private String dbUrl;
    private String usuario;
    private String senha;

    public ConfiguracaoJDBC(String jdbcDriver, String dbUrl, String usuario, String senha) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.usuario = usuario;
        this.senha = senha;
    }

    public ConfiguracaoJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:~/test;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'"; // Criando a tabela por um arquivo externo
        this.usuario = "sa";
        this.senha = "";
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(this.jdbcDriver);
            connection = DriverManager.getConnection(this.dbUrl,this.usuario, this.senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
