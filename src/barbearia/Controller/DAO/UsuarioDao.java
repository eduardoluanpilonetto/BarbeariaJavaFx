package barbearia.Controller.DAO;

import barbearia.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDao {
    Conexao con;

    public boolean incluir(Usuario user) throws Exception {
        con = new Conexao();
        String SQL = "insert into usuario (login,senha) values (?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getSenha());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Usuario> Pesquisar(int id) throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM usuario where id = "+id;
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Usuario user = new Usuario();
                user.setId(resultado.getInt("id"));
                user.setLogin(resultado.getString("login"));
                user.setSenha(resultado.getString("senha"));
                retorno.add(user);
            }
        } catch (SQLException ex) {}
        return retorno;
    }

    public boolean Editar(Usuario user) throws Exception {
        con = new Conexao();
        String SQL = "update usuario set login = ?, senha = ? where id = ?";                  
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getSenha());
        ps.setInt(3, user.getId());
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Excluir(int ID) throws Exception {
        con = new Conexao();
        String SQL = "delete from Usuario where id = ?";                  
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, ID);
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Usuario> getList() throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM usuario";
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Usuario user = new Usuario();
                user.setId(resultado.getInt("id"));
                user.setLogin(resultado.getString("login"));
                user.setSenha(resultado.getString("senha"));
                retorno.add(user);
            }
        } catch (SQLException ex) {}
        return retorno;
    }
    
    public boolean logar (String login, String senha) throws Exception{
       con = new Conexao();
       boolean check;
        String SQL = "select * from usuario where login = ? and senha = ?";                  
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, login); 
        ps.setString(2, senha);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
}
