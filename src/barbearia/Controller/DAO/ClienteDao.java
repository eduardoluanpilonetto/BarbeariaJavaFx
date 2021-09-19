package barbearia.Controller.DAO;

import java.sql.PreparedStatement;
import barbearia.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDao {

    Conexao con;

    public boolean incluir(Cliente objcliente) throws Exception {
        con = new Conexao();
        String SQL = "insert into cliente values (?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, objcliente.getNome());
        ps.setString(2, objcliente.getEndereco());
        ps.setString(3, objcliente.getTelefone());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

     public List<Cliente> pesquisar(int id) throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM cliente where id = "+id;
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Cliente cliente= new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setTelefone(resultado.getString("telefone"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {}
        return retorno;
     }
     
    public boolean Editar(Cliente objcliente) throws Exception {
        con = new Conexao();
        String SQL = "update cliente set nome = ?, endereco = ?, telefone = ? where id = ?";                  //nome cpf telefone email data nascimento
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, objcliente.getNome());
        ps.setString(2, objcliente.getEndereco());
        ps.setString(3, objcliente.getTelefone());
        ps.setInt(4, objcliente.getId());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Excluir(int ID) throws Exception {
        con = new Conexao();
        String SQL = "delete from cliente where id = ?";                  
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, ID);
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Cliente> getList() throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM cliente";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Cliente cliente= new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setTelefone(resultado.getString("telefone"));
                retorno.add(cliente);
            }
        } catch (SQLException ex) {}
        return retorno;
    }
    
     public List<String> listarNomeClientes() throws Exception {
         con = new Conexao();
        String sql = "SELECT nome FROM cliente ORDER BY nome ASC";
        List<String> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.getConexao().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(resultado.getString("nome"));
                retorno.add(cliente.getNome());
            }
        } catch (SQLException ex) {
        }
        return retorno;
    }
}
