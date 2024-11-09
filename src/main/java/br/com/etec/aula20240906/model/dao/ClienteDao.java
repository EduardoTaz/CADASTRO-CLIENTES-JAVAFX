/*

package br.com.etec.aula20240906.model.dao;

import br.com.etec.aula20240906.model.database.DatabaseMySQL;
import javafx.fxml.FXML;

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

            if(resultado.next()) { // se tiver um id igual esse  cara, ele retorna true
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

    public Boolean deleteClienteById(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {

            PreparedStatement stmt = connection.prepareStatement(sql); // A consulta SQL é preparada usando a connection
            stmt.setInt(1, id);
            // O setInt(1, id) informa ao PreparedStatement que o primeiro parâmetro (marcado pelo ?) deve ser substituído pelo valor de id que foi passado para o método.
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            // Um log de erro é gerado usando a classe Logger, registrando o erro com nível SEVERE (grave), para facilitar o diagnóstico e a solução do problema.
            return false;
        }
    }


}


// DAO = DATA ACESS OBJECT

*/


package br.com.etec.aula20240906.model.dao;

import br.com.etec.aula20240906.model.database.DatabaseMySQL;
import javafx.fxml.FXML;

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
        // Recupera o maior ID existente na tabela de clientes
        int maiorId = obterMaiorId();
        int novoId = maiorId + 1;

        // SQL para inserção de um novo cliente
        String sql = "INSERT INTO clientes (id, nome, email, telefone, sexo, casado) VALUES (?,?,?,?,?,?)";
        try {
            // Prepara a declaração SQL com os parâmetros necessários
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, novoId); // Define o novo ID
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getSexo());
            stmt.setBoolean(6, cliente.getCasado());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            // Registra o erro em caso de falha na inserção
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private int obterMaiorId() {
        int maiorId = 0;
        String sql = "SELECT MAX(id) AS maiorId FROM clientes";
        try {
            // Prepara e executa a consulta SQL para obter o maior ID
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                maiorId = resultado.getInt("maiorId");
            }
            resultado.close();
            stmt.close();
        } catch (SQLException ex) {
            // Registra o erro em caso de falha na consulta
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maiorId;
    }

    /**
     * Retorna um cliente com base no ID fornecido.
     *
     * @param id O ID do cliente a ser recuperado
     * @return O cliente correspondente ao ID fornecido, ou null em caso de erro
     */
    public model.Cliente getClienteById(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        model.Cliente retorno = new model.Cliente();
        try {
            // Prepara a declaração SQL com o parâmetro necessário
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                // Preenche o objeto Cliente com os dados recuperados
                retorno.setId(resultado.getInt("id"));
                retorno.setNome(resultado.getString("nome"));
                retorno.setEmail(resultado.getString("email"));
                retorno.setTelefone(resultado.getString("telefone"));
                retorno.setSexo(resultado.getString("sexo"));
                retorno.setCasado(resultado.getBoolean("casado"));
            }
            resultado.close();
            stmt.close();
        } catch (SQLException ex) {
            // Registra o erro em caso de falha na consulta
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return retorno;
    }

    /**
     * Exclui um cliente com base no ID fornecido.
     *
     * @param id O ID do cliente a ser excluído
     * @return true se a exclusão foi bem-sucedida, false caso contrário
     */
    public Boolean deleteClienteById(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            // Prepara a declaração SQL com o parâmetro necessário
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException ex) {
            // Registra o erro em caso de falha na exclusão
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Recupera todos os clientes da tabela.
     *
     * @return Um ResultSet contendo todos os clientes
     */
    public ResultSet listarTodos() {
        String sql = "SELECT * FROM clientes ORDER BY id";
        try {
            // Prepara e executa a consulta SQL para obter todos os clientes
            PreparedStatement stmt = connection.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException ex) {
            // Registra o erro em caso de falha na consulta
            Logger.getLogger(DatabaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}