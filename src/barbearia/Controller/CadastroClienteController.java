package barbearia.Controller;

import barbearia.Controller.DAO.ClienteDao;
import barbearia.model.Cliente;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class CadastroClienteController implements Initializable {

    @FXML private TextField telefone;
    @FXML private Label idlc;
    @FXML private TextField endereco;
    @FXML private TextField idc;
    @FXML private TextField nome;
    @FXML private Button pesquisarc;
    @FXML private Button novoc;
    @FXML private Label labelShop;
    @FXML private Label enderecolc;
    @FXML private Label nomelc;
    @FXML private Label labelBarber;
    @FXML private Label telefonelc;
    @FXML private Button excluirc;
    @FXML private Button editarc;
    @FXML private Button atulizarTabela;
    @FXML private TableView<Cliente> tableC;
    @FXML private TableColumn<Cliente, Integer> CID;
    @FXML private TableColumn<Cliente, String> CTELEFONE;
    @FXML private TableColumn<Cliente, String> CENDERECO;
    @FXML private TableColumn<Cliente, String> CNOME;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        novoc.setOnMouseClicked((MouseEvent e) -> {
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = new Cliente(nome.getText(),endereco.getText(),telefone.getText());
            try {
               clienteDao.incluir(cliente);
                JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Falha no cadastro");
            }
                
        });
        
        excluirc.setOnMouseClicked((MouseEvent e) -> {
            ClienteDao clienteDao = new ClienteDao();
            int id = parseInt(idc.getText());
            try {
                clienteDao.Excluir(id);
                JOptionPane.showMessageDialog(null,"Cadastro excluido com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Falha ao excluir cadastro");
            }
        });
        
        editarc.setOnMouseClicked((MouseEvent e) -> {
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = new Cliente(parseInt(idc.getText()),nome.getText(),endereco.getText(),telefone.getText());
            try {
                clienteDao.Editar(cliente);
                JOptionPane.showMessageDialog(null,"Cadastro editado com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Falha ao atualizar cadastro");
            }
        });
        try {PopularTabela();} catch (Exception ex) { }
        pesquisarc.setOnMouseClicked((MouseEvent e) -> {
            try {PesquisarTabela();} catch (Exception ex) {}
        });
        
        atulizarTabela.setOnMouseClicked((MouseEvent e) -> {
            try {PopularTabela();} catch (Exception ex) {}
        });
    }
        
        public void PopularTabela() throws Exception{
            ClienteDao clienteDao = new ClienteDao();
            List<Cliente> users = clienteDao.getList();
            CID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CNOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
            CENDERECO.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            CTELEFONE.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            
            tableC.setItems(FXCollections.observableArrayList(users));
        }
        
        public void PesquisarTabela() throws Exception{
            ClienteDao clienteDao = new ClienteDao();
            List<Cliente> users = clienteDao.pesquisar(parseInt(idc.getText()));
            CID.setCellValueFactory(new PropertyValueFactory<>("id"));
            CNOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
            CENDERECO.setCellValueFactory(new PropertyValueFactory<>("endereco"));
            CTELEFONE.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            
            tableC.setItems(FXCollections.observableArrayList(users));
        }
}
