package br.com.etec.aula20240906.model.database;
import java.sql.Connection;

public interface Database {
    public Connection conectar();

    public void desconectar();
}
