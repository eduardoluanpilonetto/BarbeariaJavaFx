package barbearia.Controller.DAO;

import barbearia.model.Servico;
import static java.lang.Float.parseFloat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDao {
    
    Conexao con;

    public boolean incluir(Servico servico) throws Exception {
        con = new Conexao();
        String SQL = "insert into servico values (?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, servico.getNome());
        ps.setFloat(2, servico.getValor());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Servico> Pesquisar(int id) throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM servico where id = "+id;
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setId(resultado.getInt("id"));
                servico.setNome(resultado.getString("tipo"));
                servico.setValor(resultado.getFloat("valor"));
                retorno.add(servico);
            }
        } catch (SQLException ex) {}
        return retorno;
    }

    public boolean Editar(Servico servico) throws Exception {
        con = new Conexao();
        String SQL = "update servico set tipo = ?, valor = ? where id = ?";                
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, servico.getNome());
        ps.setFloat(2, servico.getValor());
        ps.setInt(3, servico.getId());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Excluir(int ID) throws Exception {
        con = new Conexao();
        String SQL = "delete from servico where id = ?";                  
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, ID);
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Servico> getList() throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM servico";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setId(resultado.getInt("id"));
                servico.setNome(resultado.getString("tipo"));
                servico.setValor(resultado.getFloat("valor"));
                retorno.add(servico);
            }
        } catch (SQLException ex) {}
        return retorno;
    }
    
    public List<String> listarNomeServico() throws Exception {
         con = new Conexao();
        String sql = "SELECT tipo FROM servico ORDER BY tipo ASC";
        List<String> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = con.getConexao().prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setNome(resultado.getString("tipo"));
                retorno.add(servico.getNome());
            }
        } catch (SQLException ex) {
        }
        return retorno;
    }
}

