package barbearia.Controller.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class Conexao {
   private Connection conexao;
   public Conexao() throws Exception{
       Class.forName("net.sourceforge.jtds.jdbc.Driver");
       String url = "jdbc:jtds:sqlserver://LAPTOP-PEH9GDEV:1433/barbearia";
       conexao = DriverManager.getConnection(url,"Admin","1234");
   }
    public Connection getConexao(){
        return conexao; 
    }
}