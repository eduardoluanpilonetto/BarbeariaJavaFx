package barbearia.Controller.DAO;

import barbearia.Controller.DAO.Conexao;
import barbearia.model.Agendamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AgendamentoDao {

    Conexao con;
    
    public boolean incluir(Agendamento age) throws Exception {
        con = new Conexao();
        String SQL = "insert into agendamento values (?,?,?)";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setString(1, age.getNomeCliente());
        ps.setString(2, age.getNomeServico());
        ps.setString(3, age.getData());
        if (ps.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null,"Agendado com sucesso");
            return true;
        } else {
            return false;
        }
    }
    
    public boolean Excluir(int ID) throws Exception {
        con = new Conexao();
        String SQL = "delete from agendamento where id = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, ID);
        if (ps.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Agendamento> getList() throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM agendamento";
        List<Agendamento> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Agendamento age = new Agendamento();
                age.setId(resultado.getInt("id"));
                age.setNomeCliente(resultado.getString("NomeCliente"));
                age.setNomeServico(resultado.getString("NomeServico"));
                age.setData(resultado.getString("data"));
                retorno.add(age);
            }
        } catch (SQLException ex) {JOptionPane.showMessageDialog(null, "caixa erro");}
        return retorno;
    }
    
    public List<Agendamento> Pesquisa(int id) throws Exception{
        con = new Conexao();
        String sql = "SELECT * FROM agendamento where id ="+id;
        List<Agendamento> retorno = new ArrayList<>();
        try {
            PreparedStatement ps = con.getConexao().prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Agendamento age = new Agendamento();
                age.setId(resultado.getInt("id"));
                age.setNomeCliente(resultado.getString("NomeCliente"));
                age.setNomeServico(resultado.getString("NomeServico"));
                age.setData(resultado.getString("data"));
                retorno.add(age);
            }
        } catch (SQLException ex) {JOptionPane.showMessageDialog(null, "caixa erro");}
        return retorno;
    }
}
