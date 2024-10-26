package br.com.etec.aula20240906.model.dao;

import br.com.etec.aula20240906.model.database.DatabaseMySQL;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao {
    private Connection connection;


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public boolean inserir(model.Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, email, telefone, sexo, casado) VALUES (?,?,?,?,?)"; // se tudo estiver certo, ele vai inserir os dados com os parametros "?"
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getSexo());
            stmt.setBoolean(5, cliente.getCasado());
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    public model.Cliente getClienteById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        model.Cliente retorno = new model.Cliente();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            if(resultado.next()) {
                retorno.setId(resultado.getInt("id"));
                retorno.setNome(resultado.getString("nome"));
                retorno.setEmail(resultado.getString("email"));
                retorno.setTelefone(resultado.getString("telefone"));
                retorno.setSexo(resultado.getString("sexo"));
                retorno.setCasado(resultado.getBoolean("casado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return retorno;
    }



}
